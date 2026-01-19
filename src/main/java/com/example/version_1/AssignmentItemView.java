package com.example.version_1;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AssignmentItemView extends HBox {
    private Assignment assignment;

    public AssignmentItemView(Assignment assignment) {
        this.assignment = assignment;
        Label title = new Label(assignment.getTitle());
        Label due = new Label(assignment.getDueDate().toString());
        getChildren().addAll(title,due);
    }
}
