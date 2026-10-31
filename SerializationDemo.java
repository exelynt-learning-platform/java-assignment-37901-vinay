import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * SerializationDemo demonstrates:
 *
 * 1. SERIALIZATION:
 *    - Uses java.io.ObjectOutputStream wrapping FileOutputStream.
 *    - Translates in-memory Java objects into a portable binary byte stream.
 *    - Method: objectOutputStream.writeObject(object)
 *
 * 2. DESERIALIZATION:
 *    - The reverse process of serialization.
 *    - Reads the raw binary byte stream and reconstructs the original Java object
 *      graph back in memory.
 *    - Uses java.io.ObjectInputStream wrapping FileInputStream.
 *    - Method: (TargetType) objectInputStream.readObject()
 * =============================================================================
 */
public class SerializationDemo {

    private static final String DATA_FILE = "students.dat";

    /**
     * Serializes a list of Student objects to a binary file.
     *
     * @param studentList List of Student objects to write
     */
    public static void serializeStudents(List<Student> studentList) {
        System.out.println("--- 1. Serializing Student Objects to '" + DATA_FILE + "' ---");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            // Write the count of students
            oos.writeInt(studentList.size());

            // Write each Student object sequentially
            for (Student student : studentList) {
                oos.writeObject(student);
                System.out.println("  [Serialized Object]: " + student);
            }

            System.out.println(">> Serialization successfully completed! Byte stream stored in " + DATA_FILE + ".");

        } catch (IOException e) {
            System.err.println("Serialization Error: " + e.getMessage());
        }
    }

    /**
     * Deserializes Student objects from the binary file and reconstructs them in memory.
     *
     * @return List of reconstructed Student objects
     */
    public static List<Student> deserializeStudents() {
        System.out.println("\n--- 2. Deserializing Student Objects from '" + DATA_FILE + "' ---");
        List<Student> deserializedList = new ArrayList<>();

        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("Binary file '" + DATA_FILE + "' not found.");
            return deserializedList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Read number of objects stored
            int studentCount = ois.readInt();
            System.out.println("Reconstructing " + studentCount + " objects from binary stream:");

            for (int i = 0; i < studentCount; i++) {
                // readObject() deserializes bytes back into a Student instance
                Student student = (Student) ois.readObject();
                deserializedList.add(student);
                System.out.println("  [Deserialized Object]: " + student);
            }

            System.out.println(">> Deserialization successfully completed! Objects restored in heap memory.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization Error: " + e.getMessage());
        }

        return deserializedList;
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("     JAVA OBJECT SERIALIZATION & DESERIALIZATION DEMO            ");
        System.out.println("=================================================================\n");

        // 1. Create in-memory Student objects
        List<Student> originalStudents = new ArrayList<>();
        originalStudents.add(new Student(101, "Vinay Madarkhandi", 3.92));
        originalStudents.add(new Student(102, "Alice Johnson", 3.85));
        originalStudents.add(new Student(103, "Bob Smith", 3.45));
        originalStudents.add(new Student(104, "Diana Prince", 4.00));

        // 2. Perform Serialization
        serializeStudents(originalStudents);

        // 3. Perform Deserialization
        List<Student> restoredStudents = deserializeStudents();

        // 4. Verify reconstructed objects
        System.out.println("\n=================================================================");
        System.out.println("            VERIFICATION OF RESTORED OBJECTS IN MEMORY           ");
        System.out.println("=================================================================");
        for (Student student : restoredStudents) {
            System.out.printf("Verified: ID=%-4d | Name=%-20s | GPA=%.2f\n",
                    student.getId(), student.getName(), student.getGpa());
        }
        System.out.println("=================================================================");
        System.out.println("Serialization & Deserialization cycle verified without errors!");
    }
}
