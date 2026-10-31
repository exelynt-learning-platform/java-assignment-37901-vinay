import java.io.Serializable;

/**
 * =============================================================================
 * Class: Student (Serializable)
 *
 * WHAT IS SERIALIZATION?
 * - Serialization is the mechanism of converting the state of a Java object into
 *   a sequence of bytes (byte stream).
 * - This byte stream can be persisted to a file (.dat), stored in a database,
 *   or transmitted over a network socket to another JVM.
 *
 * java.io.Serializable:
 * - A Marker Interface (an interface with zero methods or fields).
 * - Informs the Java Virtual Machine that instances of Student can be safely
 *   serialized by ObjectOutputStream.
 *
 * serialVersionUID:
 * - A unique universal version identifier used during deserialization to verify
 *   that the sender and receiver of a serialized object have loaded classes
 *   for that object that are compatible with respect to serialization.
 * =============================================================================
 */
public class Student implements Serializable {

    // Unique serial version identifier for class versioning
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double gpa;

    // Parameterized Constructor
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Getters and Setters
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("Student [ID: %d | Name: %-18s | GPA: %.2f]", id, name, gpa);
    }
}
