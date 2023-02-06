package com.example.astarproject;

public class Node {
    private City current;
    private boolean known;
    private Double distance;
    private City source;

    public Node(City current, boolean known, Double dist, City source) {
        super();
        this.current = current;
        this.known = known;
        this.distance = dist;
        this.source = source;
    }
    public City getCurrent() {
        return current;
    }
    public void setCurrent(City current) {
        this.current = current;
    }
    public boolean isKnown() {
        return known;
    }
    public void setKnown(boolean known) {
        this.known = known;
    }

    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public City getSource() {
        return source;
    }
    public void setSource(City source) {
        this.source = source;
    }
}

