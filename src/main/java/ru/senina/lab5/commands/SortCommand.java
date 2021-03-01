package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;

public class SortCommand extends CommandWithoutArgs {
    private  CollectionKeeper collectionKeeper;

    public SortCommand(CollectionKeeper collectionKeeper) {
        super("sort");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.sort();
    }
}
