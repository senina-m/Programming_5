package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.command.*;
import ru.senina.lab5.labwork.Coordinates;
import ru.senina.lab5.labwork.Difficulty;
import ru.senina.lab5.labwork.Discipline;
import ru.senina.lab5.labwork.LabWork;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Keeper {
    private final String filename;
    private CollectionKeeper collectionKeeper;
    private String standardOutputFile = "C:\\Users\\senin\\Desktop\\ITMO\\Programming\\Programming_5\\src\\main\\resources\\outputData.json";

    public Keeper(String filename) {
        this.filename = filename;
    }

    public void start() throws IOException, InvalidArgumentsException {
        Parser parser = new Parser();
        try {
            collectionKeeper = parser.fromJsonToCollectionKeeper(parser.fromFileToString(filename));
        } catch (InvalidArgumentsException e) {
            System.out.println(e.getMessage());
        }
        terminal();
        parser.fromJsonToFile(standardOutputFile, parser.fromCollectionKeeperToJson(collectionKeeper));
    }

    public void terminal() throws JsonProcessingException {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand());
        commandMap.put("show", new ShowCommand(collectionKeeper));
        commandMap.put("add", new AddCommand(collectionKeeper));
        commandMap.put("update", new UpdateCommand(collectionKeeper));
//        commandMap.put("remove_by_id", new Command());
//        commandMap.put("clear", new Command());
//        commandMap.put("save", new Command());
//        commandMap.put("execute_script", new Command());
//        commandMap.put("exit", new Command());
//        commandMap.put("remove_at", new Command());
//        commandMap.put("remove_greater", new Command());
//        commandMap.put("sort", new Command());
//        commandMap.put("min_by_difficulty", new Command());
//        commandMap.put("print_descending", new Command());

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                //TODO: проверить что contains вернёт то, что надо. Есть сомнения по поводу ссылочности типа String
                String[] line = sc.nextLine().split(" ");
                if (commandMap.containsKey(line[0])) {
                    boolean validCommand = true;
                    Command command = commandMap.get(line[0]);
                    // TODO: кинет ли оно ошибку, если аргументов у меня нет или запишет null?
                    command.setArgs(line);
                    if (command instanceof ElementNeed) {
                        //TODO: можно ли считать что здесь LabWorkElement всегда не null?
                        LabWork labWorkElement = readElement(sc);
                        ((ElementNeed) command).setLabWorkElement(labWorkElement);
                    }
                    System.out.println(command.run());
                }
                if (line[0].equals("exit")) {
                    sc.close();
                    System.exit(0);
                }
            } catch (InvalidArgumentsException e) {
                System.out.println(e.getMessage());
            } catch (ExitException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public LabWork readElement(Scanner sc) throws TryAgainException {
        while (true) {
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

                System.out.println("Enter discipline parametrs.");
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
                return element;
            } catch (InvalidArgumentsException e) {
                System.out.println("You have entered invalidate value." + e.getMessage() + "\nDo you want to exit from command? (yes/no)");
                if (sc.nextLine().equals("yes")) {
                    throw new ExitException("You have exit from previous command.");
                } else {
                    System.out.println("Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered wrong type value." + "\nDo you want to exit from command? (yes/no)");
                if (sc.nextLine().equals("yes")) {
                    throw new ExitException("You have exit from previous command.");
                } else {
                    System.out.println("Try again.");
                }
            }
        }
    }
}
