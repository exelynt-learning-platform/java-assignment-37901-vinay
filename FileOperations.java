import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * =============================================================================
 * FileOperations demonstrates the capabilities of java.io.File:
 *
 * The File class is an abstract representation of file and directory pathnames.
 * It is used for inspecting, creating, deleting, and querying file metadata,
 * but does not handle reading/writing data content directly (which is done via
 * streams/readers/writers).
 *
 * Key Methods Demonstrated:
 * - createNewFile()   : Creates a new, empty file if it doesn't already exist.
 * - exists()          : Tests whether the file or directory actually exists on disk.
 * - getName()         : Returns the simple name of the file.
 * - getAbsolutePath() : Returns the full absolute pathname string.
 * - canRead()         : Tests whether the application can read the file.
 * - canWrite()        : Tests whether the application can modify the file.
 * - canExecute()      : Tests whether the application can execute the file.
 * - length()          : Returns the size of the file in bytes.
 * - isFile()          : Verifies if the path denotes a normal file (not directory).
 * =============================================================================
 */
public class FileOperations {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("         JAVA File CLASS: BASIC FILE OPERATIONS DEMO             ");
        System.out.println("=================================================================\n");

        String fileName = "data.txt";

        /*
         * 1. CREATING A FILE OBJECT:
         * Instantiates a File object referencing "data.txt" in the current directory.
         * Note: Creating a File object does NOT automatically create the file on disk!
         */
        File file = new File(fileName);

        // 2. CREATING THE PHYSICAL FILE ON DISK
        try {
            System.out.println("--- 1. File Creation Operation ---");
            if (file.createNewFile()) {
                System.out.println(">> createNewFile(): File '" + fileName + "' did not exist; created successfully!");
                // Write sample initial text
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Hello from data.txt!\nThis file demonstrates java.io.File operations.\n");
                }
            } else {
                System.out.println(">> createNewFile(): File '" + fileName + "' already exists on disk.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating/writing the file: " + e.getMessage());
        }

        // 3. CHECKING FILE EXISTENCE
        System.out.println("\n--- 2. Checking File Existence ---");
        boolean fileExists = file.exists();
        System.out.println("Does file '" + fileName + "' exist? (file.exists()): " + fileExists);

        if (fileExists) {
            // 4. DISPLAYING FILE METADATA AND PROPERTIES
            System.out.println("\n=================================================================");
            System.out.println("                   FILE METADATA & PERMISSIONS                   ");
            System.out.println("=================================================================");

            // File Name
            System.out.println("File Name (getName())               : " + file.getName());

            // Absolute Path
            System.out.println("Absolute Path (getAbsolutePath())   : " + file.getAbsolutePath());

            // Canonical Path
            try {
                System.out.println("Canonical Path (getCanonicalPath()) : " + file.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Could not resolve canonical path.");
            }

            // Parent Directory
            System.out.println("Parent Directory (getParent())      : " + (file.getParent() != null ? file.getParent() : "Current Working Directory"));

            // File Type Check
            System.out.println("Is Normal File? (isFile())          : " + file.isFile());
            System.out.println("Is Directory? (isDirectory())       : " + file.isDirectory());

            // File Permissions
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Readable Permission (canRead())     : " + (file.canRead() ? "YES [Read Allowed]" : "NO"));
            System.out.println("Writable Permission (canWrite())    : " + (file.canWrite() ? "YES [Write Allowed]" : "NO"));
            System.out.println("Executable Permission (canExecute()): " + (file.canExecute() ? "YES [Execute Allowed]" : "NO"));

            // File Size
            System.out.println("-----------------------------------------------------------------");
            System.out.println("File Size in Bytes (length())       : " + file.length() + " bytes");
            System.out.println("Last Modified (lastModified())      : " + file.lastModified() + " (epoch timestamp)");
            System.out.println("=================================================================");
        } else {
            System.out.println("File could not be found or verified on disk.");
        }

        System.out.println("\nFile operations executed successfully!");
    }
}
