import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JdbcExceptionDemo demonstrates robust error handling in JDBC:
 * 1. Handling SQLException and subclasses (SQLSyntaxErrorException, SQLIntegrityConstraintViolationException).
 * 2. Automatic resource management using try-with-resources to prevent database leaks.
 * 3. Extracting rich diagnostic error properties:
 *    - Message: Human-readable error description (e.getMessage()).
 *    - SQLState: Standardized 5-character X/Open or SQL:2003 error code (e.getSQLState()).
 *    - Vendor ErrorCode: Database-specific error integer code (e.getErrorCode()).
 *    - Chained Exceptions: Iterating through e.getNextException().
 */
public class JdbcExceptionDemo {

    private static final String DB_URL = "jdbc:sqlite:exception_demo.db";

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       JDBC EXCEPTION HANDLING & TRY-WITH-RESOURCES DEMO                  ");
        System.out.println("==========================================================================");

        // Setup base table in SQLite
        initializeDatabase();

        // ---------------------------------------------------------------------
        // Scenario 1: Handling SQL Syntax / Non-Existent Table Error
        // ---------------------------------------------------------------------
        System.out.println("\n--- Scenario 1: Querying a Non-Existent Table ---");
        simulateMissingTableError();

        // ---------------------------------------------------------------------
        // Scenario 2: Handling Primary Key / Constraint Violation Error
        // ---------------------------------------------------------------------
        System.out.println("\n--- Scenario 2: Primary Key Constraint Violation (Duplicate ID) ---");
        simulateDuplicateKeyError();

        // ---------------------------------------------------------------------
        // Scenario 3: Handling Invalid Connection String / Driver Error
        // ---------------------------------------------------------------------
        System.out.println("\n--- Scenario 3: Invalid Database URL / Unreachable DB ---");
        simulateInvalidConnectionError();

        // ---------------------------------------------------------------------
        // Scenario 4: Successful Query with Guaranteed Resource Closure
        // ---------------------------------------------------------------------
        System.out.println("\n--- Scenario 4: Clean Execution with Try-With-Resources ---");
        executeValidQuery();

        System.out.println("\n==========================================================================");
        System.out.println("All exceptions were handled gracefully. The application did not crash!");
        System.out.println("==========================================================================");
    }

    /**
     * Scenario 1: Catches error when executing query against a missing table.
     */
    private static void simulateMissingTableError() {
        String invalidSql = "SELECT * FROM non_existent_students_table";

        // Try-with-resources ensures Connection and Statement close even when SQLException occurs
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(invalidSql)) {

            while (rs.next()) {
                System.out.println("Found student: " + rs.getString("name"));
            }

        } catch (SQLException e) {
            printDetailedSqlException("Querying Non-Existent Table", e);
        }
    }

    /**
     * Scenario 2: Catches error when inserting a duplicate primary key.
     */
    private static void simulateDuplicateKeyError() {
        String insertSql = "INSERT INTO students (id, name, marks) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            // First insertion (Succeeds)
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, 101);
                ps.setString(2, "Vinay Madarkhandi");
                ps.setDouble(3, 95.0);
                ps.executeUpdate();
                System.out.println("  [SUCCESS] First record inserted successfully (ID: 101).");
            }

            // Second insertion with identical ID 101 (Fails with constraint violation)
            System.out.println("  Attempting to insert duplicate record with duplicate primary key ID: 101...");
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, 101); // DUPLICATE KEY
                ps.setString(2, "Duplicate Student");
                ps.setDouble(3, 80.0);
                ps.executeUpdate();
            } catch (SQLException e) {
                printDetailedSqlException("Primary Key Duplicate Insertion", e);
            }

        } catch (SQLException e) {
            printDetailedSqlException("Connection Failure in Duplicate Key Test", e);
        }
    }

    /**
     * Scenario 3: Catches error when connection URL is invalid.
     */
    private static void simulateInvalidConnectionError() {
        String badUrl = "jdbc:invalid_protocol://localhost:9999/fakename";

        try (Connection conn = DriverManager.getConnection(badUrl)) {
            System.out.println("Connected: " + conn);
        } catch (SQLException e) {
            printDetailedSqlException("Invalid JDBC Protocol / Connection", e);
        }
    }

    /**
     * Scenario 4: Demonstrates clean execution using try-with-resources.
     */
    private static void executeValidQuery() {
        String sql = "SELECT id, name, marks FROM students ORDER BY id ASC";

        // Nested/chained try-with-resources guarantees LIFO closing order:
        // ResultSet closed -> Statement closed -> Connection closed
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("  Registered Student Records in Database:");
            while (rs.next()) {
                System.out.printf("  - ID: %-3d | Name: %-20s | Marks: %.2f%n",
                        rs.getInt("id"), rs.getString("name"), rs.getDouble("marks"));
            }
            System.out.println("  [INFO] try-with-resources verified: Connection, Statement, and ResultSet closed safely.");

        } catch (SQLException e) {
            printDetailedSqlException("Valid Query Execution", e);
        }
    }

    /**
     * Prints comprehensive and meaningful diagnostic messages for any SQLException.
     */
    private static void printDetailedSqlException(String context, SQLException ex) {
        System.err.println("  [CAUGHT EXCEPTION] In: " + context);
        System.err.println("  Exception Type : " + ex.getClass().getName());
        System.err.println("  Error Message  : " + ex.getMessage());
        System.err.println("  SQLState       : " + (ex.getSQLState() != null ? ex.getSQLState() : "N/A"));
        System.err.println("  Error Code     : " + ex.getErrorCode());

        // Check for chained exceptions
        SQLException next = ex.getNextException();
        while (next != null) {
            System.err.println("  Chained Cause  : " + next.getMessage());
            next = next.getNextException();
        }
    }

    /**
     * Initializes a clean SQLite table for testing.
     */
    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS students ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT NOT NULL, "
                    + "marks REAL NOT NULL)");

            stmt.executeUpdate("DELETE FROM students"); // Clean for fresh run

        } catch (SQLException e) {
            System.err.println("Initialization error: " + e.getMessage());
        }
    }
}
