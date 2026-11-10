package org.example;

public class Cel {
    private final String name;
    private final String x;
    private final String y;
    private final String h;

    public Cel(String name, String x, String y, String h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.h = h;

    }

    public String getName() {
        return name;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getH() {
        return h;
    }

    @Override
    public String toString() {
        return "Cel" +
                " name = " + name +
                ", x = " + x +
                ", y = " + y +
                ", h = " + h;
    }
}
