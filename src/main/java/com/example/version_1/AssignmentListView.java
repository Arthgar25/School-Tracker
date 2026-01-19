package com.example.version_1;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AssignmentListView extends ScrollPane {
    private VBox listContainer;

    public AssignmentListView() {
        listContainer = new VBox();
        listContainer.setPadding(new Insets(15));
        setContent(listContainer);
        setFitToWidth(true);
    }

    public void addAssignment(Assignment assignment){
        listContainer.getChildren().add(new AssignmentItemView(assignment));
    }
}
