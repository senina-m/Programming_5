package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Parser {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public Parser() {
    }

    public CollectionKeeper fromJsonToCollectionKeeper(String json) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.readValue(json, CollectionKeeper.class);
    }

    public String fromFileToString(String path) throws FileNotFoundException {
        StringBuilder resultString = new StringBuilder();
        FileReader reader = new FileReader(new File(path));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()){
            resultString.append(scanner.nextLine());
        }
        scanner.close();
        return resultString.toString();
    }

    public void fromJsonToFile(String filename, String json){}
    public String fromCollectionKeeperToJson(CollectionKeeper collectionKeeper){
        return "Пока так чтобы не ругулось";
    }

}
