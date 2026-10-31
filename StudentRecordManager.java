import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * =============================================================================
 * Student Model Class
 * Represents a student record and implements Serializable for object persistence.
 * =============================================================================
 */
class Student implements Serializable {
    private static final long serialVersionUID = 100L;

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

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public String toTextRecord() {
        return String.format("%d,%s,%s,%.2f", id, name, course, marks);
    }

    @Override
    public String toString() {
        return String.format("Student [ID: %-4d | Name: %-20s | Course: %-15s | Marks: %6.2f]",
                id, name, course, marks);
    }
}

/**
 * =============================================================================
 * Mini-Project: Student Record Management System (File-Based Persistence)
 *
 * Core Features:
 * 1. Dual Persistence Mechanisms:
 *    - Text-Based Persistence: Uses BufferedReader, BufferedWriter, and PrintWriter
 *      to save human-readable reports and parse comma-separated text files.
 *    - Binary Object Persistence: Uses ObjectOutputStream and ObjectInputStream to
 *      serialize and deserialize live Student objects without manual parsing.
 *
 * 2. Complete Exception Handling:
 *    - Employs try-with-resources across all stream operations for automatic resource closure.
 *    - Catches FileNotFoundException, IOException, and ClassNotFoundException with
 *      meaningful, recoverable error messages.
 *
 * 3. Operations:
 *    - Add student records.
 *    - View in-memory records.
 *    - Save and load from Text File.
 *    - Save and load from Object (binary) File.
 * =============================================================================
 */
public class StudentRecordManager {

    private final List<Student> studentList = new ArrayList<>();
    private static final String TEXT_FILE = "student_records.txt";
    private static final String OBJECT_FILE = "student_records.dat";

    /**
     * Adds a student record to the in-memory database.
     */
    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println(">> Added Record: " + student);
    }

    /**
     * Displays all student records currently in memory.
     */
    public void viewCurrentStudents() {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("              CURRENT IN-MEMORY STUDENT RECORDS                  ");
        System.out.println("-----------------------------------------------------------------");
        if (studentList.isEmpty()) {
            System.out.println("No records found in memory.");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
        System.out.println("Total Records in Memory: " + studentList.size());
    }

    /**
     * Saves student records into a formatted human-readable text file using
     * BufferedWriter and PrintWriter.
     */
    public void saveToTextFile(String filePath) {
        System.out.println("\n--- Saving Records to Text File ('" + filePath + "') ---");

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            writer.println("#ID,Name,Course,Marks");
            for (Student student : studentList) {
                writer.println(student.toTextRecord());
            }
            System.out.println(">> Text Persistence Success: " + studentList.size() +
                    " records saved to " + filePath);

        } catch (IOException e) {
            System.err.println("[I/O Error]: Failed to write text file: " + e.getMessage());
        }
    }

    /**
     * Reads student records from a text file using BufferedReader.
     */
    public List<Student> loadFromTextFile(String filePath) {
        System.out.println("\n--- Loading Records from Text File ('" + filePath + "') ---");
        List<Student> loadedList = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Notice: Text file '" + filePath + "' does not exist yet.");
            return loadedList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip header or empty lines
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String course = parts[2].trim();
                    double marks = Double.parseDouble(parts[3].trim());

                    Student student = new Student(id, name, course, marks);
                    loadedList.add(student);
                }
            }
            System.out.println(">> Text Load Success: " + loadedList.size() + " records loaded from " + filePath);

        } catch (FileNotFoundException e) {
            System.err.println("[File Not Found]: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[I/O Error]: Error reading text file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("[Parse Error]: Corrupted number format in text file: " + e.getMessage());
        }

        return loadedList;
    }

    /**
     * Serializes student records into a binary object file using ObjectOutputStream.
     */
    public void saveToObjectFile(String filePath) {
        System.out.println("\n--- Serializing Records to Object File ('" + filePath + "') ---");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeInt(studentList.size());
            for (Student student : studentList) {
                oos.writeObject(student);
            }
            System.out.println(">> Object Serialization Success: " + studentList.size() +
                    " objects saved to " + filePath);

        } catch (IOException e) {
            System.err.println("[Serialization Error]: Failed to write binary object file: " + e.getMessage());
        }
    }

    /**
     * Deserializes student records from a binary object file using ObjectInputStream.
     */
    public List<Student> loadFromObjectFile(String filePath) {
        System.out.println("\n--- Deserializing Records from Object File ('" + filePath + "') ---");
        List<Student> loadedList = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Notice: Binary file '" + filePath + "' does not exist.");
            return loadedList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                Student student = (Student) ois.readObject();
                loadedList.add(student);
            }
            System.out.println(">> Deserialization Success: " + loadedList.size() +
                    " objects restored from " + filePath);

        } catch (FileNotFoundException e) {
            System.err.println("[File Not Found]: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[I/O Error]: Error reading binary stream: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("[Class Error]: Student class definition not found: " + e.getMessage());
        }

        return loadedList;
    }

    public static void main(String[] args) {
        StudentRecordManager manager = new StudentRecordManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("   STUDENT RECORD MANAGEMENT SYSTEM (FILE-BASED PERSISTENCE)     ");
        System.out.println("=================================================================\n");

        // 1. Adding Sample Student Records
        System.out.println("--- 1. Adding Student Records ---");
        manager.addStudent(new Student(101, "Vinay Madarkhandi", "Java Core", 95.50));
        manager.addStudent(new Student(102, "Alice Johnson", "Data Structures", 88.00));
        manager.addStudent(new Student(103, "Bob Smith", "Cloud Computing", 76.25));
        manager.addStudent(new Student(104, "Diana Prince", "Machine Learning", 98.75));

        // 2. View in-memory records
        manager.viewCurrentStudents();

        // 3. Text-Based Persistence (Write & Read)
        manager.saveToTextFile(TEXT_FILE);
        List<Student> textLoadedStudents = manager.loadFromTextFile(TEXT_FILE);
        System.out.println("Displaying Records Read From Text File:");
        for (Student s : textLoadedStudents) {
            System.out.println("  [Text File Record] " + s);
        }

        // 4. Object-Based Binary Persistence (Serialization & Deserialization)
        manager.saveToObjectFile(OBJECT_FILE);
        List<Student> binaryLoadedStudents = manager.loadFromObjectFile(OBJECT_FILE);
        System.out.println("Displaying Records Restored From Binary Object File:");
        for (Student s : binaryLoadedStudents) {
            System.out.println("  [Object File Record] " + s);
        }

        System.out.println("\n=================================================================");
        System.out.println("All text and object file management operations completed!        ");
        System.out.println("=================================================================");

        scanner.close();
    }
}
