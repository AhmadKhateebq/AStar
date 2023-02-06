package com.example.astarproject;


import java.util.ArrayList;

public class City {

    private double coordinateX;
    private double coordinateY;
    private String name;
    private ArrayList<Neighbor> adjacents = new ArrayList<>();;

    public City(double coordinateX, double coordinateY, String name) {
        super();
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.name = name;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public ArrayList<Neighbor> getAdjacent() {
        return adjacents;
    }

    public void setAdjacent(ArrayList<Neighbor> adjacent) {
        this.adjacents = adjacent;
    }

    @Override
    public String toString() {
        return"City : " + name + " -- (X : " + coordinateX + " * Y : " + coordinateY + ")";
    }

    public String fullToString() {
        return "City [coordinateX=" + coordinateX + ", coordinateY=" + coordinateY + ", name=" + name + ", adjacent=" + adjacents + "]";

    }
}
