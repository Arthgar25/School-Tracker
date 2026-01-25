package com.example.version_1;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalTimeStringConverter;
import java.time.LocalTime;


import java.time.LocalDate;
import java.util.List;

public class NavBar extends VBox {
    private HBox assignmentRow;
    private HBox courseRow;
    private TextField courseNameField;
    private Button addCourseBtn;
    private Button addAssignmentBtn;
    private Button clearList;
    private TextField title;
    private DatePicker dueDate;
    private ComboBox<AssignmentType> typeBox;
    private ComboBox<Course> courseBox;
    private TextArea description;
    private Course defaultCourse;
    private Spinner<LocalTime> dueTime;

    public NavBar(){
        initialize();
        layOut();
    }

    private void initialize(){
        courseNameField = new TextField();
        courseNameField.setPromptText("Course Name");
        addCourseBtn = new Button("Add Course");
        addAssignmentBtn = new Button("Add Assignment");
        clearList = new Button("Clear List");
        title = new TextField();
        dueDate = new DatePicker();
        typeBox = new ComboBox<>();
        courseBox = new ComboBox<>();

        assignmentRow = new HBox(10);
        courseRow = new HBox(10);
        courseBox.setValue(defaultCourse);
        description =  new TextArea();
        description.setPromptText("Enter Description");
        description.setPrefRowCount(1);
        typeBox.getItems().addAll(AssignmentType.values());
        typeBox.setValue(AssignmentType.HOMEWORK);
        title.setPromptText("Assignment Title");
        dueDate.setValue(LocalDate.now());
        dueTime = new Spinner<>();
        dueTime.setEditable(true);

        SpinnerValueFactory<LocalTime> timeFactory =
                new SpinnerValueFactory<LocalTime>() {

                    {
                        setValue(LocalTime.of(12, 0)); // 12:00 PM default
                    }

                    @Override
                    public void decrement(int steps) {
                        LocalTime current = getValue();
                        setValue(current.minusMinutes(15L * steps));
                    }

                    @Override
                    public void increment(int steps) {
                        LocalTime current = getValue();
                        setValue(current.plusMinutes(15L * steps));
                    }
                };

        timeFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                dueTime.getEditor().setText(newVal.toString());
            }
        });

        dueTime.setValueFactory(timeFactory);

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
        setSpacing(8);
        setPadding(new Insets(10));

        assignmentRow.setAlignment(Pos.CENTER_LEFT);
        assignmentRow.getChildren().addAll(
                title, dueDate, dueTime, typeBox, courseBox, description, addAssignmentBtn, clearList
        );

        courseRow.setAlignment(Pos.CENTER_LEFT);
        courseRow.getChildren().addAll(
                courseNameField, addCourseBtn
        );

        getChildren().addAll(assignmentRow, courseRow);
    }

    public void resetForm(){
        title.clear();
        dueDate.setValue(LocalDate.now());
        typeBox.setValue(AssignmentType.HOMEWORK);
        description.clear();
        dueTime.getValueFactory().setValue(LocalTime.of(12, 0));
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

    public String getCourseName() {
        return courseNameField.getText();
    }

    public void resetCourseForm() {
        courseNameField.clear();
    }

    public boolean isCourseNameEmpty() {
        return courseNameField.getText().isEmpty();
    }

    public LocalTime getDueTime() {
        return dueTime.getValue();
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
