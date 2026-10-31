import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for Calculator class using JUnit 5 (Jupiter).
 * Demonstrates:
 * - @Test annotations
 * - @BeforeEach lifecycle setup
 * - @DisplayName descriptive labels
 * - Standard assertion methods (assertEquals)
 * - Exception assertion using assertThrows for division by zero
 */
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Verify Calculator instance is initialized")
    void testCalculatorInitialization() {
        assertNotNull(calculator, "Calculator instance should not be null");
    }

    @Test
    @DisplayName("Test Addition of positive and negative numbers")
    void testAdd() {
        assertEquals(8, calculator.add(5, 3), "5 + 3 should equal 8");
        assertEquals(-2, calculator.add(3, -5), "3 + (-5) should equal -2");
        assertEquals(0, calculator.add(0, 0), "0 + 0 should equal 0");
    }

    @Test
    @DisplayName("Test Subtraction of positive and negative numbers")
    void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3), "5 - 3 should equal 2");
        assertEquals(8, calculator.subtract(3, -5), "3 - (-5) should equal 8");
        assertEquals(0, calculator.subtract(4, 4), "4 - 4 should equal 0");
    }

    @Test
    @DisplayName("Test Multiplication of numbers including zero")
    void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3), "5 * 3 should equal 15");
        assertEquals(-15, calculator.multiply(5, -3), "5 * (-3) should equal -15");
        assertEquals(0, calculator.multiply(5, 0), "5 * 0 should equal 0");
    }

    @Test
    @DisplayName("Test Division resulting in exact integers")
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3), "6 / 3 should equal 2");
        assertEquals(-2, calculator.divide(6, -3), "6 / (-3) should equal -2");
        assertEquals(0, calculator.divide(0, 5), "0 / 5 should equal 0");
    }

    @Test
    @DisplayName("Test Division by Zero throws ArithmeticException with expected message")
    void testDivideByZero() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0),
                "Dividing by zero must throw an ArithmeticException"
        );

        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }
}
