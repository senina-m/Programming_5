package ru.senina.lab5.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.Parser;
import ru.senina.lab5.labwork.LabWork;

import java.util.List;

public class PrintDescendingCommand extends CommandWithoutArgs {
    private CollectionKeeper collectionKeeper;
    private Parser parser;

    public PrintDescendingCommand(CollectionKeeper collectionKeeper, Parser parser) {
        super("print_descending");
        this.collectionKeeper = collectionKeeper;
        this.parser = parser;
    }

    @Override
    protected String doRun() {
        try {
            List<LabWork> resultElements = collectionKeeper.getSortedList();
            if(resultElements.size() != 0){
                StringBuilder result = new StringBuilder();
                result.append("You entered a command print_descending\":\n");
                for(int i = resultElements.size() - 1; i >= 0; i--){
                    result.append("Element ").append(i + 1).append(": \n").append(parser.fromElementToString(resultElements.get(i))).append("\n");
                }
                return result.toString();
            }else{
                return "There is now elements in collection now.";
            }
        }catch (JsonProcessingException e){
            return "Parsing in print_descending was failed";
        }
    }
}
