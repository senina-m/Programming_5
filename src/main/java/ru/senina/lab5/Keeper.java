package ru.senina.lab5;

import ru.senina.lab5.commands.*;
import ru.senina.lab5.labwork.Coordinates;
import ru.senina.lab5.labwork.Difficulty;
import ru.senina.lab5.labwork.Discipline;
import ru.senina.lab5.labwork.LabWork;

import java.io.IOException;
import java.util.*;

public class Keeper {
    private final String filename;
    private CollectionKeeper collectionKeeper;
    private String standardOutputFile = "C:\\Users\\senin\\Desktop\\ITMO\\Programming\\Programming_5\\src\\main\\resources\\outputData.json";

    public Keeper(String filename) {
        this.filename = filename;
    }

    public void terminal() throws IOException {
        Parser parser = new Parser();
        try {
            collectionKeeper = parser.fromJsonToCollectionKeeper(parser.fromFileToString(filename));
        } catch (InvalidArgumentsException e) {
            System.out.println(e.getMessage());
        }

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand(collectionKeeper));
        commandMap.put("show", new ShowCommand(collectionKeeper, parser));
        commandMap.put("add", new AddCommand(collectionKeeper));
        commandMap.put("update", new UpdateCommand(collectionKeeper));
        commandMap.put("remove_by_id", new RemoveByIDCommand(collectionKeeper));
        commandMap.put("clear", new ClearCommand(collectionKeeper));
        commandMap.put("save", new SaveCommand(collectionKeeper, parser, standardOutputFile));
//        commandMap.put("execute_script", new Command());
        commandMap.put("remove_at", new RemoveAtCommand(collectionKeeper));
        commandMap.put("remove_greater", new RemoveGreaterCommand(collectionKeeper));
        commandMap.put("sort", new SortCommand(collectionKeeper));
        commandMap.put("min_by_difficulty", new MinByDifficultyCommand(collectionKeeper, parser));
        commandMap.put("filter_by_description", new FilterByDescriptionCommand(collectionKeeper, parser));
        commandMap.put("print_descending", new PrintDescendingCommand(collectionKeeper, parser));

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                //TODO: проверить что contains вернёт то, что надо. Есть сомнения по поводу ссылочности типа String
                String[] line = sc.nextLine().split(" ");
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
                                element.setName(sc.nextLine());

                                System.out.println("Enter coordinates. In first line x <= 74. In second y >= -47.");
                                element.setCoordinates(new Coordinates(Integer.parseInt(sc.nextLine()), Long.parseLong(sc.nextLine())));

                                System.out.println("Enter minimal point.");
                                element.setMinimalPoint(Float.parseFloat(sc.nextLine()));

                                System.out.println("Enter element description.");
                                element.setDescription(sc.nextLine());

                                System.out.println("Enter average point.");
                                element.setAveragePoint(Integer.parseInt(sc.nextLine()));

                                System.out.println("Enter one difficulty of following list:");
                                Difficulty[] difficulties = Difficulty.values();
                                for (Difficulty difficulty : difficulties) {
                                    System.out.print(difficulty.toString() + "; ");
                                }

                                element.setDifficulty(sc.nextLine());

                                System.out.println("Enter discipline parametrs:");
                                Discipline discipline = new Discipline();
                                System.out.println("Enter discipline name.");
                                discipline.setName(sc.nextLine());
                                System.out.println("Enter discipline lectureHours.");
                                discipline.setLectureHours(Long.parseLong(sc.nextLine()));
                                System.out.println("Enter discipline practiceHours.");
                                discipline.setPracticeHours(Integer.parseInt(sc.nextLine()));
                                System.out.println("Enter discipline selfStudyHours.");
                                discipline.setSelfStudyHours(Integer.parseInt(sc.nextLine()));
                                element.setDiscipline(discipline);
                                ((ElementNeed) command).setLabWorkElement(element);
                                commandIsReady = true;
                                exit = true;
                            } catch (InvalidArgumentsException e) {
                                System.out.println("You have entered invalidate value." + e.getMessage() + "\nDo you want to exit from command? (yes/no)");
                                if (sc.nextLine().equals("yes")) {
                                    exit = true;
                                    commandIsReady = false;
                                    System.out.println("You have exit from previous command.");
                                } else {
                                    System.out.println("Try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("You have entered wrong type value." + "\nDo you want to exit from command? (yes/no)");
                                if (sc.nextLine().equals("yes")) {
                                    exit = true;
                                    commandIsReady = false;
                                    System.out.println("You have exit from previous command.");
                                } else {
                                    System.out.println("Try again.");
                                }
                            }
                        }
                    }
                    if(commandIsReady){
                        System.out.println(command.run());
                    }
                }else{
                if (line[0].equals("exit")) {
                    sc.close();
                    System.exit(0);
                }else{
                    System.out.println("There is no such command.");}
                }
            } catch (InvalidArgumentsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
