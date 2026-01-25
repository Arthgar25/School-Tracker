package com.example.version_1;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Assignment {
    private String title;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private AssignmentType type;
    private boolean completed;
    private String description;
    private String courseName;

    public Assignment(String title, LocalDate dueDate, LocalTime dueTime, AssignmentType type, String description, String courseName) {
        this.title = title;
        this.type = type;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.completed = false;
        this.description = description;
        this.courseName = courseName;
    }

    public String getTitle() {
        return title;
    }
    public AssignmentType getType() {return type;}
    public LocalDate getDueDate() {return dueDate;}
    public String getDescription() { return description; }
    public boolean getCompleted() {return completed;}
    public String getCourseName() {return courseName;}
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public long daysUntilDue() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
}
