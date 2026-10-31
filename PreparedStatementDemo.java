import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * =============================================================================
 *                STATEMENT VS. PREPAREDSTATEMENT IN JDBC
 * =============================================================================
 *
 * 1. Statement:
 *    - Executes static SQL statements directly without pre-compilation.
 *    - Relies on String concatenation to embed user values:
 *      String sql = "INSERT INTO users (username, email) VALUES ('" + userInput + "', ...)";
 *    - Re-compiled and re-parsed by the database engine on every single execution.
 *
 *    * THE SQL INJECTION RISK:
 *      Suppose a login check is written using Statement:
 *      String sql = "SELECT * FROM users WHERE username = '" + user + "' AND password = '" + pass + "'";
 *      If an attacker inputs:
 *          user: admin' OR '1'='1
 *          pass: anything
 *      The resulting SQL query becomes:
 *          SELECT * FROM users WHERE username = 'admin' OR '1'='1' AND password = '...'
 *      Because '1'='1' is always true, the WHERE condition succeeds, allowing the attacker
 *      to bypass authentication without a valid password!
 *      Similarly, malicious input like "'; DROP TABLE users; --" can destroy database tables.
 *
 * 2. PreparedStatement:
 *    - Pre-compiles the SQL query structure once in the database engine.
 *    - Uses parameterized placeholders (?) for dynamic values:
 *      String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
 *    - Input values are bound using type-safe setters (setString, setInt, etc.).
 *    - The database treats parameter values strictly as DATA LITERALS, never as
 *      executable SQL commands. Even if the user inputs "' OR '1'='1", it is treated
 *      as the exact string value, completely neutralizing SQL Injection attacks.
 *    - Higher performance for recurring queries due to pre-compilation and plan caching.
 * =============================================================================
 */
public class PreparedStatementDemo {

    private static final String DB_URL = "jdbc:sqlite:security_demo.db";

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("        JDBC SECURITY: STATEMENT VS. PREPAREDSTATEMENT                    ");
        System.out.println("==========================================================================");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Setup demo table
            setupDatabase(conn);

            // -----------------------------------------------------------------
            // Step 1: Explain Vulnerable Statement via Commented Example
            // -----------------------------------------------------------------
            /*
             * ================================================================
             * COMMENTED COMPARISON EXAMPLE: VULNERABLE STATEMENT USAGE
             * ================================================================
             * try (Statement stmt = conn.createStatement()) {
             *     String dangerousInput = "hacker'; DROP TABLE users; --";
             *     // String concatenation makes this dangerously vulnerable:
             *     String badSql = "INSERT INTO users (username, email) VALUES ('"
             *                     + dangerousInput + "', 'bad@example.com')";
             *     stmt.executeUpdate(badSql); // DANGER: Executes arbitrary SQL commands!
             * }
             * ================================================================
             */
            System.out.println("\n[1] Conceptual Comparison:");
            System.out.println("    Statement         : Vulnerable to SQL injection via string concatenation.");
            System.out.println("    PreparedStatement : Immune to SQL injection via parameterized placeholders (?).");

            // -----------------------------------------------------------------
            // Step 2: Accept User Input (with non-interactive fallback)
            // -----------------------------------------------------------------
            Scanner scanner = new Scanner(System.in);
            String inputUsername = "Vinay Madarkhandi";
            String inputEmail = "vinay.madarkhandi@example.com";

            System.out.println("\n[2] Reading User Input for Registration:");
            System.out.print("Enter username (or press enter for default): ");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    inputUsername = line;
                }
            }

            System.out.print("Enter email (or press enter for default): ");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    inputEmail = line;
                }
            }

            // -----------------------------------------------------------------
            // Step 3: Insert User Input Safely using PreparedStatement
            // -----------------------------------------------------------------
            System.out.println("\n[3] Safely Inserting User Input via PreparedStatement:");
            insertUserSafely(conn, inputUsername, inputEmail);

            // -----------------------------------------------------------------
            // Step 4: Test PreparedStatement with SQL Injection Payload
            // -----------------------------------------------------------------
            System.out.println("\n[4] Testing PreparedStatement with SQL Injection Attack String:");
            String attackPayload = "admin' OR '1'='1' --";
            String attackEmail = "attacker@exploit.com";
            System.out.println("    Attempting injection payload: " + attackPayload);
            insertUserSafely(conn, attackPayload, attackEmail);

            // -----------------------------------------------------------------
            // Step 5: Query and Display Table Contents
            // -----------------------------------------------------------------
            System.out.println("\n[5] Displaying Table Contents to Verify Parameterized Safety:");
            displayUsers(conn);

            System.out.println("\nNotice: The injection payload was safely treated as a pure text literal");
            System.out.println("        in the database, completely preventing SQL command execution!");

            System.out.println("\n==========================================================================");
            System.out.println("PreparedStatement demonstration completed successfully!");
            System.out.println("==========================================================================");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initializes the demo database table.
     */
    private static void setupDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT NOT NULL, "
                    + "email TEXT NOT NULL)");
            stmt.executeUpdate("DELETE FROM users"); // Clear table for fresh run
        }
    }

    /**
     * Inserts a user safely into the database using PreparedStatement with placeholders (?).
     */
    private static void insertUserSafely(Connection conn, String username, String email) throws SQLException {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";

        // PreparedStatement pre-compiles query template
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Type-safe parameter binding neutralizes SQL Injection
            pstmt.setString(1, username);
            pstmt.setString(2, email);

            int rowsAffected = pstmt.executeUpdate();
            System.out.printf("    [SAFE INSERT] Added user: '%s' | Rows Affected: %d%n",
                    username, rowsAffected);
        }
    }

    /**
     * Displays all registered users.
     */
    private static void displayUsers(Connection conn) throws SQLException {
        String sql = "SELECT id, username, email FROM users ORDER BY id ASC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("    +----+-----------------------------+----------------------------+");
            System.out.println("    | ID | Username                    | Email                      |");
            System.out.println("    +----+-----------------------------+----------------------------+");

            while (rs.next()) {
                System.out.printf("    | %-2d | %-27s | %-26s |%n",
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"));
            }
            System.out.println("    +----+-----------------------------+----------------------------+");
        }
    }
}
