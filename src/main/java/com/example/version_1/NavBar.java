package com.example.version_1;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

public class NavBar extends VBox {
    private HBox assignmentRow;
    private HBox courseRow;
    // Assignment Elements
    private TextField title;
    private DatePicker dueDate;
    private Spinner<LocalTime> dueTime;
    private ComboBox<AssignmentType> typeBox;
    private ComboBox<Course> courseBox;
    private TextArea description;
    private Button addAssignmentBtn;
    private Button clearList;
    // Course Elements
    private TextField courseNameField;
    private Button addCourseBtn;
    private Course defaultCourse;

    // Constructor
    public NavBar(){
        initialize();
        layOut();
    }

    // Internal
    private void initialize(){
        // Rows
        assignmentRow = new HBox(10);
        courseRow = new HBox(10);

        // Title
        title = new TextField();
        title.setPromptText("Assignment Title");

        // Date Picker
        dueDate = new DatePicker();
        dueDate.setValue(LocalDate.now());

        // Time Picker
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

        // Assignment Type
        typeBox = new ComboBox<>();
        typeBox.getItems().addAll(AssignmentType.values());
        typeBox.setValue(AssignmentType.HOMEWORK);

        // Course Combo Box
        courseBox = new ComboBox<>();
        courseBox.setValue(defaultCourse);

        // Description
        description =  new TextArea();
        description.setPromptText("Enter Description");
        description.setPrefRowCount(1);
        description.setPrefColumnCount(15);
        description.setMaxWidth(200);

        // Buttons
        addAssignmentBtn = new Button("Add Assignment");
        addCourseBtn = new Button("Add Course");
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

        // Course Title
        courseNameField = new TextField();
        courseNameField.setPromptText("Course Name");
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

    // Actions
    public void resetForm(){
        title.clear();
        dueDate.setValue(LocalDate.now());
        typeBox.setValue(AssignmentType.HOMEWORK);
        description.clear();
        dueTime.getValueFactory().setValue(LocalTime.of(12, 0));
    }

    public void resetCourseForm() {
        courseNameField.clear();
    }

    public void addCourse(Course course){
        courseBox.getItems().add(course);
        courseBox.setValue(course);
    }

    // Booleans
    public boolean isClear(){
        if(title.getText().isEmpty() || dueDate.getValue() == null || description.getText().isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isCourseNameEmpty() {
        return courseNameField.getText().isEmpty();
    }

    // Getters
    public String getTitle(){return title.getText();}
    public LocalDate getDueDate(){return dueDate.getValue();}
    public AssignmentType getType(){return typeBox.getValue();}
    public String getDescription(){return description.getText();}
    public String getCourseName() {return courseNameField.getText();}
    public String getCourseBox(){return courseBox.getSelectionModel().getSelectedItem().toString();}
    public LocalTime getDueTime() {return dueTime.getValue();}
    public Course getCourse() {return courseBox.getValue();}

    // Setters
    public void setOnAddCourse(Runnable action) {addCourseBtn.setOnAction(e -> action.run());}
    public void setOnAddAssignment(Runnable action) {addAssignmentBtn.setOnAction(e -> action.run());}
    public void setOnClearList(Runnable action) {clearList.setOnAction(e -> action.run());}
    public void setCourses(List<Course> courses, Course defaultCourse) {
        this.defaultCourse = defaultCourse;
        courseBox.getItems().setAll(courses);
        courseBox.setValue(defaultCourse);
    }
}
