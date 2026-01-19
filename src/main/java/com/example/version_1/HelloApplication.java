package com.example.version_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        NavBar navBar = new NavBar();
        root.setTop(navBar);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Semester Planner");
        stage.setScene(scene);
        stage.show();
    }
}
