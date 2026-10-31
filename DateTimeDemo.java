import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * DateTimeDemo demonstrates the modern Java 8 Date and Time API (java.time package).
 *
 * Key Concepts:
 * 1. LocalDate: Immutable, thread-safe date representation without a time-zone (ISO-8601 calendar).
 * 2. Period: Date-based amount of time (years, months, days) used to calculate differences.
 * 3. DateTimeFormatter: Thread-safe parser and formatter for printing dates cleanly.
 *
 * Replaces legacy java.util.Date and java.util.Calendar, which were mutable and prone to concurrency bugs.
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("   EMPLOYEE EXPERIENCE CALCULATOR (JAVA TIME API)             ");
        System.out.println("==============================================================");

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter longDisplayFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");

        LocalDate joiningDate = null;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee joining date (yyyy-MM-dd) [or press Enter for sample 2020-07-15]: ");
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine().trim();
        }

        if (input.isEmpty()) {
            // Default sample joining date for automated testing / non-interactive runs
            joiningDate = LocalDate.of(2020, 7, 15);
            System.out.println("Using default joining date: " + joiningDate);
        } else {
            try {
                joiningDate = LocalDate.parse(input, inputFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format entered. Falling back to default: 2020-07-15");
                joiningDate = LocalDate.of(2020, 7, 15);
            }
        }

        // Current date
        LocalDate currentDate = LocalDate.now();

        // Ensure joining date is not in future
        if (joiningDate.isAfter(currentDate)) {
            System.out.println("Warning: Joining date is in the future relative to today's date.");
        }

        // -------------------------------------------------------------------------
        // Calculate Experience using java.time.Period
        // Period.between(startDateInclusive, endDateExclusive) computes years, months, days
        // -------------------------------------------------------------------------
        Period experience = Period.between(joiningDate, currentDate);

        int years = experience.getYears();
        int months = experience.getMonths();
        int days = experience.getDays();

        // -------------------------------------------------------------------------
        // Display Formatted Date Output
        // -------------------------------------------------------------------------
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("                   EXPERIENCE REPORT                          ");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Joining Date (Formatted)     : " + joiningDate.format(displayFormatter));
        System.out.println("Joining Date (Full Details)  : " + joiningDate.format(longDisplayFormatter));
        System.out.println("Current Date (Formatted)     : " + currentDate.format(displayFormatter));
        System.out.println("Current Date (Full Details)  : " + currentDate.format(longDisplayFormatter));
        System.out.println("--------------------------------------------------------------");
        System.out.printf("Total Experience             : %d Years, %d Months, %d Days%n",
                years, months, days);
        System.out.printf("Total Years of Experience    : %d Year(s)%n", years);
        System.out.println("--------------------------------------------------------------");

        // Additional demonstration of Period methods
        System.out.println("\n--- Additional Period & Chronology Info ---");
        System.out.println("Is Leap Year (Joining Year)  : " + joiningDate.isLeapYear());
        System.out.println("Day of Week on Joining Date  : " + joiningDate.getDayOfWeek());
        System.out.println("Day of Year on Joining Date  : " + joiningDate.getDayOfYear());
        System.out.println("Is Experience >= 2 Years?    : " + (years >= 2));

        System.out.println("\n==============================================================");
        System.out.println("Date & Time API calculation completed successfully!");
        System.out.println("==============================================================");

        scanner.close();
    }
}
