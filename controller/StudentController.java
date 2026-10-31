package controller;

import model.Student;
import view.StudentView;

/**
 * =============================================================================
 *                            CONTROLLER LAYER
 * =============================================================================
 * The Controller acts as an intermediary / mediator between the Model and the View.
 *
 * Characteristics of the Controller Layer:
 * 1. Coordinates Data Flow: Receives user commands, performs validation,
 *    invokes updates on the Model, and instructs the View to re-render.
 * 2. Separation of Concerns: Keeps Model and View decoupled from one another;
 *    neither Model nor View has direct knowledge of the other.
 * =============================================================================
 */
public class StudentController {

    private final Student model;
    private final StudentView view;

    /**
     * Dependency Injection of Model and View into the Controller.
     */
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    // --- Model Accessor/Mutator Delegations ---

    public int getStudentId() {
        return model.getId();
    }

    public void setStudentId(int id) {
        model.setId(id);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentCourse() {
        return model.getCourse();
    }

    public void setStudentCourse(String course) {
        model.setCourse(course);
    }

    public double getStudentMarks() {
        return model.getMarks();
    }

    public void setStudentMarks(double marks) {
        // Business logic / validation in controller
        if (marks < 0.0 || marks > 100.0) {
            view.displayMessage("Validation Warning: Marks must be between 0 and 100. Received: " + marks);
            return;
        }
        model.setMarks(marks);
    }

    /**
     * Bridges the Model and View by extracting data from the Model and
     * instructing the View to display it.
     */
    public void updateView() {
        view.displayStudentDetails(
                model.getId(),
                model.getName(),
                model.getCourse(),
                model.getMarks(),
                model.calculateGrade()
        );
    }
}
