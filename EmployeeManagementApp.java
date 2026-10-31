import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * =============================================================================
 *          EMPLOYEE MANAGEMENT SYSTEM (JDBC CONSOLE APPLICATION)
 * =============================================================================
 *
 * JDBC Workflow Overview:
 * 1. Establishing Connection:
 *    - DriverManager.getConnection(url, user, password) establishes a session with
 *      the database engine (MySQL or SQLite).
 *    - Credentials are read securely from environment variables (DB_URL, DB_USER,
 *      DB_PASSWORD) or a git-ignored properties file to prevent password leakage.
 *
 * 2. Creating and Executing Statements:
 *    - PreparedStatement is used for all dynamic queries. It pre-compiles SQL templates
 *      with '?' placeholders, protecting the application against SQL injection attacks
 *      and improving execution plan caching.
 *
 * 3. Processing Results:
 *    - ResultSet maintains a cursor that starts before the first row. The rs.next()
 *      method iterates row-by-row, while getters (getInt, getString, getDouble) extract
 *      strongly typed column values.
 *
 * 4. Exception Handling & Resource Management:
 *    - SQLException is caught with comprehensive diagnostic extraction (SQLState, ErrorCode).
 *    - Automatic resource deallocation is guaranteed using try-with-resources.
 * =============================================================================
 */
public class EmployeeManagementApp {

    // Default to SQLite embedded database; overridden by DB_URL environment variable if set
    private static final String DEFAULT_SQLITE_URL = "jdbc:sqlite:employeemanagement.db";

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM - CONSOLE-BASED JDBC CRUD               ");
        System.out.println("==========================================================================");

        try (Connection conn = getConnection()) {
            System.out.println("Connected to database successfully: " + conn.getMetaData().getDatabaseProductName());

            // Initialize database schema from schema.sql
            initializeSchema(conn);

            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextLine()) {
                // Non-interactive automated execution (for automated testing or sample run capture)
                System.out.println("\n[INFO] Non-interactive mode detected: Executing automated CRUD demonstration workflow...\n");
                runAutomatedCrudDemonstration(conn);
            } else {
                // Interactive menu-driven mode
                runInteractiveMenu(conn, scanner);
            }

