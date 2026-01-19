package com.example.version_1;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class NavBar extends HBox{
    private Button addCourseBtn;
    private Button addAssignmentBtn;
    private Button clearList;

    public NavBar(){
        initialize();
        layOut();
    }

    private void initialize(){
        addCourseBtn = new Button("Add Course");
        addAssignmentBtn = new Button("Add Assignment");
        clearList = new Button("Clear List");

        addCourseBtn.prefWidthProperty().bind(
                widthProperty().multiply(0.15)
        );
        addAssignmentBtn.prefWidthProperty().bind(
                widthProperty().multiply(0.2)
        );
        clearList.prefWidthProperty().bind(
                widthProperty().multiply(0.2)
        );
    }

    private void layOut(){
        setSpacing(10);
        setPadding(new Insets(10,10,10,10));
        setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(addCourseBtn,addAssignmentBtn);
        setMinHeight(50);
    }

    public void setOnAddCourse(Runnable action) {
        addCourseBtn.setOnAction(e -> action.run());
    }

    public void setOnAddAssignment(Runnable action) {
        addAssignmentBtn.setOnAction(e -> action.run());
    }

    public void setOnClearList(Runnable action) {
        clearList.setOnAction(e -> action.run());
    }
}
