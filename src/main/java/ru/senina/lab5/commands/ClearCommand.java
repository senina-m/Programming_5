package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;

/**
 * Command clear collection - delete all elements
 */
public class ClearCommand extends CommandWithoutArgs {
    private final CollectionKeeper collectionKeeper;
    public ClearCommand(CollectionKeeper collectionKeeper) {
        super("clear", "clear collection");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.clear();
    }
}
