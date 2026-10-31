import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * =============================================================================
 *                      INTRODUCTION TO JDBC & ARCHITECTURE
 * =============================================================================
 * 1. What is JDBC?
 *    JDBC (Java Database Connectivity) is a standard Java API (application programming
 *    interface) provided by Oracle (under java.sql and javax.sql packages) that allows
 *    Java applications to interact with relational databases (RDBMS) in a database-independent
 *    manner. JDBC defines interfaces, while database vendors (Oracle, MySQL, PostgreSQL,
 *    SQLite) provide the concrete driver implementations.
 *
 * 2. Key JDBC Components & Flow:
 *    a) Driver:
 *       - The database vendor implementation of java.sql.Driver.
 *       - Translates Java method calls into the proprietary network database protocol.
 *       - Common Drivers:
 *         * MySQL:  com.mysql.cj.jdbc.Driver
 *         * SQLite: org.sqlite.JDBC
 *
 *    b) DriverManager:
 *       - The central service and factory class that manages database drivers.
 *       - Automatically discovers and registers drivers available on the classpath (JDBC 4.0+ SPI).
 *       - Establishes a Connection via DriverManager.getConnection(url, user, password).
 *
 *    c) Connection:
 *       - An active, open session with the database over a network socket.
 *       - Used to manage database transactions (setAutoCommit, commit, rollback) and create statements.
 *
 *    d) Statement / PreparedStatement / CallableStatement:
 *       - Statement: Executes simple static SQL queries without parameters.
 *       - PreparedStatement: Pre-compiled SQL with parameterized placeholders (?) to prevent SQL Injection.
 *       - CallableStatement: Used to execute database stored procedures and functions.
 *
 *    e) ResultSet:
 *       - Represents the tabular query results returned from executing a SELECT statement.
 *       - Maintains a cursor pointing to its current row of data.
 *
 * 3. Driver Loading in JDBC (Class.forName vs. JDBC 4.0 Automatic Loading):
 *    - Prior to JDBC 4.0: Applications had to explicitly call Class.forName("com.mysql.cj.jdbc.Driver")
 *      to trigger static initializer blocks that register the driver with DriverManager.
 *    - JDBC 4.0+: DriverManager uses the Java ServiceLoader mechanism to automatically locate
 *      and load drivers listed in META-INF/services/java.sql.Driver found on the classpath.
 * =============================================================================
 */
public class JdbcIntro {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       INTRODUCTION TO JDBC ARCHITECTURE & DRIVER MANAGEMENT              ");
        System.out.println("==========================================================================");

        // ---------------------------------------------------------------------
        // Step 1: Explain JDBC Architecture & Component Roles
        // ---------------------------------------------------------------------
        System.out.println("\n[1] Key JDBC Component Roles in Connectivity Flow:");
        System.out.println("    1. Driver        : Translates JDBC standard calls into DB-specific wire protocol.");
        System.out.println("    2. DriverManager : Discovers, registers drivers, and creates Connection objects.");
        System.out.println("    3. Connection    : Represents active session, controls transactions & statement creation.");
        System.out.println("    4. Statement     : Compiles and executes SQL queries against the database.");
        System.out.println("    5. ResultSet     : Navigable tabular data returned from executing SELECT queries.");

        // ---------------------------------------------------------------------
        // Step 2: Driver Loading using Class.forName()
        // ---------------------------------------------------------------------
        System.out.println("\n[2] Checking Driver Availability via Class.forName():");

        String[] driverCandidates = {
                "com.mysql.cj.jdbc.Driver", // Modern MySQL Connector/J driver
                "org.sqlite.JDBC",           // SQLite JDBC driver
                "org.h2.Driver"              // H2 In-Memory DB driver
        };

        boolean anyDriverLoaded = false;
        for (String driverClass : driverCandidates) {
            try {
                // Explicitly attempt to load the driver class
                Class<?> clazz = Class.forName(driverClass);
                System.out.println("    [SUCCESS] Driver class loaded: " + clazz.getName());
                anyDriverLoaded = true;
            } catch (ClassNotFoundException e) {
                System.out.println("    [INFO] Driver class not found on classpath: " + driverClass);
            }
        }

        if (!anyDriverLoaded) {
            System.out.println("    [NOTE] Neither MySQL nor SQLite jar was detected in the classpath.");
            System.out.println("           To enable them at runtime, add mysql-connector-j or sqlite-jdbc to classpath.");
        }

        // ---------------------------------------------------------------------
        // Step 3: Inspect Registered Drivers in DriverManager
        // ---------------------------------------------------------------------
        System.out.println("\n[3] Querying DriverManager for Registered Drivers (JDBC 4.0+ SPI):");
        Enumeration<Driver> registeredDrivers = DriverManager.getDrivers();
        int driverCount = 0;

        while (registeredDrivers.hasMoreElements()) {
            Driver driver = registeredDrivers.nextElement();
            driverCount++;
            System.out.printf("    Driver #%d: %s (v%d.%d, JDBC Compliant: %b)%n",
                    driverCount,
                    driver.getClass().getName(),
                    driver.getMajorVersion(),
                    driver.getMinorVersion(),
                    driver.jdbcCompliant());
        }

        if (driverCount == 0) {
            System.out.println("    No external JDBC drivers registered yet in DriverManager.");
            System.out.println("    (When a driver jar like mysql-connector-j or sqlite-jdbc is included,");
            System.out.println("     DriverManager automatically registers it via Java ServiceLoader).");
        } else {
            System.out.printf("    Confirmation: %d driver(s) are registered and ready for connection.%n", driverCount);
        }

        // ---------------------------------------------------------------------
        // Step 4: Summary of Connection Flow
        // ---------------------------------------------------------------------
        System.out.println("\n[4] Standard Database Connectivity Flow Summary:");
        System.out.println("    1. Load Driver (Automatic since JDBC 4.0 or Class.forName())");
        System.out.println("    2. Connection conn = DriverManager.getConnection(url, user, pass);");
        System.out.println("    3. PreparedStatement ps = conn.prepareStatement(\"SELECT * FROM students WHERE id = ?\");");
        System.out.println("    4. ps.setInt(1, 101);");
        System.out.println("    5. ResultSet rs = ps.executeQuery();");
        System.out.println("    6. Process results: while (rs.next()) { ... }");
        System.out.println("    7. Close resources safely using try-with-resources.");

        System.out.println("\n==========================================================================");
        System.out.println("JDBC introduction and driver verification completed successfully!");
        System.out.println("==========================================================================");
    }
}
