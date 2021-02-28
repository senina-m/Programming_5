package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //TODO: make reading from environment variables
        //TODO: translate all massages
        //filepath = System.getenv("SENINA_PATH");
        String filePath = "C:\\Users\\senin\\Desktop\\ITMO\\Programming\\Programming_5\\src\\main\\resources\\inputData.json";
        Keeper keeper = new Keeper(filePath);
        keeper.start();
//        Scanner sc = new Scanner(System.in);
//        System.out.println(Arrays.toString(sc.nextLine().split(" \n")));

    }
}
