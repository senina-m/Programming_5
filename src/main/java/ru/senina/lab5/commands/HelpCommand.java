package ru.senina.lab5.commands;

/**
 * Command displays help for available commands
 */
public class HelpCommand extends CommandWithoutArgs {
    public HelpCommand() {
        super("help");
    }

    @Override
    public String doRun() {
        String commands = "help : display help for available commands \n" +
                    "info : print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream\n" +
                    "show : print to standard output all elements of the collection in string representation\n" +
                    "add {element} : add new element to collection\n" +
                    "update id {element} : update the value of the collection element whose id is equal to the given\n" +
                    "remove_by_id id : remove an item from the collection by its id\n" +
                    "clear : clear collection\n" +
                    "save : save collection to file\n" +
                    "execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.\n" +
                    "exit : end the program (without saving to file)\n" +
                    "remove_at index : remove the element at the given collection position (index)\n" +
                    "remove_greater {element} : remove all items from the collection that are greater than the specified one\n" +
                    "sort : sort the collection in natural order\n" +
                    "min_by_difficulty : remove any object from the collection with the minimum difficulty value\n" +
                    "filter_by_description description : display elements whose description field value is equal to the given one\n" +
                    "print_descending : display the elements of the collection in descending order\n";
        //TODO: Подумать не надо ли собирать инфу о всех командах автоматически или нормально оставить её отдельное написание здесь.
        return "You entered help command. The full list of commands is here: \n" + commands;
    }
}
