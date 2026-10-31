import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * StudentStreamProcessor demonstrates student performance analysis using
 * Java 8 Streams, Functional Programming concepts, and Optional for safe null/empty handling.
 */
public class StudentStreamProcessor {

    // Student model class representing a student record
    static class Student {
        private final int id;
        private final String name;
        private final String department;
        private final double marks;

        public Student(int id, String name, String department, double marks) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.marks = marks;
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

        public double getMarks() {
            return marks;
        }

        @Override
        public String toString() {
            return String.format("Student[ID=%-3d | Name=%-18s | Dept=%-15s | Marks=%6.2f]",
                    id, name, department, marks);
        }
    }

    /**
     * Analyzes and displays performance statistics for a given list of students.
     * Demonstrates how Optional protects against NoSuchElementException when the list is empty.
     */
    public static void analyzeStudentCohort(String cohortTitle, List<Student> students) {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("  COHORT: " + cohortTitle + " (Total Students: " + students.size() + ")");
        System.out.println("--------------------------------------------------------------------------");

        if (students.isEmpty()) {
            System.out.println("Notice: Cohort is currently empty. Testing Optional safety handlers...");
        }

        // ---------------------------------------------------------------------
        // 1. Filter Top-Performing Students (Marks >= 80.0)
        // ---------------------------------------------------------------------
        List<Student> topPerformers = students.stream()
                .filter(s -> s.getMarks() >= 80.0)
                .collect(Collectors.toList());

        System.out.println("\n[1] Top-Performing Students (Marks >= 80.0):");
        if (topPerformers.isEmpty()) {
            System.out.println("    No top-performing students found.");
        } else {
            topPerformers.forEach(s -> System.out.println("    " + s));
        }

        // ---------------------------------------------------------------------
        // 2. Sort Students by Marks in Descending Order
        // ---------------------------------------------------------------------
        List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .collect(Collectors.toList());

        System.out.println("\n[2] Students Ranked by Marks (Descending Order):");
        if (sortedStudents.isEmpty()) {
            System.out.println("    No students to display.");
        } else {
            for (int i = 0; i < sortedStudents.size(); i++) {
                System.out.printf("    Rank %2d: %s%n", (i + 1), sortedStudents.get(i));
            }
        }

        // ---------------------------------------------------------------------
        // 3. Calculate Average Marks using Streams and OptionalDouble
        // ---------------------------------------------------------------------
        OptionalDouble averageMarksOpt = students.stream()
                .mapToDouble(Student::getMarks)
                .average();

        // ---------------------------------------------------------------------
        // 4. Find Highest Marks and Top Student using Optional<Student>
        // ---------------------------------------------------------------------
        Optional<Student> topStudentOpt = students.stream()
                .max(Comparator.comparingDouble(Student::getMarks));

        // ---------------------------------------------------------------------
        // 5. Find Lowest Marks using Optional<Student>
        // ---------------------------------------------------------------------
        Optional<Student> lowestStudentOpt = students.stream()
                .min(Comparator.comparingDouble(Student::getMarks));

        // Safely extract values using Optional methods (orElse / orElseGet / ifPresent)
        System.out.println("\n[3] Statistical Performance Summary (Safe Optional Extraction):");

        String avgStr = averageMarksOpt.isPresent()
                ? String.format("%.2f", averageMarksOpt.getAsDouble())
                : "N/A (List is empty)";
        System.out.println("    Class Average Marks : " + avgStr);

        topStudentOpt.ifPresentOrElse(
                top -> System.out.printf("    Highest Mark Record : %.2f scored by %s (%s)%n",
                        top.getMarks(), top.getName(), top.getDepartment()),
                () -> System.out.println("    Highest Mark Record : N/A (No student records present)")
        );

        lowestStudentOpt.ifPresentOrElse(
                low -> System.out.printf("    Lowest Mark Record  : %.2f scored by %s (%s)%n",
                        low.getMarks(), low.getName(), low.getDepartment()),
                () -> System.out.println("    Lowest Mark Record  : N/A (No student records present)")
        );
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("        STUDENT PERFORMANCE ANALYSIS WITH JAVA 8 STREAMS & OPTIONAL       ");
        System.out.println("==========================================================================");

        // Populate student records
        List<Student> activeCohort = Arrays.asList(
                new Student(101, "Vinay Madarkhandi", "Computer Science", 92.5),
                new Student(102, "Alice Johnson",     "Information Tech", 78.0),
                new Student(103, "Bob Smith",         "Computer Science", 64.5),
                new Student(104, "Charlie Brown",     "Electronics",      85.0),
                new Student(105, "Diana Prince",      "Information Tech", 96.0),
                new Student(106, "Evan Wright",       "Computer Science", 49.5),
                new Student(107, "Fiona Gallagher",   "Electronics",      88.5),
                new Student(108, "George Clark",      "Mechanical",       71.0)
        );

        // Run analysis on populated cohort
        analyzeStudentCohort("Fall Semester 2026 Batch", activeCohort);

        // Run analysis on empty list to explicitly demonstrate Optional safety
        List<Student> emptyCohort = new ArrayList<>();
        analyzeStudentCohort("Unregistered Winter Batch (Empty List)", emptyCohort);

        System.out.println("\n==========================================================================");
        System.out.println("Student stream processing and performance analysis completed!");
        System.out.println("==========================================================================");
    }
}
