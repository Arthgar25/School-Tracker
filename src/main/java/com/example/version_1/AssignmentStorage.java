package com.example.version_1;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssignmentStorage {
    private static final String FILE_NAME = "assignments.txt";
    private final Path filePath;
    private List<Assignment> assignments;

    public AssignmentStorage() {
        this.assignments = new ArrayList<>();
        this.filePath = Path.of(System.getProperty("user.home"), FILE_NAME);
    }
    public void load(){

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
        return "line";
    }

    public void addAssignment(Assignment assignment){

    }

    public List<Assignment> getAssignments(){
        return assignments;
    }


}
