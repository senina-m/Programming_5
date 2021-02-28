package ru.senina.lab5.command;

import ru.senina.lab5.InvalidArgumentsException;

import java.util.Arrays;

public abstract class CommandWithoutArgs extends Command{
    public CommandWithoutArgs(String name) {
        super(name);
    }

    @Override
    public void validateArguments() {
        if(this.getArgs().length > 1){
            throw new InvalidArgumentsException("This command doesn't have any arguments." + Arrays.toString(getArgs()));
        }
    }
}