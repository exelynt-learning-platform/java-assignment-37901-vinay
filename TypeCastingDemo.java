/**
 * TypeCastingDemo demonstrates implicit (widening) casting, explicit (narrowing)
 * casting, and conversions between numeric types and Strings in Java.
 */
public class TypeCastingDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Type Casting and Conversion Demo ===\n");

        // ---------------------------------------------------------------
        // 1. Implicit Type Casting (Widening Conversion)
        // Automatic conversion from a smaller data type to a larger data type.
        // byte -> short -> char -> int -> long -> float -> double
        // ---------------------------------------------------------------
        int originalInt = 42;
        // int automatically converted to double (no data loss)
        double implicitDouble = originalInt;

        char originalChar = 'B';
        // char automatically converted to int (ASCII/Unicode code point)
        int charToInt = originalChar;

        float originalFloat = 12.34f;
        // float automatically converted to double
        double floatToDouble = originalFloat;

        System.out.println("--- 1. Implicit Type Casting (Widening) ---");
        System.out.println("Original int: " + originalInt + " -> Converted double: " + implicitDouble);
        System.out.println("Original char: '" + originalChar + "' -> Converted int (ASCII code): " + charToInt);
        System.out.println("Original float: " + originalFloat + " -> Converted double: " + floatToDouble);

        // ---------------------------------------------------------------
        // 2. Explicit Type Casting (Narrowing Conversion)
        // Manual conversion from a larger data type to a smaller data type.
        // Requires cast operator (targetType). May lead to truncation/precision loss.
        // ---------------------------------------------------------------
        double largeDecimal = 99.789;
        // double manually cast to int (fractional part truncated)
        int explicitInt = (int) largeDecimal;

        double precisionValue = 1234.56789;
        // double manually cast to float
        float explicitFloat = (float) precisionValue;

        int asciiCode = 65;
        // int manually cast to char
        char codeToChar = (char) asciiCode;

        System.out.println("\n--- 2. Explicit Type Casting (Narrowing) ---");
        System.out.println("Original double: " + largeDecimal + " -> Explicitly cast to int: " + explicitInt);
        System.out.println("Original double: " + precisionValue + " -> Explicitly cast to float: " + explicitFloat);
        System.out.println("Original int: " + asciiCode + " -> Explicitly cast to char: '" + codeToChar + "'");

        // ---------------------------------------------------------------
        // 3. Numeric to String Conversion
        // Converting numeric values into their String representations.
        // ---------------------------------------------------------------
        int numberToConvert = 250;
        double decimalToConvert = 88.5;

        // Using String.valueOf() and Double.toString()
        String intAsString = String.valueOf(numberToConvert);
        String doubleAsString = Double.toString(decimalToConvert);

        System.out.println("\n--- 3. Numeric to String Conversion ---");
        System.out.println("int value: " + numberToConvert + " -> Converted to String: \"" + intAsString + "\"");
        System.out.println("double value: " + decimalToConvert + " -> Converted to String: \"" + doubleAsString + "\"");
        // Verify String concatenation vs arithmetic
        System.out.println("Demonstrating String concatenation: intAsString + \"50\" = " + (intAsString + "50"));

        // ---------------------------------------------------------------
        // 4. String to Numeric Conversion
        // Parsing Strings containing numeric characters into primitive types.
        // ---------------------------------------------------------------
        String intString = "500";
        String doubleString = "123.45";

        // Using wrapper class parsing methods
        int parsedInt = Integer.parseInt(intString);
        double parsedDouble = Double.parseDouble(doubleString);

        System.out.println("\n--- 4. String to Numeric Conversion ---");
        System.out.println("String: \"" + intString + "\" -> Parsed to int: " + parsedInt);
        System.out.println("String: \"" + doubleString + "\" -> Parsed to double: " + parsedDouble);
        // Verify arithmetic with parsed values
        System.out.println("Demonstrating arithmetic addition: parsedInt + 100 = " + (parsedInt + 100));
    }
}
