package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.labwork.LabWork;

/**
 * Command removes all elements greater than given
 */
public class RemoveGreaterCommand extends CommandWithoutArgs implements ElementNeed{
    private final CollectionKeeper collectionKeeper;
    private LabWork element;

    public RemoveGreaterCommand( CollectionKeeper collectionKeeper) {
        super("remove_greater {element}", "remove all items from the collection that are greater than the specified one");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.removeGreater(element);
    }

    @Override
    public void setLabWorkElement(LabWork labWorkElement) {
        this.element = labWorkElement;
    }
}
