package com.example.version_1;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AssignmentItemView extends HBox {
    private Assignment assignment;

    public AssignmentItemView(Assignment assignment) {
        this.assignment = assignment;

        // Spacing
        setSpacing(12);
        setPadding(new Insets(8,12,8,12));

        // Rounded outline
        setStyle("""
            -fx-border-color: #cfcfcf;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
            -fx-background-color: #f9f9f9;
        """);

        Label title = new Label(assignment.getTitle());
        Label due = new Label(formatDueText());
        Label description = new Label(assignment.getDescription());
        Label assignmentType = new Label(assignment.getType().toString());

        // Description styling
        description.setStyle("-fx-text-fill: #666666;");

        CheckBox completedCheckBox = new CheckBox();
        completedCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            assignment.setCompleted(newVal);
        });

        getChildren().addAll(completedCheckBox, title, due, assignmentType, description);
    }

    public Assignment getAssignment() {
        return assignment;
    }

    private String formatDueText() {
        long days = assignment.daysUntilDue();

        if (days > 0) {
            return "Due in " + days + " days";
        } else if (days == 0) {
            return "Due today";
        } else {
            return "Overdue by " + Math.abs(days) + " days";
        }
    }
}
