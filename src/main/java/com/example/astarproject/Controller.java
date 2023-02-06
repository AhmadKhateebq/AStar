package com.example.astarproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller implements Initializable {
    @FXML
    ImageView iv;
    static Boolean set = false;
    public static String city1;
    public static String city2;
    public static double distance;
    File cityFile;
    File adjFile;
    @FXML
    Button b;
    PathFinder pf = new PathFinder();
    static LinkedList<Line> ll = new LinkedList<Line>();
    static Stack<String> path = new Stack<String>();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cityFile = PathFinder.towns;
        adjFile = PathFinder.roads;
        b.setOnAction(e -> {
            FileChooser fw = new FileChooser();
            fw.setTitle("Select Cities File :");
//            cityFile = fw.showOpenDialog(GUI.mainStage);

            if (pf.readCities(cityFile)) {
                b.setVisible(false);
            } else
                new Alert(Alert.AlertType.ERROR, "FILES ERROR ,TRY AGAIN").show();
        });
    }

    public boolean findPath() {
        City c1 = pf.getCity(city1);
        City c2 = pf.getCity(city2);
        pf.Compute(c1, c2);
        Node temp = pf.getNode(city2);
        double d = temp.getDistance() * 100;
        d = ((int) d) / 100.0;
        boolean reached = false;
        try {
            path.clear();
            removeLines();
            while (!reached) {
                if (temp.getCurrent().getName().equals(c1.getName())) {
                    path.add(temp.getCurrent().getName());
                    reached = true;
                }
                else {
                    path.add(temp.getCurrent().getName());
                    double x1 = temp.getCurrent().getCoordinateX();
                    double y1 = temp.getCurrent().getCoordinateY();
                    double x2 = temp.getSource().getCoordinateX();
                    double y2 = temp.getSource().getCoordinateY();
                    ll.add(linkCreater(x1, y1, x2, y2));
                    temp = pf.getNode(temp.getSource().getName());
                }
            }
        } catch (NullPointerException e1) {
            new Alert(Alert.AlertType.ERROR, "NO PATH FOUND!").show();
            return false;
        }
        distance = d;
        city1 = "";
        city2 = "";
        return true;
    }

    public void CityCreator(double x, double y, String name) {
        Label city = new Label(name);
        city.setLayoutX(x - name.length());
        city.setLayoutY(y - 10);
        city.setOnMouseClicked(e -> {
            setCities(name);
        });
        GUI.root.getChildren().add(city);
    }

    public Line linkCreater(double x1, double y1, double x2, double y2) {
        Line l = new Line(x1, y1, x2, y2);
        l.setStyle("-fx-stroke-width: 3px;");
        GUI.root.getChildren().add(l);
        return l;
    }

    public void removeLines() {
        while(!ll.isEmpty()) {
            GUI.root.getChildren().remove(ll.pop());
        }
    }

    public void setCities(String name) {
        if (set) {
            city2 = name;
            set = false;
            if (findPath()) {
                StringBuilder s = new StringBuilder();
                while(!path.isEmpty()) {
                    s.append(path.pop()+"\n");
                }
                String distHours;
                int hours = 0;
                while (distance>=60){
                    hours+=1;
                    distance-=60;
                }
                distHours = "Time = "+hours+"H:"+(int)distance+"m ";

                new Alert(Alert.AlertType.INFORMATION, distHours+"\n PATH : \n"+s).show();
            }

        } else {
            city1 = name;
            set = true;
        }
    }
}