package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTests {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        Assertions.assertEquals(5.0, calculator.add(2.0, 3.0));
    }

    @Test
    void testSubtract() {
        Assertions.assertEquals(1.0, calculator.subtract(3.0, 2.0));
    }

    @Test
    void testMultiply() {
        Assertions.assertEquals(6.0, calculator.multiply(2.0, 3.0));
    }

    @Test
    void testDivide() {
        Assertions.assertEquals(2.0, calculator.divide(6.0, 3.0));
    }

    @Test
    void testDivideByZero() {
        // Перевірка викидання ArithmeticException при діленні на нуль
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.divide(5.0, 0.0);
        });
    }

    @Test
    void testSquareRootValid() throws InvalidInputException {
        Assertions.assertEquals(4.0, calculator.squareRoot(16.0));
    }

    @Test
    void testSquareRootInvalidInputException() {
        // Перевірка викидання власного виключення InvalidInputException для від'ємного числа
        Assertions.assertThrows(InvalidInputException.class, () -> {
            calculator.squareRoot(-9.0);
        });
    }
}