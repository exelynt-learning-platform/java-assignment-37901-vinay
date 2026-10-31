import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * =============================================================================
 * CollectionTypesDemo demonstrates the four primary Java Collection interfaces:
 *
 * 1. LIST (ArrayList):
 *    - An ordered collection (sequence).
 *    - Allows duplicate elements.
 *    - Supports index-based access (get(i), add(i, elem)).
 *
 * 2. SET (HashSet):
 *    - A collection that contains NO duplicate elements.
 *    - Backed by a hash table (HashMap); does not guarantee insertion order.
 *    - Provides O(1) constant-time performance for basic operations (add, contains, remove).
 *
 * 3. MAP (HashMap):
 *    - Stores key-value mappings (Key -> Value).
 *    - Keys must be unique; each key maps to exactly one value.
 *    - Values can be duplicated.
 *    - Extremely fast O(1) retrieval by key.
 *
 * 4. QUEUE (LinkedList implementing Queue):
 *    - Designed for holding elements prior to processing.
 *    - Operates on a First-In-First-Out (FIFO) basis.
 *    - Uses offer() to enqueue and poll() to dequeue in arrival order.
 * =============================================================================
 */
public class CollectionTypesDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("    JAVA COLLECTIONS FRAMEWORK: LIST, SET, MAP, & QUEUE DEMO     ");
        System.out.println("=================================================================\n");

        // =====================================================================
        // 1. LIST: ArrayList<String> to store Student Names
        // Allows duplicates and preserves insertion order.
        // =====================================================================
        System.out.println("--- 1. List Interface: ArrayList (Student Names) ---");
        List<String> studentNamesList = new ArrayList<>();
        studentNamesList.add("Vinay Madarkhandi");
        studentNamesList.add("Alice Johnson");
        studentNamesList.add("Bob Smith");
        studentNamesList.add("Vinay Madarkhandi"); // Allowed: duplicate entry

        System.out.println("ArrayList Elements (Order preserved, duplicates permitted):");
        for (int i = 0; i < studentNamesList.size(); i++) {
            System.out.printf("  [%d] %s\n", i, studentNamesList.get(i));
        }

        // =====================================================================
        // 2. SET: HashSet<String> to store Unique Course Names
        // Rejects duplicate entries automatically.
        // =====================================================================
        System.out.println("\n--- 2. Set Interface: HashSet (Unique Courses) ---");
        Set<String> uniqueCoursesSet = new HashSet<>();
        uniqueCoursesSet.add("Java Programming");
        uniqueCoursesSet.add("Data Structures");
        uniqueCoursesSet.add("Database Systems");
        uniqueCoursesSet.add("Java Programming"); // Duplicate insertion ignored
        uniqueCoursesSet.add("Cloud Computing");

        System.out.println("HashSet Elements (Duplicates automatically filtered out):");
        for (String course : uniqueCoursesSet) {
            System.out.println("  * " + course);
        }
        System.out.println("Total Unique Courses: " + uniqueCoursesSet.size());

        // =====================================================================
        // 3. MAP: HashMap<Integer, String> for Roll Number -> Student Name Mapping
        // Key is unique roll number (Integer); Value is student name (String).
        // =====================================================================
        System.out.println("\n--- 3. Map Interface: HashMap (Roll Number -> Student Name) ---");
        Map<Integer, String> studentRollMap = new HashMap<>();
        studentRollMap.put(101, "Vinay Madarkhandi");
        studentRollMap.put(102, "Alice Johnson");
        studentRollMap.put(103, "Bob Smith");
        studentRollMap.put(104, "Charlie Davis");
        studentRollMap.put(102, "Alice J. Updated"); // Replaces previous value for key 102

        System.out.println("HashMap Entries (Key-Value pairs):");
        for (Map.Entry<Integer, String> entry : studentRollMap.entrySet()) {
            System.out.printf("  Roll No: %-4d => Student Name: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println("Direct lookup for Roll No 103: " + studentRollMap.get(103));

        // =====================================================================
        // 4. QUEUE: Queue<String> (FIFO) for Student Registration Order
        // Simulates students lining up to register for classes.
        // =====================================================================
        System.out.println("\n--- 4. Queue Interface: LinkedList as Queue (FIFO Registration) ---");
        Queue<String> registrationQueue = new LinkedList<>();

        // Enqueuing students using offer()
        System.out.println("Students joining registration line (offer):");
        registrationQueue.offer("Student #1: Vinay");
        registrationQueue.offer("Student #2: Alice");
        registrationQueue.offer("Student #3: Bob");
        registrationQueue.offer("Student #4: Diana");

        System.out.println("Current student at front of queue (peek): " + registrationQueue.peek());
        System.out.println("Queue size before processing: " + registrationQueue.size());

        System.out.println("\nProcessing students in First-In-First-Out (FIFO) order (poll):");
        while (!registrationQueue.isEmpty()) {
            // poll() removes and returns the head of the queue
            String registeredStudent = registrationQueue.poll();
            System.out.println("  [Registered]: " + registeredStudent);
        }
        System.out.println("Queue size after processing: " + registrationQueue.size());

        System.out.println("\n=================================================================");
        System.out.println("All 4 core collection interfaces demonstrated successfully!");
        System.out.println("=================================================================");
    }
}
