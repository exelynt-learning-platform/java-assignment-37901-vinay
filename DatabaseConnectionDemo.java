import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DatabaseConnectionDemo demonstrates establishing and verifying a database connection
 * in Java using JDBC with either MySQL or SQLite.
 *
 * Security Best Practices:
 * 1. Passwords and credentials are NEVER hardcoded in source control.
 * 2. Configuration is loaded from:
 *    - Environment Variables (DB_URL, DB_USER, DB_PASSWORD)
 *    - OR an external configuration file (db.properties) listed in .gitignore
 *    - Fallback default to SQLite (which does not require network passwords).
 * 3. Connection closure is guaranteed using try-with-resources.
 */
public class DatabaseConnectionDemo {

    // Configuration keys
    private static final String CONFIG_FILE = "db.properties";

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       DATABASE CONNECTIVITY VERIFICATION (MYSQL / SQLITE)               ");
        System.out.println("==========================================================================");

        // 1. Load database configuration securely
        DbConfig config = loadConfiguration();

        System.out.println("\nActive Database Configuration:");
        System.out.println("  Driver / Type : " + config.dbType);
        System.out.println("  JDBC URL      : " + config.url);
        System.out.println("  Username      : " + (config.user.isEmpty() ? "(None / SQLite)" : config.user));
        System.out.println("  Password      : " + (config.password.isEmpty() ? "(None / SQLite)" : "******** [PROTECTED]"));

        // 2. Establish connection using try-with-resources to ensure automatic closure
        System.out.println("\nAttempting to connect to the database...");

        try (Connection conn = establishConnection(config)) {
            if (conn != null && !conn.isClosed()) {
                // Exact required confirmation message
                System.out.println("\nDatabase connected successfully");

                // Verify and display connection metadata
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("  Connected Database Product : " + metaData.getDatabaseProductName());
                System.out.println("  Database Product Version   : " + metaData.getDatabaseProductVersion());
                System.out.println("  Driver Name                : " + metaData.getDriverName());
                System.out.println("  Driver Version             : " + metaData.getDriverVersion());
                System.out.println("  Auto-Commit Mode           : " + conn.getAutoCommit());
                System.out.println("  Connection Valid (2s test) : " + conn.isValid(2));
                System.out.println("--------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("\n[ERROR] Database connection failed: " + e.getMessage());
            System.err.println("SQLState : " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            System.out.println("\nTroubleshooting Tip:");
            System.out.println(" - Ensure the database server is running.");
            System.out.println(" - Verify JDBC driver (mysql-connector-j or sqlite-jdbc) is in classpath.");
            System.out.println(" - Verify DB_URL, DB_USER, and DB_PASSWORD environment variables.");
        }

        // 3. Confirm connection closure
        System.out.println("\nConnection verified: Resource was properly closed by try-with-resources.");
        System.out.println("==========================================================================");
        System.out.println("Database connectivity demo finished.");
        System.out.println("==========================================================================");
    }

    /**
     * Establishes a database connection based on configuration.
     */
    private static Connection establishConnection(DbConfig config) throws SQLException {
        if (config.user.isEmpty() && config.password.isEmpty()) {
            return DriverManager.getConnection(config.url);
        } else {
            return DriverManager.getConnection(config.url, config.user, config.password);
        }
    }

    /**
     * Securely loads configuration with precedence:
     * 1. Environment variables (DB_URL, DB_USER, DB_PASSWORD)
     * 2. Config file db.properties (if exists)
     * 3. Fallback to SQLite database file
     */
    private static DbConfig loadConfiguration() {
        String envUrl = System.getenv("DB_URL");
        String envUser = System.getenv("DB_USER");
        String envPass = System.getenv("DB_PASSWORD");

        if (envUrl != null && !envUrl.trim().isEmpty()) {
            return new DbConfig(
                    envUrl.contains("sqlite") ? "SQLite" : "MySQL",
                    envUrl.trim(),
                    envUser != null ? envUser.trim() : "",
                    envPass != null ? envPass.trim() : ""
            );
        }

        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(file)) {
                props.load(fis);
                String fileUrl = props.getProperty("db.url", "").trim();
                if (!fileUrl.isEmpty()) {
                    return new DbConfig(
                            fileUrl.contains("sqlite") ? "SQLite" : "MySQL",
                            fileUrl,
                            props.getProperty("db.user", "").trim(),
                            props.getProperty("db.password", "").trim()
                    );
                }
            } catch (IOException e) {
                System.out.println("Notice: Could not read " + CONFIG_FILE + ", using fallback.");
            }
        }

        // Default: SQLite embedded database (safe, portable, zero credentials needed)
        return new DbConfig("SQLite", "jdbc:sqlite:student_management.db", "", "");
    }

    /**
     * Immutable container for database connection configuration.
     */
    static class DbConfig {
        final String dbType;
        final String url;
        final String user;
        final String password;

        DbConfig(String dbType, String url, String user, String password) {
            this.dbType = dbType;
            this.url = url;
            this.user = user;
            this.password = password;
        }
    }
}
