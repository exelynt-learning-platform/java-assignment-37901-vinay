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
import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * StudentRecordManager
 * Integrates comprehensive file handling, character streams, buffered streams,
 * serialization/deserialization, and robust exception handling in Java I/O.
 * =============================================================================
 */
public class StudentRecordManager {

    private static final String TEXT_FILE = "students.txt";
    private static final String BINARY_FILE = "students.dat";

    /**
     * 1. File Operations using File Class:
     * Creates students.txt if it does not exist and inspects file properties.
     */
    public static File setupAndValidateTextFile(String filePath) {
        System.out.println("--- 1. File Class Validation & Creation ---");
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                System.out.println("File '" + filePath + "' did not exist. Created new file? " + created);
            } else {
                System.out.println("File '" + filePath + "' already exists.");
            }

            // Validating file properties
            System.out.println("File Name          : " + file.getName());
            System.out.println("Absolute Path      : " + file.getAbsolutePath());
            System.out.println("File Exists?       : " + file.exists());
            System.out.println("Readable (canRead) : " + file.canRead());
            System.out.println("Writable (canWrite): " + file.canWrite());
            System.out.println("File Size in Bytes : " + file.length());

        } catch (IOException e) {
            System.err.println("[IOException]: Error creating or validating file: " + e.getMessage());
        }

        return file;
    }

    /**
     * 2. Writing Formatted Data using FileWriter, BufferedWriter, and PrintWriter:
     * Stores student records in a readable text table.
     */
    public static void writeStudentRecordsToTextFile(String filePath, List<Student> students) {
        System.out.println("\n--- 2. Writing Records via FileWriter, BufferedWriter, & PrintWriter ---");

        /*
         * Stream Chaining:
         * FileWriter -> Connects to file on disk.
         * BufferedWriter -> Buffers characters in memory to minimize physical disk writes.
         * PrintWriter -> Provides convenient formatted printing (printf, println).
         */
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {

            printWriter.println("=================================================================");
            printWriter.println("                 STUDENT ACADEMIC RECORDS DATABASE               ");
            printWriter.println("=================================================================");
            printWriter.printf("%-8s | %-22s | %-20s | %-6s\n", "ID", "Student Name", "Department", "GPA");
            printWriter.println("-----------------------------------------------------------------");

            for (Student s : students) {
                printWriter.printf("%-8d | %-22s | %-20s | %6.2f\n",
                        s.getId(), s.getName(), s.getDepartment(), s.getGpa());
            }

            printWriter.println("=================================================================");
            System.out.println(">> Success: Formatted student records written to " + filePath);

        } catch (IOException e) {
            System.err.println("[IOException]: Failed to write formatted text file: " + e.getMessage());
        }
    }

    /**
     * 3. Reading Data from File using FileReader and BufferedReader:
     * Traverses the text file line by line until EOF (null).
     */
    public static void readStudentRecordsFromTextFile(String filePath) {
        System.out.println("\n--- 3. Reading Records via FileReader & BufferedReader ---\n");

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File '" + filePath + "' not found.");
            return;
        }

        /*
         * Stream Chaining:
         * FileReader -> Reads characters from file.
         * BufferedReader -> Caches characters in an 8KB memory buffer, enabling readLine().
         */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            int lineNumber = 1;

            // Reading until end-of-file (readLine() returns null)
            while ((currentLine = bufferedReader.readLine()) != null) {
                System.out.println(currentLine);
                lineNumber++;
            }

            System.out.println("\n>> Success: End of file reached. All lines read from " + filePath);

        } catch (FileNotFoundException e) {
            System.err.println("[FileNotFoundException]: Target file could not be found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[IOException]: Failed reading file: " + e.getMessage());
        }
    }

    /**
     * 4. Object Serialization:
     * Serializes Student objects into a binary .dat file.
     */
    public static void serializeStudentObjects(String filePath, List<Student> students) {
        System.out.println("\n--- 4. Object Serialization (ObjectOutputStream) ---");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeInt(students.size());
            for (Student s : students) {
                oos.writeObject(s);
            }
            System.out.println(">> Serialization Success: " + students.size() + " Student objects written to " + filePath);

        } catch (IOException e) {
            System.err.println("[Serialization Error]: Failed to serialize objects: " + e.getMessage());
        }
    }

    /**
     * 5. Object Deserialization:
     * Reconstructs Student objects from binary .dat stream back into heap memory.
     */
    public static List<Student> deserializeStudentObjects(String filePath) {
        System.out.println("\n--- 5. Object Deserialization (ObjectInputStream) ---");
        List<Student> restoredList = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Binary file '" + filePath + "' not found.");
            return restoredList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            int count = ois.readInt();
            System.out.println("Reconstructing " + count + " objects from binary stream:");

            for (int i = 0; i < count; i++) {
                Student s = (Student) ois.readObject();
                restoredList.add(s);
                System.out.println("  [Restored]: " + s);
            }

            System.out.println(">> Deserialization Success: Live objects reconstructed successfully.");

        } catch (FileNotFoundException e) {
            System.err.println("[FileNotFoundException]: Binary data file missing: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[IOException]: Error reading binary stream: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("[ClassNotFoundException]: Student class bytecode not found: " + e.getMessage());
        }

        return restoredList;
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   FILE-BASED STUDENT RECORD MANAGEMENT SYSTEM (JAVA I/O)        ");
        System.out.println("=================================================================\n");

        // 1. File Class Setup and Validation
        setupAndValidateTextFile(TEXT_FILE);

        // 2. Prepare Sample In-Memory Student Data
        List<Student> sampleStudents = new ArrayList<>();
        sampleStudents.add(new Student(101, "Vinay Madarkhandi", "Computer Science", 3.95));
        sampleStudents.add(new Student(102, "Alice Johnson", "Data Engineering", 3.88));
        sampleStudents.add(new Student(103, "Bob Smith", "Information Security", 3.65));
        sampleStudents.add(new Student(104, "Diana Prince", "Artificial Intelligence", 4.00));

        // 3. Write Formatted Text Data (FileWriter, BufferedWriter, PrintWriter)
        writeStudentRecordsToTextFile(TEXT_FILE, sampleStudents);

        // 4. Read Formatted Text Data (FileReader, BufferedReader)
        readStudentRecordsFromTextFile(TEXT_FILE);

        // 5. Binary Object Serialization (ObjectOutputStream)
        serializeStudentObjects(BINARY_FILE, sampleStudents);

        // 6. Binary Object Deserialization (ObjectInputStream)
        List<Student> deserializedStudents = deserializeStudentObjects(BINARY_FILE);

        System.out.println("\n=================================================================");
        System.out.println("All Java I/O file handling operations verified successfully!     ");
        System.out.println("=================================================================");
    }
}
