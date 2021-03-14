package ru.senina.lab5.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.Parser;

/**
 * Command shows all collection elements
 */
public class ShowCommand extends CommandWithoutArgs {

    private final CollectionKeeper collectionKeeper;
    private final Parser parser;

    public ShowCommand(CollectionKeeper collectionKeeper, Parser parser) {
        super("show", "print to standard output all elements of the collection in string representation");
        this.collectionKeeper =  collectionKeeper;
        this.parser = parser;
    }

    @Override
    protected String doRun(){
        try {
            if(collectionKeeper.getAmountOfElements()!= 0) {
                parser.fromCollectionKeeperToJsonElements(collectionKeeper);
                return parser.fromCollectionKeeperToJsonElements(collectionKeeper);
            } else {
                return "No elements in collection.";
            }
        }
        catch (JsonProcessingException e){
            return "Parsing was failed.";
        }
    }
}
