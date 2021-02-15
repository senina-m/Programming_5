package ru.senina.lab5.labwork;

import java.util.Objects;

public class Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long lectureHours;
    private Integer practiceHours; //Поле может быть null
    private int selfStudyHours;

    public Discipline() {
    }

    public Discipline(String name, long lectureHours, Integer practiceHours, int selfStudyHours) {
        this.name = name;
        this.lectureHours = lectureHours;
        this.practiceHours = practiceHours;
        this.selfStudyHours = selfStudyHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return lectureHours == that.lectureHours && selfStudyHours == that.selfStudyHours && name.equals(that.name) && practiceHours.equals(that.practiceHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lectureHours, practiceHours, selfStudyHours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(long lectureHours) {
        this.lectureHours = lectureHours;
    }

    public Integer getPracticeHours() {
        return practiceHours;
    }

    public void setPracticeHours(Integer practiceHours) {
        this.practiceHours = practiceHours;
    }

    public int getSelfStudyHours() {
        return selfStudyHours;
    }

    public void setSelfStudyHours(int selfStudyHours) {
        this.selfStudyHours = selfStudyHours;
    }
}