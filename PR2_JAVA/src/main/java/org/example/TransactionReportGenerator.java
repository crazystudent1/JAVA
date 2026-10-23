package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TransactionReportGenerator {
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printVisualCategoryReport(List<Transaction> transactions) {
        System.out.println("\n--- Візуальний звіт витрат по категоріях та місяцях (1 '*' = 1000 грн) ---");

        Map<String, Map<String, Double>> grouped = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                                .format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.groupingBy(
                                Transaction::getDescription,
                                Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                        )
                ));

        grouped.forEach((month, categories) -> {
            System.out.println("Місяць: " + month);
            categories.forEach((category, sum) -> {
                int starsCount = (int) (sum / 1000);
                String stars = "*".repeat(Math.max(0, starsCount));
                System.out.printf("  %s: %s (%.2f грн)%n", category, stars.isEmpty() ? "< 1000" : stars, sum);
            });
        });
    }
}