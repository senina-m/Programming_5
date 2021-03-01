package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.labwork.LabWork;

public class AddCommand extends CommandWithoutArgs implements ElementNeed{
    CollectionKeeper collectionKeeper;
    private LabWork element;

    public AddCommand(CollectionKeeper collectionKeeper) {
        super("add");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    public void setLabWorkElement(LabWork labWorkElement) {
        this.element = labWorkElement;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.add(element);
    }

}
