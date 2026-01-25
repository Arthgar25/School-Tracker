package com.example.version_1;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class AssignmentListView extends ScrollPane {
    private VBox listContainer;
    private List<AssignmentItemView> items;

    public AssignmentListView() {
        items = new ArrayList<>();
        listContainer = new VBox(10);
        listContainer.setPadding(new Insets(15));
        setContent(listContainer);
        setFitToWidth(true);
    }

    public void addAssignment(Assignment assignment){
        AssignmentItemView view = new AssignmentItemView(assignment);
        items.add(view);
        listContainer.getChildren().add(view);
    }

    public void hideCompleted(){
        listContainer.getChildren().clear();
        for (AssignmentItemView item : items){
            if(!item.getAssignment().getCompleted()){
                listContainer.getChildren().add(item);
            }
        }
    }

    public void refresh(List<Assignment> assignments) {
        items.clear();
        listContainer.getChildren().clear();

        for (Assignment assignment : assignments) {
            if(!assignment.getCompleted()){
                AssignmentItemView view = new AssignmentItemView(assignment);
                items.add(view);
                listContainer.getChildren().add(view);
            }
        }
    }
}
