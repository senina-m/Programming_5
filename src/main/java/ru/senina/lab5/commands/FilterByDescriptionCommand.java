package ru.senina.lab5.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.InvalidArgumentsException;
import ru.senina.lab5.Parser;
import ru.senina.lab5.labwork.LabWork;

import java.util.List;

/**
 * Command to find all elements in collection with given description
 */
public class FilterByDescriptionCommand extends Command {
    private final CollectionKeeper collectionKeeper;
    private String description;
    private final Parser parser;

    public FilterByDescriptionCommand(CollectionKeeper collectionKeeper, Parser parser) {
        super("filter_by_description description", "display elements whose description field value is equal to the given one");
        this.collectionKeeper = collectionKeeper;
        this.parser = parser;
    }

    @Override
    protected String doRun() {
        try {
            List<LabWork> resultElements = collectionKeeper.filterByDescription(description);
            if(resultElements.size() != 0){
                StringBuilder result = new StringBuilder();
                result.append("You entered a command filter_by_description. These are the elements with description \"").append(description).append("\":\n");
                for(int i = 0; i < resultElements.size(); i++){
                    result.append("Element ").append(i + 1).append(": \n").append(parser.fromElementToString(resultElements.get(i))).append("\n");
                }
                return result.toString();
            }else{
                return "There is now elements with description \"" + description + "\".";
            }
        }catch (JsonProcessingException e){
            return "Parsing in filter_by_description was failed";
        }
    }

    @Override
    public void validateArguments() {
        String[] args = getArgs();
        if(args.length >= 2){
            StringBuilder description = new StringBuilder();
            description.append("");
            for(int i = 1; i < args.length; i++){
                description.append(args[i]);
                if(i != args.length - 1){
                    description.append(" ");
                }
            }
            this.description = description.toString();
        }else {
            throw new InvalidArgumentsException("Command filter_by_description has to have a String argument.");
        }
    }
}
