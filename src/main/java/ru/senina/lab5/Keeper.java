package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.labwork.LabWork;

import java.io.FileNotFoundException;
import java.util.*;

public class Keeper {
    private final String filename;
    private CollectionKeeper collectionKeeper;
    private String standardOutputFile = "C:\\Users\\senin\\Desktop\\ITMO\\Programming\\Programming_5\\src\\main\\resources\\outputData.json";

    public Keeper(String filename) {
        this.filename = filename;
    }

    public void start() throws FileNotFoundException, JsonProcessingException {
        Parser parser = new Parser();
        collectionKeeper = parser.fromJsonToCollectionKeeper(parser.fromFileToString(filename));
        terminal();
        parser.fromJsonToFile(standardOutputFile, parser.fromCollectionKeeperToJson(collectionKeeper));
    }

    public void terminal() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("info", new HelpCommand("help"));
        commandMap.put("show", new ShowCommand("show"));
//        commandMap.put("add", new Command());
        commandMap.put("update", new UpdateCommand("update", collectionKeeper));
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
        List<String> allCommandsList = Arrays.asList("help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script", "exit", "remove_at", "remove_greater", "sort", "min_by_difficulty", "print_descending");
        Set<String> allCommands = new HashSet<>(allCommandsList);
        List<String> elementCommandsList = Arrays.asList("add", "update", "remove_greater");
        Set<String> elementCommands = new HashSet<>(elementCommandsList);
        List<String> stringParamCommandsList = Arrays.asList("update", "remove_by_id", "execute_script", "remove_at", "min_by_difficulty");
        Set<String> stringParamCommands = new HashSet<>(stringParamCommandsList);

        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        while (!next.equals("exit")) {
            if(commandMap.containsKey(next)){
                Command command = commandMap.get(next);
                //функция от команды, которая кладёт в неё нужные аргументы
                command.setIn(sc);

//                if(stringParamCommands.contains(command.getName())){
//                    command.setStringParam();
//                }
//                if (elementCommands.contains(command.getName())) {
//                    LabWork labWorkElement = readElement(sc);
//                    command.setLabWorkElement(labWorkElement);
//                }
//                System.out.println(collectionKeeper.runCommand(command));
            }
            String[] potentialCommand = next.split(" ",2);
//            //TODO: проверить что contains вернёт то, что надо. Есть сомнения по поводу ссылочности типа String
            next = sc.next();
        }
        sc.close();
    }

    public LabWork readElement(Scanner sc){
        //TODO: Написать метод читающий и запрашивающий элемент LabWork
        return new LabWork();
    }
}
