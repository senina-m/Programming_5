package ru.senina.lab5;

import ru.senina.lab5.commands.*;
import ru.senina.lab5.labwork.Coordinates;
import ru.senina.lab5.labwork.Difficulty;
import ru.senina.lab5.labwork.Discipline;
import ru.senina.lab5.labwork.LabWork;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * Class to deal with input and output and keep CollectionKeeper class instance.
 */
public class Keeper {
    private String filename;
    private CollectionKeeper collectionKeeper;

    /**
     * @param filename the path to file from which we read and to which we write collection data
     */
    public Keeper(String filename) {
        this.filename = filename;
    }

    /**
     * Method to start a new collection and System.in reader
     */
    public void start() {
        Parser parser = new Parser();
        try {
            File f = new File(filename);
            collectionKeeper = parser.fromJsonToCollectionKeeper(parser.fromFileToString(filename));
        } catch (InvalidArgumentsException | NullPointerException | IOException e) {
            System.out.println("Filename is wrong. Run program again with correct filename.");

        }

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand(collectionKeeper));
        commandMap.put("show", new ShowCommand(collectionKeeper, parser));
        commandMap.put("add", new AddCommand(collectionKeeper));
        commandMap.put("update", new UpdateCommand(collectionKeeper));
        commandMap.put("remove_by_id", new RemoveByIDCommand(collectionKeeper));
        commandMap.put("clear", new ClearCommand(collectionKeeper));
        commandMap.put("save", new SaveCommand(collectionKeeper, parser, filename));
        commandMap.put("remove_at", new RemoveAtCommand(collectionKeeper));
        commandMap.put("remove_greater", new RemoveGreaterCommand(collectionKeeper));
        commandMap.put("sort", new SortCommand(collectionKeeper));
        commandMap.put("min_by_difficulty", new MinByDifficultyCommand(collectionKeeper, parser));
        commandMap.put("filter_by_description", new FilterByDescriptionCommand(collectionKeeper, parser));
        commandMap.put("print_descending", new PrintDescendingCommand(collectionKeeper, parser));
        try {
            terminal(parser, commandMap, "no file", 0);
        } catch (IOException e) {
            throw new InvalidArgumentsException("You have entered wrong file name. Try the command again.");
        }

    }

    /**
     * @param parser     Parser instance
     * @param commandMap Map of all commands from string command to Command instance
     * @param filename   file to execute script
     * @param level      recursion level
     * @throws IOException throws if file has a wrong name
     */
    public void terminal(Parser parser, Map<String, Command> commandMap, String filename, int level) throws IOException {
        if (level > 10) {
            System.out.println("You can't execute file recursively more then 10 times! The programme will be finished!");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File(filename);
        if (f.exists() && !f.isDirectory() && Files.isReadable(f.toPath())) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        }

        while (true) {
            try {
                System.out.print("> ");
                String[] newline = br.readLine().split("[ \t\f]+");
                String[] line = cleanLine(newline);
                if (line.length > 0) {
                    if (commandMap.containsKey(line[0])) {
                        boolean validCommand = true;
                        Command command = commandMap.get(line[0]);
                        command.setArgs(line);
                        boolean commandIsReady = true;
                        // TODO: переписать чтобы было без commandIsReady
                        if (command instanceof ElementNeed) {
                            commandIsReady = false;
                            boolean exit = false;
                            while (!exit) {
                                try {
                                    //TODO: rewrite without code duplication
                                    LabWork element = new LabWork();
                                    System.out.println("You run a command, which needs LabWork element to be entered.");

                                    System.out.println("Enter element's name.");
                                    element.setName(br.readLine());

                                    System.out.println("Enter coordinates. In first line x <= 74. In second y >= -47.");
                                    element.setCoordinates(new Coordinates(Integer.parseInt(br.readLine()), Long.parseLong(br.readLine())));

                                    System.out.println("Enter minimal point.");
                                    element.setMinimalPoint(Float.parseFloat(br.readLine()));

                                    System.out.println("Enter element description.");
                                    element.setDescription(br.readLine());

                                    System.out.println("Enter average point.");
                                    element.setAveragePoint(Integer.parseInt(br.readLine()));

                                    System.out.println("Enter one difficulty of following list:");
                                    Difficulty[] difficulties = Difficulty.values();
                                    for (Difficulty difficulty : difficulties) {
                                        System.out.print(difficulty.toString() + "; ");
                                    }

                                    element.setDifficulty(br.readLine());

                                    System.out.println("Enter discipline parametrs:");
                                    Discipline discipline = new Discipline();
                                    System.out.println("Enter discipline name.");
                                    discipline.setName(br.readLine());
                                    System.out.println("Enter discipline lectureHours.");
                                    discipline.setLectureHours(Long.parseLong(br.readLine()));
                                    System.out.println("Enter discipline practiceHours.");
                                    discipline.setPracticeHours(Integer.parseInt(br.readLine()));
                                    System.out.println("Enter discipline selfStudyHours.");
                                    discipline.setSelfStudyHours(Integer.parseInt(br.readLine()));
                                    element.setDiscipline(discipline);
                                    ((ElementNeed) command).setLabWorkElement(element);
                                    commandIsReady = true;
                                    exit = true;
                                } catch (InvalidArgumentsException e) {
                                    System.out.println("You have entered invalidate value." + e.getMessage() + "\nDo you want to exit from command? (yes/no)");
                                    if (br.readLine().equals("yes") && !filename.equals("no file")) {
                                        exit = true;
                                        commandIsReady = false;
                                        System.out.println("You have exit from previous command.");
                                    } else {
                                        System.out.println("Try again.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("You have entered wrong type value." + "\nDo you want to exit from command? (yes/no)");
                                    if (br.readLine().equals("yes") || !filename.equals("no file")) {
                                        exit = true;
                                        commandIsReady = false;
                                        System.out.println("You have exit from previous command.");
                                    } else {
                                        System.out.println("Try again.");
                                    }
                                }
                            }
                        }
                        if (commandIsReady) {
                            System.out.println(command.run());
                        }
                    } else if (line[0].equals("exit")) {
                        br.close();
                        System.exit(0);
                    } else if (line[0].equals("execute_script")) {
                        if (line.length != 2) {
                            System.out.println("execute_script have the only argument - filename.");
                        } else {
                            terminal(parser, commandMap, line[1], level + 1);
                        }
                    } else {
                        System.out.println("There is no such command.");
                    }
                }
            } catch (InvalidArgumentsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String[] cleanLine(String[] line) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : line) {
            if (!s.equals("")) {
                result.add(s);
            }
        }
        String[] resultStr = new String[result.size()];
        return result.toArray(resultStr);
    }
}
