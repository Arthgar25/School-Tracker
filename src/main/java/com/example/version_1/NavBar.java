package com.example.version_1;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;

public class NavBar extends HBox{
    private Button addCourseBtn;
    private Button addAssignmentBtn;
    private Button clearList;
    private TextField title;
    private DatePicker dueDate;
    private ComboBox<AssignmentType> typeBox;
    private TextArea description;

    public NavBar(){
        initialize();
        layOut();
    }

    private void initialize(){
        addCourseBtn = new Button("Add Course");
        addAssignmentBtn = new Button("Add Assignment");
        clearList = new Button("Clear List");
        title = new TextField();
        dueDate = new DatePicker();
        typeBox = new ComboBox<>();
        description =  new TextArea();

        description.setPromptText("Enter Description");
        description.setPrefRowCount(1);
        typeBox.getItems().addAll(AssignmentType.values());
        typeBox.setValue(AssignmentType.HOMEWORK);
        title.setPromptText("Assignment Title");
        dueDate.setValue(LocalDate.now());

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
        getChildren().addAll(title, dueDate, typeBox, description, addAssignmentBtn, clearList);
        setMinHeight(50);
    }

    public String getTitle(){
        return title.getText();
    }
    public LocalDate getDueDate(){
        return dueDate.getValue();
    }
    public AssignmentType getType(){
        return typeBox.getValue();
    }
    public String getDescription(){
        return description.getText();
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
