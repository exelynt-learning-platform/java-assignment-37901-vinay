package libraryutils;

/**
 * The Book class is placed inside the 'libraryutils' package.
 * In Java, packages provide namespace management, avoid naming collisions,
 * and enable access protection across modular codebases.
 */
public class Book {
    // Public/accessible fields
    private String title;
    private String author;

    /**
     * Parameterized constructor to initialize Book attributes.
     * Must be declared public to be accessible when imported from outside the package.
     *
     * @param title  Book title
     * @param author Book author name
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Public method to display book details.
     * Accessible by external classes importing libraryutils.Book.
     */
    public void displayBookInfo() {
        System.out.println("Book Title : " + this.title);
        System.out.println("Book Author: " + this.author);
        System.out.println("----------------------------------------");
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
