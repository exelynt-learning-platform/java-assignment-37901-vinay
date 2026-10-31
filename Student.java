import java.io.Serializable;

/**
 * =============================================================================
 * Student Model Entity
 * Implements Serializable to support object persistence into binary streams.
 * =============================================================================
 */
public class Student implements Serializable {

    // Serial version UID for serialization compatibility verification
    private static final long serialVersionUID = 202408L;

    private int id;
    private String name;
    private String department;
    private double gpa;

    public Student(int id, String name, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return String.format("Student [ID: %-4d | Name: %-20s | Dept: %-15s | GPA: %.2f]",
                id, name, department, gpa);
    }
}
