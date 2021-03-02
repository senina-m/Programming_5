package ru.senina.lab5.commands;

/**
 * Parent of all commands
 */
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

    public String run(){
        validateArguments();
        return doRun();
    }

    /**
     * Command execute method
     * @return value to print in output like the result of command execute
     */
    protected abstract String doRun();

    /**
     * Arguments validation method
     */
    public abstract void validateArguments();

}
