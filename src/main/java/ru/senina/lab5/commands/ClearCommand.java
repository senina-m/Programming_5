package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;

public class ClearCommand extends CommandWithoutArgs {
    private CollectionKeeper collectionKeeper;
    public ClearCommand(CollectionKeeper collectionKeeper) {
        super("clear");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.clear();
    }
}
