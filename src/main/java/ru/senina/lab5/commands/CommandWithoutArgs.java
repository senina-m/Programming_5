package ru.senina.lab5.commands;

import ru.senina.lab5.InvalidArgumentsException;

public abstract class CommandWithoutArgs extends Command{
    public CommandWithoutArgs(String name) {
        super(name);
    }

    @Override
    public void validateArguments() {
        if(this.getArgs().length > 1){
            throw new InvalidArgumentsException("This command doesn't have any arguments.");
        }
    }
}