import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JdbcCrudDemo demonstrates performing complete CRUD (Create, Read, Update, Delete)
 * database operations using JDBC in Java.
 *
 * Operations:
 * 1. CREATE TABLE: Sets up the schema defined in schema.sql.
 * 2. INSERT (Create): Adds new employee records using parameterized queries.
 * 3. SELECT (Read): Queries and displays records using ResultSet.
 * 4. UPDATE (Update): Modifies an existing employee's salary.
 * 5. DELETE (Delete): Removes an employee record from the database.
 */
public class JdbcCrudDemo {

    // Default connection parameters (supports SQLite by default, MySQL if env configured)
    private static String getJdbcUrl() {
        String envUrl = System.getenv("DB_URL");
        return (envUrl != null && !envUrl.trim().isEmpty()) ? envUrl.trim() : "jdbc:sqlite:employees.db";
    }

    private static Connection getConnection() throws SQLException {
        String url = getJdbcUrl();
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASSWORD");

        if (user != null && pass != null && !user.isEmpty()) {
            return DriverManager.getConnection(url, user, pass);
        }
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       JDBC CRUD OPERATIONS DEMONSTRATION (EMPLOYEE MANAGEMENT)           ");
        System.out.println("==========================================================================");
        System.out.println("Database URL: " + getJdbcUrl());

        try (Connection conn = getConnection()) {
            System.out.println("Database connection established successfully.\n");

            // Step 1: Ensure table exists
            setupTable(conn);

            // Clean table for a fresh repeatable run
            clearTable(conn);

            // Step 2: CREATE (INSERT)
            System.out.println("--- 1. INSERT OPERATION (Create) ---");
            insertEmployee(conn, 101, "Vinay Madarkhandi", "Engineering", 85000.0, "vinay@example.com");
            insertEmployee(conn, 102, "Alice Johnson",     "Marketing",   65000.0, "alice@example.com");
            insertEmployee(conn, 103, "Bob Smith",         "Engineering", 72000.0, "bob@example.com");
            insertEmployee(conn, 104, "Charlie Brown",     "Finance",     68000.0, "charlie@example.com");

            // Step 3: READ (SELECT)
            System.out.println("\n--- 2. SELECT OPERATION (Read Initial Records) ---");
            displayEmployees(conn);

            // Step 4: UPDATE
            System.out.println("\n--- 3. UPDATE OPERATION (Update Employee 101 Salary) ---");
            updateEmployeeSalary(conn, 101, 95000.0);
            displayEmployees(conn);

            // Step 5: DELETE
            System.out.println("\n--- 4. DELETE OPERATION (Delete Employee 103) ---");
            deleteEmployee(conn, 103);
            displayEmployees(conn);

            System.out.println("\n==========================================================================");
            System.out.println("All JDBC CRUD operations executed successfully!");
            System.out.println("==========================================================================");

        } catch (SQLException e) {
            System.err.println("Database Error during CRUD execution: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates the employees table if it does not already exist.
     */
    private static void setupTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employees ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "department VARCHAR(50) NOT NULL, "
                + "salary DOUBLE NOT NULL, "
                + "email VARCHAR(100))";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("[Schema] 'employees' table ready.");
        }
    }

    /**
     * Clears existing records to ensure fresh demo state.
     */
    private static void clearTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM employees");
        }
    }

    /**
     * INSERT: Inserts an employee record into the database using PreparedStatement.
     */
    private static void insertEmployee(Connection conn, int id, String name, String dept, double salary, String email)
            throws SQLException {
        String sql = "INSERT INTO employees (id, name, department, salary, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setDouble(4, salary);
            pstmt.setString(5, email);

            int rowsInserted = pstmt.executeUpdate();
            System.out.printf("  [INSERT] Added ID %d: %-18s | Salary: $%,.2f (Rows affected: %d)%n",
                    id, name, salary, rowsInserted);
        }
    }

    /**
     * SELECT: Retrieves and displays all records from the employees table.
     */
    private static void displayEmployees(Connection conn) throws SQLException {
        String sql = "SELECT id, name, department, salary, email FROM employees ORDER BY id ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("  +-----+--------------------+---------------+--------------+--------------------+");
            System.out.println("  | ID  | Name               | Department    | Salary ($)   | Email              |");
            System.out.println("  +-----+--------------------+---------------+--------------+--------------------+");

            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double salary = rs.getDouble("salary");
                String email = rs.getString("email");

                System.out.printf("  | %-3d | %-18s | %-13s | %12.2f | %-18s |%n",
                        id, name, dept, salary, (email != null ? email : "N/A"));
            }

            if (!hasRows) {
                System.out.println("  |                   [ No employee records found ]                          |");
            }
            System.out.println("  +-----+--------------------+---------------+--------------+--------------------+");
        }
    }

    /**
     * UPDATE: Updates the salary for a specific employee ID.
     */
    private static void updateEmployeeSalary(Connection conn, int id, double newSalary) throws SQLException {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();
            System.out.printf("  [UPDATE] Updated ID %d salary to $%,.2f (Rows affected: %d)%n",
                    id, newSalary, rowsUpdated);
        }
    }

    /**
     * DELETE: Deletes an employee record by ID.
     */
    private static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            System.out.printf("  [DELETE] Removed Employee ID %d (Rows affected: %d)%n",
                    id, rowsDeleted);
        }
    }
}
