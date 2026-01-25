package com.example.version_1;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.time.format.DateTimeFormatter;

public class AssignmentItemView extends HBox {
    private Assignment assignment;

    public AssignmentItemView(Assignment assignment) {
        this.assignment = assignment;
        setSpacing(12);
        setPadding(new Insets(8,12,8,12));
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
        String timePart = "";
        if (assignment.getDueTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            timePart = " at " + assignment.getDueTime().format(formatter);
        }
        if (days > 0) {
            return "Due in " + days + " days" + timePart;
        } else if (days == 0) {
            return "Due today" + timePart;
        } else {
            return "Overdue by " + Math.abs(days) + " days" + timePart;
        }
    }
}
