package view;

/**
 * =============================================================================
 *                               VIEW LAYER
 * =============================================================================
 * The View is responsible strictly for formatting and rendering data to the user.
 *
 * Characteristics of the View Layer:
 * 1. Presentation Only: Contains no application logic, state persistence, or
 *    data transformations.
 * 2. Decoupled from Model: Receives raw values or read-only representations to
 *    display without modifying model attributes directly.
 * =============================================================================
 */
public class StudentView {

    /**
     * Renders detailed student profile information to the console.
     */
    public void displayStudentDetails(int id, String name, String course, double marks, String grade) {
        System.out.println("  +------------------------------------------------------------+");
        System.out.println("  |                     STUDENT PROFILE CARD                   |");
        System.out.println("  +------------------------------------------------------------+");
        System.out.printf("  |  Student ID   : %-42d |%n", id);
        System.out.printf("  |  Full Name    : %-42s |%n", name);
        System.out.printf("  |  Enrolled In  : %-42s |%n", course);
        System.out.printf("  |  Final Marks  : %-42.2f |%n", marks);
        System.out.printf("  |  Letter Grade : %-42s |%n", grade);
        System.out.println("  +------------------------------------------------------------+");
    }

    /**
     * Displays system status or lifecycle notification messages.
     */
    public void displayMessage(String message) {
        System.out.println("  [View Output] " + message);
    }
}
