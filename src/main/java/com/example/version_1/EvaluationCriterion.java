package com.example.version_1;

public class EvaluationCriterion {
    private AssignmentType type;
    private double percentage;

    public EvaluationCriterion(AssignmentType type, double percentage) {
        this.type = type;
        this.percentage = percentage;
    }
    public AssignmentType getType() {
        return type;
    }
    public double getPercentage() {
        return percentage;
    }
}
