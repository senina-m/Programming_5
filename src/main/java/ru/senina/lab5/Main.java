package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, JsonProcessingException {
        //TODO: make reading from environment variables
        //TODO: translate all massages
        //filepath = System.getenv("SENINA_PATH");
        String filePath = "C:\\Users\\senin\\Desktop\\ITMO\\Programming\\Programming_5Lab\\src\\main\\resources\\inputData.json";
        Keeper keeper = new Keeper(filePath);
        keeper.start();
//        String[] commands = new String[]{"help : вывести справку по доступным командам",
//                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
//                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении",
//                    "add {element} : добавить новый элемент в коллекцию",
//                    "update id {element} : обновить значение элемента коллекции, id которого равен заданному",
//                    "remove_by_id id : удалить элемент из коллекции по его id",
//                    "clear : очистить коллекцию",
//                    "save : сохранить коллекцию в файл",
//                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
//                    "exit : завершить программу (без сохранения в файл)",
//                    "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)",
//                    "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный",
//                    "sort : отсортировать коллекцию в естественном порядке",
//                    "min_by_difficulty : вывести любой объект из коллекции, значение поля difficulty которого является минимальным",
//                    "filter_by_description description : вывести элементы, значение поля description которых равно заданному",
//                    "print_descending : вывести элементы коллекции в порядке убывания"};
            //Обратобать команды
            // info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
            //show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
            //add {element} : добавить новый элемент в коллекцию
            //update id {element} : обновить значение элемента коллекции, id которого равен заданному
            //remove_by_id id : удалить элемент из коллекции по его id
            //clear : очистить коллекцию
            //save : сохранить коллекцию в файл
            //execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
            //
            //remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
            //remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
            //sort : отсортировать коллекцию в естественном порядке
            //min_by_difficulty : вывести любой объект из коллекции, значение поля difficulty которого является минимальным
            //filter_by_description description : вывести элементы, значение поля description которых равно заданному
            //print_descending : вывести элементы коллекции в порядке убывания
    }
}
