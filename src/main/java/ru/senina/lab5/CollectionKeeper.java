package ru.senina.lab5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.senina.lab5.labwork.Discipline;
import ru.senina.lab5.labwork.LabWork;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class to keep collection's elements
 */
public class CollectionKeeper {

    @JsonIgnore
    private final Date time = new Date();
    @JsonIgnore
    private final Comparator comparator = new Comparator();
    private LinkedList<LabWork> list;


    public void setTime(Date time) {
    }

    @JsonIgnore
    public void setMyListType(String myListType) {
    }

    public CollectionKeeper() {

    }

    /**
     * @param list of collection's elements
     */
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

    /**
     * Update element with given ID
     * @param id given ID
     * @param labWork element update value
     * @return String result of method work. If it finished successful
     */
    public String updateID(long id, LabWork labWork){
        for(int i=0; i < list.size(); i++){
           if(list.get(i).getId() == id){
               list.set(i, labWork);
               return "Element with id: " + id + " was successfully updated.";
           }
        }
        return "There is no element with id: " + id + " in collection.";
    }

    /**
     * Add element to collection
     * @param element element to add
     * @return String result of method work. If it finished successful
     */
    public String add(LabWork element) {
        list.add(element);
        return "Element with id: " + element.getId() + " was successfully added.";
    }

    /**
     * Remove element with given id
     * @param id given id
     * @return String result of method work. If it finished successful
     */
    public String removeById(long id) {
        for(int i=0; i < list.size(); i++){
            if(list.get(i).getId() == id){
                list.remove(i);
                return "Element with id: " + id + " was successfully removed.";
            }
        }
        return "There is no element with id: " + id + " in collection.";
    }

    /**
     * Clear collection
     * @return String result of method work. If it finished successful
     */
    public String clear() {
        list.clear();
        return "The collection was successfully cleared.";
    }

    /**
     * Remove element with given index
     * @param index given index
     * @return String result of method work. If it finished successful
     */
    public String removeAt(int index) {
        try {
            long id = list.remove(index).getId();
            return "Element with index " + index + " and id " + id + " was successfully removed.";
        }catch (IndexOutOfBoundsException e){
            return "Removing an element with index " + index + " was failed. No such index in the collection.";
        }
    }

    /**
     * Sort collection
     * @return String result of method work. If it finished successful
     */
    public String sort() {
        list.sort(comparator);
        return "Collection was successfully sort.";
    }

    /**
     * Remove element with grater value of SelfStudyHours of given element
     * @param element given element
     * @return String result of method work. If it finished successful
     */
    public String removeGreater(LabWork element) {
        List<Integer> indexToDelete = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(comparator.compare(list.get(i), element) > 0){
                indexToDelete.add(i);
            }
        }
        for(int i = indexToDelete.size() - 1; i >= 0; i--){
            list.remove((int) indexToDelete.get(i));
        }
        return "All elements greater then entered were successfully removed.";
    }

    /**
     * Sort by difficulty of subject
     * @return String result of method work. If it finished successful
     * @throws IndexOutOfBoundsException if no elements in collection
     */
    public LabWork minByDifficulty() throws IndexOutOfBoundsException{
        try {
            LabWork element = list.get(0);
            for (LabWork labWork : list) {
                if (comparator.compareByDifficulty(element, labWork) > 0) {
                    element = labWork;
                }
            }
            return element;
        }catch (IndexOutOfBoundsException e){
            throw new InvalidArgumentsException("No elements in collection. Can't choose the less by Difficulty.");
        }
    }

    /**
     * Filter by given description
     * @param description given description
     * @return String result of method work. If it finished successful
     */
    public List<LabWork> filterByDescription(String description) {
        List<LabWork> filteredElements = new ArrayList<>();
        for(LabWork element : list){
            if(element.getDescription().equals(description)){
                filteredElements.add(element);
            }
        }
        return filteredElements;
    }

    public List<LabWork> getSortedList() {
        List<LabWork> newList = this.list;
        newList.sort(comparator);
        return newList;
    }

    static class Comparator implements java.util.Comparator<LabWork> {

        @Override
        public int compare(LabWork o1, LabWork o2) {
            Discipline discipline1 = o1.getDiscipline();
            Discipline discipline2 = o2.getDiscipline();
            return discipline1.getSelfStudyHours() - discipline2.getSelfStudyHours();
        }

        public int compareByDifficulty(LabWork o1, LabWork o2) {
            return o1.getDifficulty().getValue() - o2.getDifficulty().getValue();
        }
    }
}
