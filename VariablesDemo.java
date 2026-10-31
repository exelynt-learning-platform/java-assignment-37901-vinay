/**
 * VariablesDemo demonstrates the declaration, initialization, and usage
 * of all eight primitive data types in Java as well as the String reference type.
 */
public class VariablesDemo {

    public static void main(String[] args) {
        // --- 1. Integer Primitive Data Types ---

        // byte: 8-bit signed integer (-128 to 127)
        byte byteValue = 100;

        // short: 16-bit signed integer (-32,768 to 32,767)
        short shortValue = 10000;

        // int: 32-bit signed integer (standard for integer numbers)
        int intValue = 150000;

        // long: 64-bit signed integer (used for very large integer values, ends with 'L')
        long longValue = 9876543210L;

        // --- 2. Floating-Point Primitive Data Types ---

        // float: 32-bit single-precision floating point (ends with 'f' or 'F')
        float floatValue = 45.75f;

        // double: 64-bit double-precision floating point (standard for decimal numbers)
        double doubleValue = 199.995;

        // --- 3. Character Primitive Data Type ---

        // char: 16-bit Unicode character (enclosed in single quotes)
        char charValue = 'A';

        // --- 4. Boolean Primitive Data Type ---

        // boolean: represents truth values (either true or false)
        boolean booleanValue = true;

        // --- 5. Non-Primitive Reference Type ---

        // String: represents a sequence of characters (text enclosed in double quotes)
        String stringValue = "Hello, Java Variables!";

        // --- Print all variable values to console ---
        System.out.println("=== Java Variables and Data Types Demo ===");
        System.out.println("byte value: " + byteValue);
        System.out.println("short value: " + shortValue);
        System.out.println("int value: " + intValue);
        System.out.println("long value: " + longValue);
        System.out.println("float value: " + floatValue);
        System.out.println("double value: " + doubleValue);
        System.out.println("char value: " + charValue);
        System.out.println("boolean value: " + booleanValue);
        System.out.println("String value: " + stringValue);
    }
}
