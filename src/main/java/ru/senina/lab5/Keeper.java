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
        List<String> allCommandsList = Arrays.asList("help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script", "exit", "remove_at", "remove_greater", "sort", "min_by_difficulty", "print_descending");
        Set<String> allCommands = new HashSet<>(allCommandsList);
        List<String> elementCommandsList = Arrays.asList("add", "update", "remove_greater");
        Set<String> elementCommands = new HashSet<>(elementCommandsList);
        List<String> stringParamCommandsList = Arrays.asList("update", "remove_by_id", "execute_script", "remove_at", "min_by_difficulty");
        Set<String> stringParamCommands = new HashSet<>(stringParamCommandsList);

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        while (!nextLine.equals("exit")) {
            String[] potentialCommand = nextLine.split(" ",2);
            //TODO: проверить что contains вернёт то, что надо. Есть сомнения по поводу ссылочности типа String
            if (allCommands.contains(potentialCommand[0])) {
                Command command = new Command(potentialCommand[0]); //тут записала команде имя
                if(stringParamCommands.contains(command.getName())){
                    command.setStringParam(potentialCommand[0]);
                }
                if (elementCommands.contains(command.getName())) {
                    LabWork labWorkElement = readElement(sc);
                    command.setLabWorkElement(labWorkElement);
                }
                System.out.println(collectionKeeper.runCommand(command));

            }else{
                System.out.println("Вы ввели что-то непонятное");
            }
            nextLine = sc.next();
        }
        sc.close();
    }

    public LabWork readElement(Scanner sc){
        //TODO: Написать метод читающий и запрашивающий элемент LabWork
        return new LabWork();
    }
}
