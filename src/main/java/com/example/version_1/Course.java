package com.example.version_1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String group;
    private String name;
    private ArrayList<Assignment> assignments = new ArrayList<>();
    private Map<AssignmentType, EvaluationCriterion> gradingScheme = new HashMap<>();

    public Course(){
        this.group = "";
        this.name = "Courses";
    }

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addEvaluationCriterion(AssignmentType type, double percentage){
        gradingScheme.put(type, new EvaluationCriterion(type, percentage));
    }

    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    public double getAssignmentWeight(Assignment assignment) {
        EvaluationCriterion criterion = gradingScheme.get(assignment.getType());

        if (criterion == null) return 0.0;

        long count = assignments.stream()
                .filter(a -> a.getType() == assignment.getType())
                .count();

        if (count == 0) return 0.0;

        return criterion.getPercentage() / count;
    }

    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
