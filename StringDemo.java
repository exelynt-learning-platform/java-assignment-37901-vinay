import java.util.Scanner;

/**
 * StringDemo demonstrates Java String manipulation methods:
 * 1. trim()        : Eliminates leading and trailing whitespace.
 * 2. length()      : Returns the total count of characters in the string.
 * 3. replace()     : Substitutes specified characters or substrings with replacements.
 * 4. toUpperCase() : Converts characters in a string to uppercase.
 * 5. toLowerCase() : Converts characters in a string to lowercase.
 * 6. substring()   : Extracts a portion of the string between specified indices.
 */
public class StringDemo {

    /**
     * Formats an individual name token:
     * - Capitalizes the first character using substring(0, 1) and toUpperCase()
     * - Converts remaining characters to lowercase using substring(1) and toLowerCase()
     *
     * @param nameToken Individual word/name token
     * @return Formatted TitleCase word
     */
    public static String formatNameToken(String nameToken) {
        // length() check ensures we don't call substring on an empty string
        if (nameToken == null || nameToken.length() == 0) {
            return "";
        }
        if (nameToken.length() == 1) {
            return nameToken.toUpperCase();
        }

        // substring(0, 1) gets the first letter; toUpperCase() makes it uppercase
        String firstLetter = nameToken.substring(0, 1).toUpperCase();

        // substring(1) extracts from index 1 to the end; toLowerCase() ensures proper casing
        String remainingLetters = nameToken.substring(1).toLowerCase();

        return firstLetter + remainingLetters;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("       JAVA STRING CLASS & MANIPULATION METHODS DEMO             ");
        System.out.println("=================================================================\n");

        System.out.println("Enter raw student name (e.g., '  #vInAY_mADarKHaNDI@  '):");
        System.out.print("Input: ");
        String rawInput = scanner.nextLine();

        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("1. Raw Input String: \"" + rawInput + "\"");

        // --- METHOD 1: length() ---
        // Measures the character count of the input string
        int rawLength = rawInput.length();
        System.out.println("   [length()] Raw character count: " + rawLength);

        // --- METHOD 2: trim() ---
        // Removes whitespace from both ends of the string
        String trimmedInput = rawInput.trim();
        System.out.println("\n2. [trim()] Whitespace removed from ends: \"" + trimmedInput + "\"");
        System.out.println("   [length()] Length after trim: " + trimmedInput.length());

        // Validate that trimmedInput is not empty
        if (trimmedInput.length() == 0) {
            System.out.println("\nValidation Error: Provided name cannot be blank.");
            scanner.close();
            return;
        }

        // --- METHOD 3: replace() ---
        // Replaces unwanted symbols/special characters with spaces or clean characters
        String cleanedInput = trimmedInput
                .replace("@", "")
                .replace("#", "")
                .replace("$", "")
                .replace("%", "")
                .replace("_", " ") // converts underscores between names to spaces
                .replace("-", " ");
        System.out.println("\n3. [replace()] Removed unwanted special characters: \"" + cleanedInput + "\"");

        // Clean extra intermediate spaces
        cleanedInput = cleanedInput.trim();

        // Split words by space to format each word (e.g. First and Last Name)
        String[] nameParts = cleanedInput.split("\\s+");
        StringBuilder formattedFullName = new StringBuilder();

        // --- METHODS 4, 5, 6: substring(), toUpperCase(), toLowerCase() ---
        for (String part : nameParts) {
            if (part.length() > 0) {
                String formattedPart = formatNameToken(part);
                formattedFullName.append(formattedPart).append(" ");
            }
        }

        String finalFormattedName = formattedFullName.toString().trim();

        // --- FINAL RESULTS DISPLAY ---
        System.out.println("\n=================================================================");
        System.out.println("                 FINAL STRING FORMATTING SUMMARY                 ");
        System.out.println("=================================================================");
        System.out.println("Original Raw Input      : \"" + rawInput + "\"");
        System.out.println("Cleaned & Formatted Name: \"" + finalFormattedName + "\"");
        System.out.println("Final Name Length       : " + finalFormattedName.length() + " characters");
        System.out.println("All UpperCase Preview   : " + finalFormattedName.toUpperCase());
        System.out.println("All LowerCase Preview   : " + finalFormattedName.toLowerCase());
        System.out.println("=================================================================");

        scanner.close();
    }
}
