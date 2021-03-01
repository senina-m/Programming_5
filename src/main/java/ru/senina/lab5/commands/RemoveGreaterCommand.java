package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.labwork.LabWork;

public class RemoveGreaterCommand extends CommandWithoutArgs implements ElementNeed{
    private CollectionKeeper collectionKeeper;
    private LabWork element;

    public RemoveGreaterCommand( CollectionKeeper collectionKeeper) {
        super("remove_greater");
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
