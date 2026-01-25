package com.example.version_1;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.util.List;

public class NavBar extends HBox{
    private Button addCourseBtn;
    private Button addAssignmentBtn;
    private Button clearList;
    private TextField title;
    private DatePicker dueDate;
    private ComboBox<AssignmentType> typeBox;
    private ComboBox<Course> courseBox;
    private TextArea description;
    private Course defaultCourse;

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
        courseBox = new ComboBox<>();

        courseBox.setValue(defaultCourse);
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
        getChildren().addAll(title, dueDate, typeBox, courseBox, description, addAssignmentBtn, clearList);
        setMinHeight(50);
    }

    public void resetForm(){
        title.clear();
        dueDate.setValue(LocalDate.now());
        typeBox.setValue(AssignmentType.HOMEWORK);
        description.clear();
    }

    public boolean isClear(){
        if(title.getText().isEmpty() || dueDate.getValue() == null || description.getText().isEmpty()){
            return true;
        }
        return false;
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

    public void addCourse(Course course){
        courseBox.getItems().add(course);
        courseBox.setValue(course);
    }

    public void setCourses(List<Course> courses, Course defaultCourse) {
        this.defaultCourse = defaultCourse;
        courseBox.getItems().setAll(courses);
        courseBox.setValue(defaultCourse);
    }
    public Course getCourse() {
        return courseBox.getValue();
    }
}
