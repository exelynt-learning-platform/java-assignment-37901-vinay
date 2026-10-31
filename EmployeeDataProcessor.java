import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * EmployeeDataProcessor demonstrates functional programming in Java 8+:
 * 1. Lambda Expressions: Concise function implementations.
 * 2. Streams API: Functional pipelining with filter(), map(), and reduce().
 * 3. Optional Class: Safe handling of nullable fields and missing data.
 * 4. Method References: Shorthand syntax for lambda expressions.
 * 5. Default & Static Interface Methods: Interface backward compatibility & utilities.
 * 6. Date & Time API (java.time): Immutable LocalDate, Period calculation, and DateTimeFormatter.
 */
public class EmployeeDataProcessor {

    // =========================================================================
    // FEATURE 5: Default and Static Methods in Interfaces
    // =========================================================================
    @FunctionalInterface
    interface EmployeeEvaluator {
        // Single Abstract Method (SAM)
        boolean test(Employee e);

        // Default method: Can be inherited or overridden without breaking implementations
        default String formatStatus(Employee e) {
            return String.format("[Status Verified] %s works in %s", e.getName(), e.getDepartment());
        }

        // Static method: Utility function bound to the interface
        static void printSectionBanner(String banner) {
            System.out.println("\n--------------------------------------------------------------------------");
            System.out.println("  " + banner);
            System.out.println("--------------------------------------------------------------------------");
        }
    }

    // =========================================================================
    // Employee Model Class
    // =========================================================================
    static class Employee {
        private final int id;
        private final String name;
        private double salary;
        private final String department;
        private final LocalDate joiningDate;
        private final String email; // Optional/nullable field

        public Employee(int id, String name, double salary, String department, LocalDate joiningDate, String email) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.department = department;
            this.joiningDate = joiningDate;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public LocalDate getJoiningDate() {
            return joiningDate;
        }

        // FEATURE 3: Using Optional to return nullable email safely
        public Optional<String> getEmail() {
            return Optional.ofNullable(email);
        }

        // FEATURE 6: Date & Time API calculating experience via Period
        public Period calculateExperience(LocalDate referenceDate) {
            return Period.between(joiningDate, referenceDate);
        }

        @Override
        public String toString() {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            return String.format("Employee[ID=%-3d | Name=%-18s | Dept=%-12s | Salary=$%-9.2f | Joined=%s]",
                    id, name, department, salary, joiningDate.format(fmt));
        }
    }

    // Helper method to find an employee by ID using Optional
    public static Optional<Employee> findEmployeeById(List<Employee> employees, int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("        EMPLOYEE DATA PROCESSOR (JAVA 8+ FUNCTIONAL PROGRAMMING)          ");
        System.out.println("==========================================================================");

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        // Populate Employee records with various joining dates and departments
        List<Employee> employeeList = Arrays.asList(
                new Employee(101, "Vinay Madarkhandi", 85000.0, "Engineering", LocalDate.of(2021, 3, 15), "vinay@example.com"),
                new Employee(102, "Alice Johnson",     72000.0, "Marketing",   LocalDate.of(2022, 6, 1),  null), // Missing email
                new Employee(103, "Bob Smith",         92000.0, "Engineering", LocalDate.of(2019, 1, 10), "bob@example.com"),
                new Employee(104, "Charlie Brown",     60000.0, "HR",          LocalDate.of(2023, 8, 20), null), // Missing email
                new Employee(105, "Diana Prince",      98000.0, "Engineering", LocalDate.of(2018, 11, 5), "diana@example.com"),
                new Employee(106, "Evan Wright",       68000.0, "Marketing",   LocalDate.of(2020, 4, 12), "evan@example.com"),
                new Employee(107, "Fiona Gallagher",   89000.0, "Engineering", LocalDate.of(2022, 9, 1),  "fiona@example.com")
        );

        // ---------------------------------------------------------------------
        // 1. Display All Employees & Calculate Experience with Java Time API
        // ---------------------------------------------------------------------
        EmployeeEvaluator.printSectionBanner("1. ALL EMPLOYEES & EXPERIENCE CALCULATION (Date & Time API)");
        for (Employee emp : employeeList) {
            Period exp = emp.calculateExperience(today);
            System.out.printf("%s | Experience: %d yrs, %d mos%n",
                    emp, exp.getYears(), exp.getMonths());
        }

        // ---------------------------------------------------------------------
        // 2. Filter Employees by Department using Lambda & Streams
        // ---------------------------------------------------------------------
        String targetDept = "Engineering";
        EmployeeEvaluator.printSectionBanner("2. FILTER EMPLOYEES BY DEPARTMENT: " + targetDept);

        // Lambda expression (e -> e.getDepartment().equalsIgnoreCase(targetDept))
        List<Employee> engineeringEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(targetDept))
                .collect(Collectors.toList());

