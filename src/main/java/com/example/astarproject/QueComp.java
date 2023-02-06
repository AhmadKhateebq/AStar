package com.example.astarproject;

import java.util.Comparator;

public class QueComp implements Comparator<Node>{

    public QueComp() {
    }
    public double getRoad(double x1, double y1, double x2, double y2) {
        double first = x1-x2;
        double second = y1-y2;
        return Math.sqrt(Math.pow(first, 2) + Math.pow(second, 2));

    }

    @Override
    public int compare(Node o1, Node o2) {
        double x1 = o1.getCurrent().getCoordinateX();
        double y1 = o1.getCurrent().getCoordinateY();
        double x2 = o2.getCurrent().getCoordinateX();
        double y2 = o2.getCurrent().getCoordinateY();
        double airDist = getRoad(x1,y1,x2,y2);
        double realDist = o1.getDistance() - o2.getDistance();
        return (int)(airDist+realDist);
    }

}
