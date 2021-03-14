package ru.senina.lab5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.senina.lab5.labwork.LabWork;

import java.io.*;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Class that works with json and output
 */
public class Parser {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public Parser() {
    }

    /**@param json json string
     * @return CollectionKeeper instance with fields serialized from json
     * @throws JsonProcessingException if something got wrong with json
     */
    public CollectionKeeper fromJsonToCollectionKeeper(String json) throws JsonProcessingException, InvalidArgumentsException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        CollectionKeeper result = objectMapper.readValue(json, CollectionKeeper.class);
        if(result.getList() != null){
            return result;
        }else{
            System.out.println("The file contents isn't valid. The collection will be empty.");
            return new CollectionKeeper();
        }
    }

    /**
     * @param path of the file from which json would be read
     * @return json string from given file
     * @throws IOException If there is no such file
     */
    public String fromFileToString(String path) throws IOException {
            StringBuilder resultString = new StringBuilder();
            File f = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line = br.readLine();
            while (line != null) {
                resultString.append(line);
                line = br.readLine();
            }
            br.close();
            return resultString.toString();
    }

    /**
     * Method to write json string to file
     * @param filename the path to which file value have be writen
     * @param str the json string
     * @throws IOException if there is no such file
     */
    public void writeStringToFile(String filename, String str) throws IOException{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            writer.write("");
            writer.write(str);
            writer.close();
    }

    /**
     * Method that parses CollectionKeeper Object to json string
     * @param collectionKeeper Object to parse
     * @return json string
     * @throws JsonProcessingException if there were some problems with JACKSON library
     */
    public String fromCollectionKeeperToJson(CollectionKeeper collectionKeeper) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionKeeper);
    }

    /**
     * Method to parse CollectionKeeper elements Objects to json string
     * @param collectionKeeper Object whose elements we need to parse
     * @return json string
     * @throws JsonProcessingException if there were some problems with JACKSON library
     */
    public String fromCollectionKeeperToJsonElements(CollectionKeeper collectionKeeper) throws
            JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        StringBuilder resultString = new StringBuilder();
        LinkedList<LabWork> list = collectionKeeper.getList();
        for (int i = 0; i < list.size(); i++) {
            resultString.append("\nElement ").append(i + 1).append(":\n").append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list.get(i)));
        }
        return resultString.toString();
    }

    /**
     * Method to parse given LabWork element to json string
     * @param element LabWork object
     * @return json string
     * @throws JsonProcessingException if there were some problems with JACKSON library
     */
    public String fromElementToString(LabWork element) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(element);
    }
}
