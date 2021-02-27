package ru.senina.lab5.labwork;


import ru.senina.lab5.InvalidArgumentsException;

import java.time.LocalDateTime;
import java.util.Objects;

public class LabWork {
    private final java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();
    ; //Поле не может быть null, Значение этого поля должно генерироваться автоматически https://javadevblog.com/polnoe-rukovodstvo-po-java-8-date-time-api-primery-localdate-instant-localdatetime-parse-i-format.html
    private Long id = (long) Objects.hash(creationDate); //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private float minimalPoint; //Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private Integer averagePoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле не может быть null

    public LabWork() {
    }

    public LabWork(String name, Coordinates coordinates, float minimalPoint, String description, Integer averagePoint, Difficulty difficulty, Discipline discipline) {
        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    //TODO: validation methods for each field

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return Float.compare(labWork.minimalPoint, minimalPoint) == 0 && id.equals(labWork.id) && name.equals(labWork.name) && coordinates.equals(labWork.coordinates) && description.equals(labWork.description) && averagePoint.equals(labWork.averagePoint) && difficulty == labWork.difficulty && discipline.equals(labWork.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, minimalPoint, description, averagePoint, difficulty, discipline);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCreationDate(LocalDateTime date) {
    }

    public float getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(float minimalPoint) {
        if(minimalPoint > 0){
            this.minimalPoint = minimalPoint;
        }else {
            throw new InvalidArgumentsException("Minimal point can't be less then 0.");
        }
    }

    public void setCoordinates(Coordinates coordinates) throws InvalidArgumentsException {
        if(coordinates.getX() <= 74 && coordinates.getY() >= -47){
            this.coordinates = coordinates;
        }else {
            throw new InvalidArgumentsException("Coordinates value is wrong.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidArgumentsException {
        if (description != null) {
            this.description = description;
        } else {
            throw new InvalidArgumentsException("Description can't be null.");
        }
    }

    public Integer getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(Integer averagePoint) {
        if (averagePoint != null && averagePoint > 0) {
            this.description = description;
        } else {
            throw new InvalidArgumentsException("Average point can't be null or less then 0.");
        }
        this.averagePoint = averagePoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

}