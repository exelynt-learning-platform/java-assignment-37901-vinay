/**
 * Program 1: Demonstrates Java syntax, naming conventions (PascalCase for classes,
 * camelCase for variables and methods), clean indentation, and comments.
 */
public class StudentDetails {

    /**
     * Prints student information demonstrating variable naming conventions
     * and method structure.
     */
    public static void displayStudentInfo() {
        // Variable names follow camelCase naming convention
        String studentName = "Vinay";
        int studentRollNumber = 101;
        double studentGradePointAverage = 3.92;
        boolean isEnrolled = true;

        System.out.println("=== Student Information ===");
        System.out.println("Name: " + studentName);
        System.out.println("Roll Number: " + studentRollNumber);
        System.out.println("GPA: " + studentGradePointAverage);
        System.out.println("Enrollment Status: " + (isEnrolled ? "Active" : "Inactive"));
    }

    /**
     * Main method: Entry point of StudentDetails application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        displayStudentInfo();
    }
}
