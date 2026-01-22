package com.example.version_1;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment {
    private String title;
    private LocalDate dueDate;
    private AssignmentType type;
    private boolean completed;
    private String description;

    public Assignment(String title, LocalDate dueDate, AssignmentType type, String description) {
        this.title = title;
        this.type = type;
        this.dueDate = dueDate;
        this.completed = false;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public AssignmentType getType() {return type;}
    public LocalDate getDueDate() {return dueDate;}
    public String getDescription() { return description; }
    public boolean getCompleted() {return completed;}

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long daysUntilDue() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
}