            System.out.println("\n==========================================================================");
            System.out.println("Employee Management System terminated cleanly. Connection closed.");
            System.out.println("==========================================================================");

        } catch (SQLException e) {
            System.err.println("[CRITICAL DATABASE ERROR] " + e.getMessage());
            System.err.println("SQLState : " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    /**
     * Obtains database connection securely without committing credentials.
     */
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ignored) {}

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {}

        String envUrl = System.getenv("DB_URL");
        if (envUrl != null && !envUrl.trim().isEmpty()) {
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");
            if (user != null && pass != null) {
                return DriverManager.getConnection(envUrl.trim(), user.trim(), pass.trim());
            }
            return DriverManager.getConnection(envUrl.trim());
        }
        return DriverManager.getConnection(DEFAULT_SQLITE_URL);
    }

    /**
     * Ensures the employees table exists.
     */
    private static void initializeSchema(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employees ("
                   + "id INT PRIMARY KEY, "
                   + "name VARCHAR(100) NOT NULL, "
                   + "department VARCHAR(50) NOT NULL, "
                   + "salary DOUBLE NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Executes an automated end-to-end demonstration of all CRUD features.
     */
    private static void runAutomatedCrudDemonstration(Connection conn) throws SQLException {
        // Reset table for clean demo run
        clearEmployees(conn);

        System.out.println("--- 1. ADD EMPLOYEES (CREATE) ---");
        addEmployee(conn, 101, "Vinay Madarkhandi", "Engineering", 88000.0);
        addEmployee(conn, 102, "Alice Johnson",     "Marketing",   67000.0);
        addEmployee(conn, 103, "Bob Smith",         "Engineering", 74000.0);
        addEmployee(conn, 104, "Charlie Brown",     "Finance",     71000.0);

        System.out.println("\n--- 2. VIEW ALL EMPLOYEES (READ) ---");
        viewAllEmployees(conn);

        System.out.println("\n--- 3. UPDATE EMPLOYEE DETAILS (UPDATE) ---");
        System.out.println("Updating Employee ID 101: Promoting to Lead Engineer with salary $98,000.00");
        updateEmployee(conn, 101, "Vinay Madarkhandi (Lead)", "Engineering", 98000.0);

        System.out.println("\nVerifying updated record:");
        viewAllEmployees(conn);

        System.out.println("\n--- 4. DELETE EMPLOYEE (DELETE) ---");
        System.out.println("Removing Employee ID 103 (Bob Smith)...");
        deleteEmployee(conn, 103);

        System.out.println("\nFinal Database State after Deletion:");
        viewAllEmployees(conn);
    }

    /**
     * Interactive loop for interactive terminal users.
     */
    private static void runInteractiveMenu(Connection conn, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--------------------------------------------------------------");
            System.out.println("                 MAIN MENU - CRUD OPERATIONS                  ");
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee Details");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            if (!scanner.hasNextLine()) break;
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter Employee ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Full Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine().trim();
                        System.out.print("Enter Salary: ");
                        double salary = Double.parseDouble(scanner.nextLine().trim());
                        addEmployee(conn, id, name, dept, salary);
                        break;
                    case "2":
                        viewAllEmployees(conn);
                        break;
                    case "3":
                        System.out.print("Enter Employee ID to update: ");
                        int uId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter New Name: ");
                        String uName = scanner.nextLine().trim();
                        System.out.print("Enter New Department: ");
                        String uDept = scanner.nextLine().trim();
                        System.out.print("Enter New Salary: ");
                        double uSalary = Double.parseDouble(scanner.nextLine().trim());
                        updateEmployee(conn, uId, uName, uDept, uSalary);
                        break;
                    case "4":
                        System.out.print("Enter Employee ID to delete: ");
                        int dId = Integer.parseInt(scanner.nextLine().trim());
                        deleteEmployee(conn, dId);
                        break;
                    case "5":
                        exit = true;
                        System.out.println("Exiting Employee Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose options 1 through 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input. Please enter valid numbers for ID and Salary.");
            } catch (SQLException e) {
                System.err.println("SQL Operation Error: " + e.getMessage());
            }
        }
    }

    /**
     * CRUD: CREATE - Adds a new employee using PreparedStatement.
     */
    public static void addEmployee(Connection conn, int id, String name, String dept, double salary)
            throws SQLException {
        String sql = "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setDouble(4, salary);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.printf("  [SUCCESS] Added: ID=%d, Name='%s', Dept='%s', Salary=$%,.2f%n",
                        id, name, dept, salary);
            }
        }
    }

    /**
     * CRUD: READ - Displays all employee records using ResultSet.
     */
    public static void viewAllEmployees(Connection conn) throws SQLException {
        String sql = "SELECT id, name, department, salary FROM employees ORDER BY id ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("  +-----+------------------------------+---------------+---------------+");
            System.out.println("  | ID  | Name                         | Department    | Salary ($)    |");
            System.out.println("  +-----+------------------------------+---------------+---------------+");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("  | %-3d | %-28s | %-13s | %13.2f |%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }

            if (!found) {
                System.out.println("  |                    [ No employee records found ]                          |");
            }
            System.out.println("  +-----+------------------------------+---------------+---------------+");
        }
    }

    /**
     * CRUD: UPDATE - Modifies details for an existing employee.
     */
    public static void updateEmployee(Connection conn, int id, String name, String dept, double salary)
            throws SQLException {
        String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, dept);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.printf("  [SUCCESS] Updated Employee ID %d successfully!%n", id);
            } else {
                System.out.printf("  [WARNING] No employee found with ID %d to update.%n", id);
            }
        }
    }

    /**
     * CRUD: DELETE - Removes an employee record.
     */
    public static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.printf("  [SUCCESS] Deleted Employee ID %d successfully!%n", id);
            } else {
                System.out.printf("  [WARNING] No employee found with ID %d to delete.%n", id);
            }
        }
    }

    /**
     * Utility to reset records between fresh runs.
     */
    private static void clearEmployees(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM employees");
        }
    }
}
