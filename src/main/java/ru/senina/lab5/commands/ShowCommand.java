package ru.senina.lab5.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.Parser;

public class ShowCommand extends CommandWithoutArgs {

    private CollectionKeeper collectionKeeper;
    private Parser parser;

    public ShowCommand(CollectionKeeper collectionKeeper, Parser parser) {
        super("show");
        this.collectionKeeper =  collectionKeeper;
        this.parser = parser;
    }

    @Override
    protected String doRun(){
        try {
            System.out.println("You have entered show command.");
            parser.fromCollectionKeeperToJsonElements(collectionKeeper);
            return parser.fromCollectionKeeperToJsonElements(collectionKeeper);
        }
        catch (JsonProcessingException e){
            return "Parsing was failed.";
        }
    }
}
