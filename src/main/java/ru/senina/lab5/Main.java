package ru.senina.lab5;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Keeper keeper = new Keeper(System.getenv("SENINA"));
        keeper.start();

    }
}
