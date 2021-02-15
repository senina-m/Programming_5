package ru.senina.lab5.labwork;

import java.util.Objects;

public class Coordinates {
    public Coordinates(int x, long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    private int x; //Максимальное значение поля: 74
    private long y; //Значение поля должно быть больше -47
}
