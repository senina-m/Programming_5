package ru.senina.lab5.commands;

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

    public String run(){
        validateArguments();
        return doRun();
    }

    protected abstract String doRun();

    public abstract void validateArguments();

}
