package ru.senina.lab5.command;

import ru.senina.lab5.CollectionKeeper;

public class InfoCommand extends CommandWithoutArgs {
    CollectionKeeper collectionKeeper;

    public InfoCommand() {
        super("info");
    }

    public CollectionKeeper getCollectionKeeper() {
        return collectionKeeper;
    }

    public void setCollectionKeeper(CollectionKeeper collectionKeeper) {
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return "You have entered info command.\n" +
                "This collection was created: " + collectionKeeper.getTime() + "\n"
                + "Collection type: " + collectionKeeper.getType() + "\n"
                + "Amount of collection's elements: " + collectionKeeper.getAmountOfElements() + "\n";
    }
}
