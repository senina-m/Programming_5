package ru.senina.lab5.command;

import ru.senina.lab5.labwork.LabWork;

import java.util.Scanner;

public abstract class Command {
    private final String name;
    private String[] args;
    //TODO: Arguments Validator

    public Command(String name) {
        this.name = name;
    }
    public void setArgs(String[] args){
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public abstract String run();

}
