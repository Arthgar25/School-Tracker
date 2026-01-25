package com.example.version_1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        NavBar navBar = new NavBar();
        AssignmentListView assignmentListView = new AssignmentListView();
        AssignmentStorage assignmentStorage = new AssignmentStorage();
        assignmentStorage.load();
        CourseStorage courseStorage = new CourseStorage();
        courseStorage.load();

        for(Assignment assignment : assignmentStorage.getAssignments()){
            assignmentListView.addAssignment(assignment);
        }

        Course defaultCourse = new Course("COURSES");
        if (courseStorage.getCourses().isEmpty()) {
            courseStorage.addCourse(defaultCourse);
        }

        navBar.setCourses(courseStorage.getCourses(), defaultCourse);


        navBar.setOnAddCourse(() -> {
            if(!navBar.isCourseNameEmpty()){
                Course course = new Course(navBar.getCourseName());
                courseStorage.addCourse(course);
                navBar.addCourse(course);
                navBar.resetCourseForm();
            }
        });

        navBar.setOnAddAssignment(() -> {
            if(!navBar.isClear()){
                Assignment assignment = new Assignment(
                        navBar.getTitle(),
                        navBar.getDueDate(),
                        navBar.getType(),
                        navBar.getDescription()
                );
                assignmentStorage.addAssignment(assignment);
                assignmentListView.addAssignment(assignment);
                assignmentListView.refresh(assignmentStorage.getAssignments());
                navBar.resetForm();
            }
        });

        navBar.setOnClearList(() -> {
            assignmentListView.hideCompleted();
            assignmentStorage.save();
        });

        root.setTop(navBar);
        root.setCenter(assignmentListView);
        Scene scene = new Scene(root, 720, 480);
        stage.setTitle("Semester Planner");
        stage.setScene(scene);
        stage.show();
    }
}
