package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;

/**
 * Command prints information about the collection (type, initialization date, number of elements, etc.) to the standard output stream
 */
public class InfoCommand extends CommandWithoutArgs {
    CollectionKeeper collectionKeeper;

    public InfoCommand(CollectionKeeper collectionKeeper) {
        super("info");
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
