package ru.senina.lab5.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.Parser;

public class ShowCommand extends CommandWithoutArgs {

    private CollectionKeeper collectionKeeper;
    private Parser parser = new Parser();

    public ShowCommand(CollectionKeeper collectionKeeper) {
        super("show");
        this.collectionKeeper =  collectionKeeper;
    }

    public CollectionKeeper getCollectionKeeper() {
        return collectionKeeper;
    }

    public void setCollectionKeeper(CollectionKeeper collectionKeeper) {
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() throws JsonProcessingException {
        System.out.println("You have entered show command.");
        Parser parser = new Parser();
        parser.fromCollectionKeeperElementsToJson(collectionKeeper);
        return parser.fromCollectionKeeperElementsToJson(collectionKeeper);
    }
}
