package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //TODO: make reading from environment variables
        //TODO: translate all massages
        //filepath = System.getenv("SENINA_PATH");
        String filePath = "C:\\Users\\senin\\Desktop\\ITMO\\Programming\\Programming_5\\src\\main\\resources\\inputData.json";
        Keeper keeper = new Keeper(filePath);
        keeper.terminal();

    }
}
