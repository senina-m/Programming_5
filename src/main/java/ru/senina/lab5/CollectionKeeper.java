package ru.senina.lab5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.senina.lab5.labwork.LabWork;
import ru.senina.lab5.command.Command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class CollectionKeeper {

    @JsonIgnore
    private final Date time = new Date();
    private LinkedList<LabWork> list;


    public void setTime(Date time) {
    }

    @JsonIgnore
    public void setMyListType(String myListType) {
    }

    public CollectionKeeper() {

    }
    public CollectionKeeper(LinkedList<LabWork> list) {
        this.list = list;
    }

    public LinkedList<LabWork> getList() {
        return list;
    }

    public void setList(LinkedList<LabWork> list) {
        this.list = list;
    }

    @JsonIgnore
    public String getType(){
        return "LinkedList";
    }

    @JsonIgnore
    public int getAmountOfElements() {
        return list.size();
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        return dateFormat.format(time);
    }

    public String runCommand(Command command){
        return "Здесь надо дописать функцию для всех команд";
    }

    public String updateID(int id, LabWork element){
        try{
            list.set(id, element);
            return "Элемент с индексом " + id + " успешно обновлён.";
        }
        catch (IndexOutOfBoundsException e){
            return "Вы ввели неверный id";
        }
    }
}
