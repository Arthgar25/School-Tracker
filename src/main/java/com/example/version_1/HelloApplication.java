package com.example.version_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        NavBar navBar = new NavBar();

        navBar.setOnAddCourse(() -> {
            System.out.println("Open Add Course dialog");
        });

        navBar.setOnAddAssignment(() -> {
            System.out.println("Open Add Assignment dialog");
        });

        navBar.setOnClearList(() -> {
            System.out.println("Open Clear List dialog");
        });

        root.setTop(navBar);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Semester Planner");
        stage.setScene(scene);
        stage.show();
    }
}
