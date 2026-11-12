package org.example;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Помилка: Ділення на нуль заборонено!");
        }
        return a / b;
    }

    // Метод із власним виключенням для перевірки значень (наприклад, квадратний корінь)
    public double squareRoot(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Помилка: Неможливо обчислити квадратний корінь з від'ємного числа!");
        }
        return Math.sqrt(a);
    }
}