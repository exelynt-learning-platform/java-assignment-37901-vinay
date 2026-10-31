/**
 * OperatorsDemo demonstrates the use of arithmetic, relational,
 * logical, and bitwise operators in Java with descriptive output.
 */
public class OperatorsDemo {

    public static void main(String[] args) {
        // --- 1. Arithmetic Operators ---
        // Used to perform basic mathematical operations
        int numberA = 15;
        int numberB = 4;

        int sum = numberA + numberB;          // Addition
        int difference = numberA - numberB;   // Subtraction
        int product = numberA * numberB;      // Multiplication
        int quotient = numberA / numberB;     // Integer Division
        int remainder = numberA % numberB;    // Modulo (Remainder)

        System.out.println("=== 1. Arithmetic Operations ===");
        System.out.println("numberA = " + numberA + ", numberB = " + numberB);
        System.out.println("Addition (numberA + numberB): " + sum);
        System.out.println("Subtraction (numberA - numberB): " + difference);
        System.out.println("Multiplication (numberA * numberB): " + product);
        System.out.println("Division (numberA / numberB): " + quotient);
        System.out.println("Remainder (numberA % numberB): " + remainder);

        // --- 2. Relational Operators ---
        // Used to compare two values and return a boolean result
        System.out.println("\n=== 2. Relational Operations ===");
        System.out.println("Is numberA greater than numberB? (numberA > numberB): " + (numberA > numberB));
        System.out.println("Is numberA less than numberB? (numberA < numberB): " + (numberA < numberB));
        System.out.println("Is numberA equal to numberB? (numberA == numberB): " + (numberA == numberB));
        System.out.println("Is numberA not equal to numberB? (numberA != numberB): " + (numberA != numberB));
        System.out.println("Is numberA greater than or equal to 15? (numberA >= 15): " + (numberA >= 15));
        System.out.println("Is numberB less than or equal to 3? (numberB <= 3): " + (numberB <= 3));

        // --- 3. Logical Operators ---
        // Used to combine multiple boolean conditions
        boolean condition1 = (numberA > 10);  // true
        boolean condition2 = (numberB > 5);   // false

        System.out.println("\n=== 3. Logical Operations ===");
        System.out.println("condition1 (numberA > 10): " + condition1);
        System.out.println("condition2 (numberB > 5): " + condition2);

        // Logical AND (&&): returns true only if both operands are true
        System.out.println("Logical AND (condition1 && condition2): " + (condition1 && condition2));

        // Logical OR (||): returns true if at least one operand is true
        System.out.println("Logical OR (condition1 || condition2): " + (condition1 || condition2));

        // Logical NOT (!): reverses the boolean value
        System.out.println("Logical NOT (!condition1): " + (!condition1));

        // --- 4. Bitwise Operators ---
        // Used to perform operations on individual bits of integer types
        int x = 6;  // Binary: 0110
        int y = 3;  // Binary: 0011

        int bitwiseAnd = x & y;  // Bitwise AND: 0010 (2)
        int bitwiseOr = x | y;   // Bitwise OR:  0111 (7)
        int bitwiseXor = x ^ y;  // Bitwise XOR: 0101 (5)
        int leftShift = x << 1;  // Bitwise Left Shift: 1100 (12)

        System.out.println("\n=== 4. Bitwise Operations ===");
        System.out.println("x = " + x + " (binary: 0110), y = " + y + " (binary: 0011)");
        System.out.println("Bitwise AND (x & y): " + bitwiseAnd);
        System.out.println("Bitwise OR (x | y): " + bitwiseOr);
        System.out.println("Bitwise XOR (x ^ y): " + bitwiseXor);
        System.out.println("Bitwise Left Shift (x << 1): " + leftShift);
    }
}
