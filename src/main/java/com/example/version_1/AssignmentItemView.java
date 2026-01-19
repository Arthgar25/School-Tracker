package com.example.version_1;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AssignmentItemView extends HBox {
    private Assignment assignment;

    public AssignmentItemView(Assignment assignment) {
        this.assignment = assignment;
        Label title = new Label(assignment.getTitle());
        Label due = new Label(assignment.getDueDate().toString());
        Label description = new Label(assignment.getDescription());
        Label assignmentType = new Label(assignment.getType().toString());
        CheckBox completedCheckBox = new CheckBox();
        completedCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            assignment.setCompleted(newVal);
        });
        getChildren().addAll(title,due, description,  assignmentType, completedCheckBox);
    }
}
