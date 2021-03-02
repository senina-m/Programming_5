package ru.senina.lab5.labwork;

/**
 * Description how difficult is something
 * @see ru.senina.lab5.labwork.LabWork
 */
public enum Difficulty {
    VERY_EASY(0),
    NORMAL(1),
    VERY_HARD(2),
    INSANE(3),
    HOPELESS(4);

    private int value;

    Difficulty(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}