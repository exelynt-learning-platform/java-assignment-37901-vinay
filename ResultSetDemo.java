import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ResultSetDemo demonstrates:
 * 1. ResultSet Navigation:
 *    - A ResultSet maintains an internal cursor pointing to the current row of data.
 *    - Initially, the cursor is positioned BEFORE the first row.
 *    - The rs.next() method advances the cursor to the next row and returns true
 *      if valid data is present, or false when the end of the ResultSet is reached.
 *
 * 2. ResultSetMetaData:
 *    - Obtains schema metadata regarding the returned columns (column count,
 *      column labels/names, database data types, display sizes, nullability).
 *    - Allows dynamic rendering of query results without hardcoding table column names.
 */
public class ResultSetDemo {

    private static final String DB_URL = "jdbc:sqlite:metadata_demo.db";

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       DYNAMIC RESULTSET NAVIGATION & RESULTSETMETADATA DEMO              ");
        System.out.println("==========================================================================");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Setup demo table with employee records
            setupDatabase(conn);

            String query = "SELECT id, name, department, salary, joining_date, email FROM employees ORDER BY id ASC";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                // -----------------------------------------------------------------
                // Step 1: Extract Metadata using ResultSetMetaData
                // -----------------------------------------------------------------
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                System.out.println("\n[1] Dynamic Schema Analysis via ResultSetMetaData:");
                System.out.println("    Total Columns Returned: " + columnCount);
                System.out.println("    --------------------------------------------------------------");
                System.out.printf("    %-6s | %-16s | %-16s | %-10s%n",
                        "Index", "Column Name", "Data Type Name", "Nullable?");
                System.out.println("    --------------------------------------------------------------");

                for (int i = 1; i <= columnCount; i++) {
                    String colName = metaData.getColumnName(i);
                    String typeName = metaData.getColumnTypeName(i);
                    int isNullable = metaData.isNullable(i);
                    String nullableStr = (isNullable == ResultSetMetaData.columnNullable) ? "YES" : "NO";

                    System.out.printf("    #%-5d | %-16s | %-16s | %-10s%n",
                            i, colName, typeName, nullableStr);
                }
                System.out.println("    --------------------------------------------------------------");

                // -----------------------------------------------------------------
                // Step 2: Dynamically Print Table Header
                // -----------------------------------------------------------------
                System.out.println("\n[2] Dynamic Table Rendering:");
                printDynamicSeparator(columnCount);

                System.out.print("|");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf(" %-18s |", metaData.getColumnLabel(i).toUpperCase());
                }
                System.out.println();
                printDynamicSeparator(columnCount);

                // -----------------------------------------------------------------
                // Step 3: Loop Through and Print All Records using rs.next()
                // -----------------------------------------------------------------
                int rowCount = 0;
                while (rs.next()) {
                    rowCount++;
                    System.out.print("|");

                    // Dynamically read each column value using 1-based index
                    for (int col = 1; col <= columnCount; col++) {
                        Object value = rs.getObject(col);
                        String displayVal = (value != null) ? value.toString() : "NULL";

                        // Format salary currency nicely if column is salary
                        if (metaData.getColumnName(col).equalsIgnoreCase("salary") && value instanceof Number) {
                            displayVal = String.format("$%,.2f", ((Number) value).doubleValue());
                        }

                        // Truncate long strings for neat alignment
                        if (displayVal.length() > 18) {
                            displayVal = displayVal.substring(0, 15) + "...";
                        }
                        System.out.printf(" %-18s |", displayVal);
                    }
                    System.out.println();
                }

                printDynamicSeparator(columnCount);
                System.out.println("Total Rows Processed: " + rowCount);

                // -----------------------------------------------------------------
                // Step 4: Summary of ResultSet Navigation Concepts
                // -----------------------------------------------------------------
                System.out.println("\n[3] Key ResultSet Navigation Concepts:");
                System.out.println("    - rs.next()        : Moves cursor forward one row from current position.");
                System.out.println("    - rs.getInt(col)   : Type-safe retrieval by 1-based index or column name.");
                System.out.println("    - rs.getString(col): Reads text or converts underlying value to String.");
                System.out.println("    - rs.wasNull()     : Checks if the last read primitive value was actually SQL NULL.");

            }

            System.out.println("\n==========================================================================");
            System.out.println("ResultSet & ResultSetMetaData demonstration completed successfully!");
            System.out.println("==========================================================================");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper to print dynamic table border matching column count.
     */
    private static void printDynamicSeparator(int columnCount) {
        System.out.print("+");
        for (int i = 0; i < columnCount; i++) {
            System.out.print("--------------------+");
        }
        System.out.println();
    }

    /**
     * Prepares the database table with sample employee records.
     */
    private static void setupDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS employees ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT NOT NULL, "
                    + "department TEXT NOT NULL, "
                    + "salary REAL NOT NULL, "
                    + "joining_date TEXT NOT NULL, "
                    + "email TEXT)");

            stmt.executeUpdate("DELETE FROM employees"); // Clean table for fresh run

            String insertSql = "INSERT INTO employees (id, name, department, salary, joining_date, email) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                insertBatchRecord(ps, 101, "Vinay Madarkhandi", "Engineering", 92000.0, "2021-03-15", "vinay@example.com");
                insertBatchRecord(ps, 102, "Alice Johnson",     "Marketing",   68000.0, "2022-07-01", "alice@example.com");
                insertBatchRecord(ps, 103, "Bob Smith",         "Engineering", 84000.0, "2020-01-20", null);
                insertBatchRecord(ps, 104, "Charlie Brown",     "Finance",     71000.0, "2023-04-10", "charlie@example.com");
                insertBatchRecord(ps, 105, "Diana Prince",      "Management",  105000.0, "2019-11-18", "diana@example.com");
            }
        }
    }

    private static void insertBatchRecord(PreparedStatement ps, int id, String name, String dept,
                                         double salary, String date, String email) throws SQLException {
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, dept);
        ps.setDouble(4, salary);
        ps.setString(5, date);
        ps.setString(6, email);
        ps.executeUpdate();
    }
}
