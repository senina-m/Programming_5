package ru.senina.lab5.commands;

import ru.senina.lab5.CollectionKeeper;
import ru.senina.lab5.InvalidArgumentsException;

public class RemoveByIDCommand extends Command {
    private CollectionKeeper collectionKeeper;
    private long id;

    public RemoveByIDCommand(CollectionKeeper collectionKeeper) {
        super("remove_by_id");
        this.collectionKeeper = collectionKeeper;
    }

    @Override
    protected String doRun(){
        return collectionKeeper.removeById(id);
    }

    @Override
    public void validateArguments() {
        String[] args = this.getArgs();
        if(args.length == 2){
            this.id = Long.parseLong(args[1]);
        }else {
            throw new InvalidArgumentsException("Remove_by_id command has the only argument - id.");
        }
    }
}
