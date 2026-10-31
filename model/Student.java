package model;

/**
 * =============================================================================
 *                              MODEL LAYER
 * =============================================================================
 * The Model represents the enterprise data structure, domain business logic,
 * and state of the entity.
 *
 * Characteristics of the Model Layer:
 * 1. Independent of User Interface: Has no knowledge of View or Controller.
 * 2. Pure Domain Logic: Contains entity attributes, accessors/mutators, and
 *    business calculations (such as calculating letter grades).
 * =============================================================================
 */
public class Student {

    private int id;
    private String name;
    private String course;
    private double marks;

    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    /**
     * Domain business logic: calculates academic letter grade.
     */
    public String calculateGrade() {
        if (marks >= 90.0) return "A+";
        if (marks >= 80.0) return "A";
        if (marks >= 70.0) return "B";
        if (marks >= 60.0) return "C";
        if (marks >= 50.0) return "D";
        return "F";
    }
}
