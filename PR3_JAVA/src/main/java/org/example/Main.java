package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("=== Калькулятор ===");
        System.out.println("Оберіть операцію (+, -, *, /, sqrt): ");

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine());

            String operation;
            System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
            operation = scanner.nextLine().trim();

            double result = 0;

            if (operation.equals("sqrt")) {
                result = calculator.squareRoot(a);
                System.out.println("Результат (корінь): " + result);
            } else {
                System.out.print("Введіть друге число: ");
                double b = Double.parseDouble(scanner.nextLine());

                switch (operation) {
                    case "+":
                        result = calculator.add(a, b);
                        break;
                    case "-":
                        result = calculator.subtract(a, b);
                        break;
                    case "*":
                        result = calculator.multiply(a, b);
                        break;
                    case "/":
                        result = calculator.divide(a, b);
                        break;
                    default:
                        System.out.println("Невідома операція!");
                        return;
                }
                System.out.println("Результат: " + result);
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка вводу: Будь ласка, введіть числове значення, а не букви чи символи!");
        } catch (ArithmeticException | InvalidInputException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("--- Завершення обробки запиту ---");
            scanner.close();
        }
    }
}