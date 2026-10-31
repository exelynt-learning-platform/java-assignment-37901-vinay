import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * EmployeeReportGenerator connects to a relational database using JDBC
 * and generates structured analytical reports from employee records:
 * 1. Comprehensive Employee Directory (All Employees)
 * 2. High-Earner Salary Audit (Employees earning above a threshold)
 * 3. Departmental Grouping & Aggregation Report
 *
 * Emphasizes clean SELECT queries, parameterized filters, ResultSet iteration,
 * and try-catch exception handling.
 */
public class EmployeeReportGenerator {

    private static final String DB_URL = "jdbc:sqlite:reports_demo.db";

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("             DATABASE REPORTING TOOL: EMPLOYEE ANALYTICS                  ");
        System.out.println("==========================================================================");

        try (Connection conn = getConnection()) {
            System.out.println("Connected to database successfully.\n");

            // Seed demonstration table with sample records
            initializeData(conn);

            // Report 1: Display all employees
            generateAllEmployeesReport(conn);

            // Report 2: Display employees earning above a given threshold
            double threshold = 75000.0;
            generateHighEarnersReport(conn, threshold);

            // Report 3: Display employees grouped by department (with department statistics)
            generateDepartmentGroupedReport(conn);

            System.out.println("\n==========================================================================");
            System.out.println("All analytical reports generated successfully!");
            System.out.println("==========================================================================");

        } catch (SQLException e) {
            System.err.println("[ERROR] Database Reporting Error: " + e.getMessage());
            System.err.println("SQLState : " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    /**
     * Establishes database connection using JDBC.
     */
    private static Connection getConnection() throws SQLException {
        String envUrl = System.getenv("DB_URL");
        if (envUrl != null && !envUrl.trim().isEmpty()) {
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");
            if (user != null && pass != null) {
                return DriverManager.getConnection(envUrl, user, pass);
            }
            return DriverManager.getConnection(envUrl);
        }
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * REPORT 1: Queries and displays all employees in formatted tabular view.
     * Query: SELECT id, name, department, salary FROM employees ORDER BY id ASC
     */
    private static void generateAllEmployeesReport(Connection conn) throws SQLException {
        System.out.println("==========================================================================");
        System.out.println("                     REPORT 1: ALL EMPLOYEES DIRECTORY                    ");
        System.out.println("==========================================================================");

        String sql = "SELECT id, name, department, salary FROM employees ORDER BY id ASC";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            printTableHeader();

            int count = 0;
            // rs.next() moves the cursor forward to process each row
            while (rs.next()) {
                count++;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double salary = rs.getDouble("salary");

                printTableRow(id, name, dept, salary);
            }

            printTableFooter();
            System.out.println("Total Employees Listed: " + count + "\n");
        }
    }

    /**
     * REPORT 2: Queries employees earning above a specific salary threshold.
     * Uses PreparedStatement to safely bind the threshold parameter.
     */
    private static void generateHighEarnersReport(Connection conn, double threshold) throws SQLException {
        System.out.println("==========================================================================");
        System.out.printf("          REPORT 2: EMPLOYEES EARNING ABOVE $%,.2f%n", threshold);
        System.out.println("==========================================================================");

        String sql = "SELECT id, name, department, salary FROM employees "
                   + "WHERE salary > ? ORDER BY salary DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Bind parameter to PreparedStatement
            pstmt.setDouble(1, threshold);

            try (ResultSet rs = pstmt.executeQuery()) {
                printTableHeader();

                int count = 0;
                while (rs.next()) {
                    count++;
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String dept = rs.getString("department");
                    double salary = rs.getDouble("salary");

                    printTableRow(id, name, dept, salary);
                }

                printTableFooter();
                System.out.printf("Total High Earners (> $%,.2f): %d%n%n", threshold, count);
            }
        }
    }

    /**
     * REPORT 3: Displays employees grouped by department, with departmental summaries.
     */
    private static void generateDepartmentGroupedReport(Connection conn) throws SQLException {
        System.out.println("==========================================================================");
        System.out.println("             REPORT 3: EMPLOYEES GROUPED BY DEPARTMENT                    ");
        System.out.println("==========================================================================");

        // Sub-query 1: Department aggregate summary
        String aggregateSql = "SELECT department, COUNT(*) AS head_count, "
                            + "AVG(salary) AS avg_sal, MAX(salary) AS max_sal "
                            + "FROM employees GROUP BY department ORDER BY department ASC";

        System.out.println("--- Department Overview & Salary Aggregates ---");
        System.out.println("+---------------+------------+----------------+----------------+");
        System.out.println("| Department    | Employees  | Average Salary | Top Salary     |");
        System.out.println("+---------------+------------+----------------+----------------+");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(aggregateSql)) {

            while (rs.next()) {
                System.out.printf("| %-13s | %-10d | $%,13.2f | $%,13.2f |%n",
                        rs.getString("department"),
                        rs.getInt("head_count"),
                        rs.getDouble("avg_sal"),
                        rs.getDouble("max_sal"));
            }
        }
        System.out.println("+---------------+------------+----------------+----------------+\n");

        // Sub-query 2: Employees listed per department
        String listingSql = "SELECT id, name, department, salary FROM employees "
                          + "ORDER BY department ASC, salary DESC";

        System.out.println("--- Detailed Employee Listing Grouped by Department ---");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(listingSql)) {

            printTableHeader();
            String currentDept = "";

            while (rs.next()) {
                String dept = rs.getString("department");
                if (!dept.equals(currentDept)) {
                    currentDept = dept;
                }

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");

                printTableRow(id, name, dept, salary);
            }
            printTableFooter();
        }
    }

    // --- Formatting Helpers ---

    private static void printTableHeader() {
        System.out.println("+-----+----------------------+---------------+---------------+");
        System.out.println("| ID  | Name                 | Department    | Salary        |");
        System.out.println("+-----+----------------------+---------------+---------------+");
    }

    private static void printTableRow(int id, String name, String dept, double salary) {
        System.out.printf("| %-3d | %-20s | %-13s | $%,12.2f |%n",
                id, name, dept, salary);
    }

    private static void printTableFooter() {
        System.out.println("+-----+----------------------+---------------+---------------+");
    }

    /**
     * Seeds the demo database table with sample employee data.
     */
    private static void initializeData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS employees ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT NOT NULL, "
                    + "department TEXT NOT NULL, "
                    + "salary REAL NOT NULL)");

            stmt.executeUpdate("DELETE FROM employees"); // Reset table for reproducible run

            String insertSql = "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                addBatchItem(ps, 101, "Vinay Madarkhandi", "Engineering", 92000.0);
                addBatchItem(ps, 102, "Alice Johnson",     "Marketing",   68000.0);
                addBatchItem(ps, 103, "Bob Smith",         "Engineering", 85000.0);
                addBatchItem(ps, 104, "Charlie Brown",     "Finance",     72000.0);
                addBatchItem(ps, 105, "Diana Prince",      "Engineering", 105000.0);
                addBatchItem(ps, 106, "Evan Wright",       "Marketing",   79000.0);
                addBatchItem(ps, 107, "Fiona Gallagher",   "Finance",     88000.0);
                addBatchItem(ps, 108, "George Clark",      "Human Res",   61000.0);
            }
        }
    }

    private static void addBatchItem(PreparedStatement ps, int id, String name, String dept, double salary)
            throws SQLException {
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, dept);
        ps.setDouble(4, salary);
        ps.executeUpdate();
    }
}
