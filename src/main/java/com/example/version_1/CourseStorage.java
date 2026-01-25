package com.example.version_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CourseStorage {
    private static final String FILE_NAME = "courses.txt";
    private final Path filePath;
    private List<Course> courses;

    public CourseStorage() {
        this.courses = new ArrayList<>();
        this.filePath = Paths.get(FILE_NAME);
    }

    public void load(){
        courses.clear();
        if(!Files.exists(filePath)){
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)){
            String line;
            while((line = reader.readLine())!=null){
                Course course = parseLine(line);
                if(course != null){
                    courses.add(course);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void save(){
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)){
            for(Course course : courses){
                writer.write(serializeCourse(course));
                writer.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private String serializeCourse(Course course){
        return String.join("|", course.getName(), course.getGroup());
    }
    private Course parseLine(String line){
        try{
            String[] parts = line.split("\\|", -1);
            String name = parts[0];
            String group = parts[1];
            Course course = new Course(name);
            return course;
        } catch (Exception e){
            return null;
        }
    }

    public void addCourse(Course course){
        courses.add(course);
        save();
    }
    
    public boolean addCourseIfNotExists(Course course) {
        if (courseExists(course.getName(), course.getGroup())) {
            return false;
        }
        courses.add(course);
        save();
        return true;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean courseExists(String name, String group) {
        return courses.stream().anyMatch(c ->
                c.getName().equalsIgnoreCase(name.trim()) &&
                        c.getGroup().equalsIgnoreCase(group.trim())
        );
    }
}
