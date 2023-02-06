package com.example.astarproject;

public class Neighbor {
    private City source;
    private City dist;
    private Double road;
    public Neighbor() {
        // TODO Auto-generated constructor stub
    }

    public Neighbor(City source, City dist, Double road) {
        super();
        this.source = source;
        this.dist = dist;
        this.road = road;
    }

    public City getSource() {
        return source;
    }
    public void setSource(City source) {
        this.source = source;
    }
    public City getDist() {
        return dist;
    }
    public void setDist(City dist) {
        this.dist = dist;
    }
    public Double getRoad() {
        return road;
    }
    public void setRoad(Double road) {
        this.road = road;
    }

    @Override
    public String toString() {
        return "Neighbor{" +
                "source=" + source +
                ", dist=" + dist +
                ", road=" + road +
                '}';
    }
}