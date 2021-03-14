package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;

/**
 * Command sorts collection
 */
public class SortCommand extends CommandWithoutArgs {
    private final CollectionKeeper collectionKeeper;

    public SortCommand(CollectionKeeper collectionKeeper) {
        super("sort", "sort the collection in natural order");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.sort();
    }
}
