package ru.senina.lab5.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.senina.lab5.labwork.LabWork;

import java.util.Scanner;

public abstract class Command {
    private final String name;
    private String[] args;

    public Command(String name) {
        this.name = name;
    }
    public void setArgs(String[] args){
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String getName() {
        return name;
    }

    public String run() throws JsonProcessingException {
        validateArguments();
        return doRun();
    }

    protected abstract String doRun() throws JsonProcessingException;

    public abstract void validateArguments();

}
