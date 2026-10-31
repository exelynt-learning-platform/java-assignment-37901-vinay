// Explicit import statement: Brings Book class from package libraryutils into scope
import libraryutils.Book;

/**
 * LibrarySystem resides in the default package and demonstrates:
 * 1. Importing a class from a custom package (libraryutils.Book).
 * 2. Creating multiple objects of the imported class.
 * 3. Calling methods on the imported class objects.
 */
public class LibrarySystem {

    public static void main(String[] args) {
        System.out.println("=== Library Management System (Package Demo) ===\n");
        System.out.println("Imported 'Book' class from package 'libraryutils'.\n");

        // Create multiple Book objects using the imported class
        Book book1 = new Book("Effective Java", "Joshua Bloch");
        Book book2 = new Book("Clean Code", "Robert C. Martin");
        Book book3 = new Book("Java: The Complete Reference", "Herbert Schildt");

        // Display book information using the public method
        System.out.println("--- Cataloged Books ---");
        book1.displayBookInfo();
        book2.displayBookInfo();
        book3.displayBookInfo();

        System.out.println("All books successfully loaded and displayed from libraryutils package.");
    }
}
