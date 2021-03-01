package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.InvalidArgumentsException;

public class RemoveAtCommand extends Command {
    private CollectionKeeper collectionKeeper;
    private int index;

    public RemoveAtCommand(CollectionKeeper collectionKeeper) {
        super("remove_at");
    }

    @Override
    protected String doRun() {
        return collectionKeeper.removeAt(index);
    }

    @Override
    public void validateArguments() {
        String[] args = getArgs();
        if (args.length == 2) {
            try {
                this.index = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentsException("Remove_at command argument has to be Integer.");
            }
        } else {
            throw new InvalidArgumentsException("Update command has to have an argument - index of the element.");
        }
    }
}
