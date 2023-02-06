package com.example.astarproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    public static Scene scene;
    public static Pane root;
    public static Stage mainStage ;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("StageFXML.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, 507.0,907.0);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}