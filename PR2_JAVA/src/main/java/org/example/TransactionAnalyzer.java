package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), FORMATTER);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static List<Transaction> findMinMaxExpensesInPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr, FORMATTER);
        LocalDate end = LocalDate.parse(endDateStr, FORMATTER);

        List<Transaction> periodExpenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), FORMATTER);
                    return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
                })
                .collect(Collectors.toList());

        if (periodExpenses.isEmpty()) return List.of();

        Transaction maxExpense = periodExpenses.stream().min(Comparator.comparing(Transaction::getAmount)).orElse(null);
        Transaction minExpense = periodExpenses.stream().max(Comparator.comparing(Transaction::getAmount)).orElse(null);

        return List.of(maxExpense, minExpense);
    }
}