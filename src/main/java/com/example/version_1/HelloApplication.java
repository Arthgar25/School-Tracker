package com.example.version_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        NavBar navBar = new NavBar();
        AssignmentListView assignmentListView = new AssignmentListView();
        AssignmentStorage assignmentStorage = new AssignmentStorage();
        assignmentStorage.load();

        for(Assignment assignment : assignmentStorage.getAssignments()){
            assignmentListView.addAssignment(assignment);
        }

        navBar.setOnAddCourse(() -> {
            System.out.println("Open Add Course dialog");
        });

        navBar.setOnAddAssignment(() -> {
            Assignment assignment = new Assignment(
                    navBar.getTitle(),
                    navBar.getDueDate(),
                    navBar.getType(),
                    navBar.getDescription()
            );

            assignmentStorage.addAssignment(assignment);
            assignmentListView.addAssignment(assignment);
        });

        navBar.setOnClearList(() -> {
            assignmentListView.hideCompleted();
        });

        root.setTop(navBar);
        root.setCenter(assignmentListView);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Semester Planner");
        stage.setScene(scene);
        stage.show();
    }
}