        // FEATURE 4: Method reference System.out::println
        engineeringEmployees.forEach(System.out::println);
        System.out.println("Total Engineering Staff: " + engineeringEmployees.size());

        // ---------------------------------------------------------------------
        // 3. Increase Salary using map()
        // ---------------------------------------------------------------------
        EmployeeEvaluator.printSectionBanner("3. SALARY INCREASE USING map() (10% Increment)");

        // Map existing salaries to increased salaries (+10%)
        List<Double> boostedSalaries = engineeringEmployees.stream()
                .map(e -> e.getSalary() * 1.10) // Lambda expression transforming salary
                .collect(Collectors.toList());

        for (int i = 0; i < engineeringEmployees.size(); i++) {
            System.out.printf("  Employee: %-18s | Current Salary: $%-9.2f | New Salary (+10%%): $%-9.2f%n",
                    engineeringEmployees.get(i).getName(),
                    engineeringEmployees.get(i).getSalary(),
                    boostedSalaries.get(i));
        }

        // ---------------------------------------------------------------------
        // 4. Calculate Average Salary using reduce()
        // ---------------------------------------------------------------------
        EmployeeEvaluator.printSectionBanner("4. CALCULATE AVERAGE SALARY USING reduce()");

        // Reduce stream to calculate sum of boosted salaries
        // Method reference Double::sum used in binary accumulator
        double totalSalary = boostedSalaries.stream()
                .reduce(0.0, Double::sum);

        double averageSalary = boostedSalaries.isEmpty() ? 0.0 : totalSalary / boostedSalaries.size();

        System.out.printf("  Total Cumulative Salary (Boosted) : $%,.2f%n", totalSalary);
        System.out.printf("  Average Salary (Boosted)          : $%,.2f%n", averageSalary);

        // ---------------------------------------------------------------------
        // 5. Handling Missing Data Safely using Optional
        // ---------------------------------------------------------------------
        EmployeeEvaluator.printSectionBanner("5. SAFE HANDLING OF MISSING DATA USING Optional");

        // Checking missing email using Optional.orElse()
        System.out.println("Auditing Employee Email Contacts:");
        for (Employee emp : employeeList) {
            String contactEmail = emp.getEmail().orElse("NO_EMAIL_ON_RECORD");
            System.out.printf("  %-18s -> Email: %s%n", emp.getName(), contactEmail);
        }

        // Searching for existing and non-existing employees
        System.out.println("\nQuerying Employee by ID using Optional:");
        Optional<Employee> foundEmp = findEmployeeById(employeeList, 101);
        foundEmp.ifPresent(e -> System.out.println("  ID 101 Found: " + e.getName()));

        Optional<Employee> missingEmp = findEmployeeById(employeeList, 999);
        String missingResult = missingEmp.map(Employee::getName).orElse("Employee not found in records");
        System.out.println("  ID 999 Lookup Result: " + missingResult);

        // ---------------------------------------------------------------------
        // 6. Default and Static Methods Execution
        // ---------------------------------------------------------------------
        EmployeeEvaluator.printSectionBanner("6. INTERFACE DEFAULT & STATIC METHOD DEMO");

        // Functional interface implemented via lambda
        EmployeeEvaluator seniorFilter = emp -> emp.calculateExperience(today).getYears() >= 3;

        for (Employee emp : employeeList) {
            if (seniorFilter.test(emp)) {
                // Calling the interface default method formatStatus()
                System.out.println("  [Senior Staff] " + seniorFilter.formatStatus(emp));
            }
        }

        System.out.println("\n==========================================================================");
        System.out.println("Employee data processing completed successfully!");
        System.out.println("==========================================================================");
    }
}
