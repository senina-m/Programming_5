package ru.senina.lab5.command;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.InvalidArgumentsException;
import ru.senina.lab5.labwork.LabWork;

import java.util.Arrays;

public class UpdateCommand extends Command implements ElementNeed {
    final CollectionKeeper collectionKeeper;
    private LabWork element;
    private long id;

    public UpdateCommand(CollectionKeeper collectionKeeper) {
        super("update");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun() {
        return collectionKeeper.updateID(id, element);
    }

    @Override
    public void validateArguments() {
        String[] args = getArgs();
        if(args.length == 2){
            try{
                this.id = Long.parseLong(args[1]);
            }
            catch (NumberFormatException e){
                throw new InvalidArgumentsException("Update command argument has to be long.");
            }
        }else {
            throw new InvalidArgumentsException("Update command has to have an argument - id of the element." + " " + Arrays.toString(args));
        }
    }

    @Override
    public void setLabWorkElement(LabWork labWorkElement) {
        this.element = labWorkElement;
    }
}
