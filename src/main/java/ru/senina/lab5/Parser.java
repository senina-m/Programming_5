package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.senina.lab5.labwork.LabWork;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
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

    public void writeStringToFile(String filename, String str) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        writer.print("");
        writer.print(str);
        writer.close();
    }
    public String fromCollectionKeeperToJson(CollectionKeeper collectionKeeper) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionKeeper);
    }

    public String fromCollectionKeeperToJsonElements(CollectionKeeper collectionKeeper) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        StringBuilder resultString  = new StringBuilder();
        LinkedList<LabWork> list = collectionKeeper.getList();
        for(int i = 0; i < list.size(); i++){
            resultString.append("\nElement ").append(i + 1).append(":\n").append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list.get(i)));
        }
        return resultString.toString();
    }

    public String fromElementToString(LabWork element) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(element);
    }
}
