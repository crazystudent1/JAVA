package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        System.out.println("\n--- Аналіз періоду (грудень 2023) ---");
        List<Transaction> minMax = TransactionAnalyzer.findMinMaxExpensesInPeriod(transactions, "01-12-2023", "31-12-2023");
        if (!minMax.isEmpty() && minMax.get(0) != null) {
            System.out.println("Найбільша витрата: " + minMax.get(0).getDescription() + " (" + minMax.get(0).getAmount() + ")");
            System.out.println("Найменша витрата: " + minMax.get(1).getDescription() + " (" + minMax.get(1).getAmount() + ")");
        }

        TransactionReportGenerator.printVisualCategoryReport(transactions);
    }
}