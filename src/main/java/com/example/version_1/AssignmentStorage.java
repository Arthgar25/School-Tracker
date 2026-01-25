package com.example.version_1;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AssignmentStorage {
    private static final String FILE_NAME = "assignments.txt";
    private final Path filePath;
    private List<Assignment> assignments;

    public AssignmentStorage() {
        this.assignments = new ArrayList<>();
        this.filePath = Paths.get(FILE_NAME);
    }

    public void load(){
        assignments.clear();
        if (!Files.exists(filePath)) {
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Assignment assignment = parseLine(line);
                if (assignment != null && !assignment.getCompleted()) {
                    assignments.add(assignment);
                }
            }
            assignments.sort(Comparator.comparing(Assignment::getDueDate));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Assignment assignment : assignments) {
                writer.write(serializeAssignment(assignment));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeAssignment(Assignment assignment) {
        return String.join("|",
                assignment.getTitle(),
                assignment.getDueDate().toString(),
                assignment.getDueTime().toString(),
                assignment.getType().name(),
                Boolean.toString(assignment.getCompleted()),
                assignment.getDescription().replace("\n", " "),
                assignment.getCourseName()
        );
    }

    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
        assignments.sort(Comparator.comparing(Assignment::getDueDate));
        save();
    }

    public List<Assignment> getAssignments(){
        return assignments;
    }

    public Assignment parseLine(String line){
        try {
            String[] parts = line.split("\\|", -1);
            String title = parts[0];
            LocalDate dueDate = LocalDate.parse(parts[1]);
            LocalTime dueTime = LocalTime.parse(parts[2]);
            AssignmentType type = AssignmentType.valueOf(parts[3]);
            boolean completed = Boolean.parseBoolean(parts[4]);
            String description = parts[5];
            String courseName = parts[6];
            Assignment assignment = new Assignment(title, dueDate, dueTime, type, description, courseName);
            assignment.setCompleted(completed);
            return assignment;
        } catch (Exception e) {
            return null;
        }
    }
}
