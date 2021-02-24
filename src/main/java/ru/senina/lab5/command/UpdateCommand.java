package ru.senina.lab5.command;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.labwork.LabWork;

public class UpdateCommand extends Command implements ElementNeed {
    private CollectionKeeper collectionKeeper;
    private LabWork element;
    private int id = 1; //TODO: убрать единицу

    public UpdateCommand(String name, CollectionKeeper collectionKeeper) {
        super(name);
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    public String run() {
        //TODO: метод проверяющий, что у команды есть все необходимые аргументы и нет лишних
       return collectionKeeper.updateID(id, element);
    }

    @Override
    public void setLabWorkElement(LabWork labWorkElement) {
        this.element = element;
    }
}
