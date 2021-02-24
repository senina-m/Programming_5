package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.command.*;
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
        String next = sc.nextLine();
        while (true) {
            //TODO: проверить что contains вернёт то, что надо. Есть сомнения по поводу ссылочности типа String
            if(commandMap.containsKey(next.split(" ")[0])){
                Command command = commandMap.get(next.split(" ")[0]);
                command.setArgs(Arrays.copyOfRange(next.split(" "), 1, next.length()));
                if (command instanceof ElementNeed) {
                    LabWork labWorkElement = readElement(sc);
                    try{
                        ((ElementNeed) command).setLabWorkElement(labWorkElement);
                    }
                    catch (TryAgainException e){
                        //TODO: написать тут что-то
                    }
                }
                System.out.println(collectionKeeper.runCommand(command));
            }

            if(!next.equals("exit")){
                sc.close();
                System.exit(0);
            }
            next = sc.next();
        }
    }

        public LabWork readElement(Scanner sc){
        LabWork element = new LabWork();
        System.out.println("Вы вызвали команду требующую ввода элемента коллекции типа LabWork. \n Введите id");
        try{
            element.setId(Long.parseLong(sc.next()));
        }
        catch (NumberFormatException e){
            throw new TryAgainException("Вы ввели не число. Для повторного ввода введите команду update заново :)");
        }
        //TODO: Доделать этот метод
        return new LabWork();
    }

    //"list" : [ {
    //"id" : 1111,
    //"name" : "element",
    //"coordinates" : {
    //"x" : 2,
    //"y" : 3
    //},
    //"creationDate" : "2021-02-14T22:06:09.2031526",
    //"minimalPoint" : 80.0,
    //"description" : "my lovely Hori",
    //"averagePoint" : 9,
    //"difficulty" : "HOPELESS",
    //"discipline" : {
    //"name" : "Killing",
    //"lectureHours" : 35,
    //"practiceHours" : 65,
    //"selfStudyHours" : 26
    //}
    //} ]
}
