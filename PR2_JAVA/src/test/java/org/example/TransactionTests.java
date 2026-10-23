package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionTests {

    @Test
    void testCsvReader() {
        String url = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(url);
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");
    }

    @Test
    void testFindTopExpenses() {
        List<Transaction> mockData = Arrays.asList(
                new Transaction("01-01-2023", 1000, "Дохід"),
                new Transaction("02-01-2023", -5000, "Оренда"),
                new Transaction("03-01-2023", -200, "Кава"),
                new Transaction("04-01-2023", -1500, "Продукти")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(mockData);

        Assertions.assertEquals(3, topExpenses.size(), "Має бути знайдено 3 витрати");
        Assertions.assertEquals(-5000.0, topExpenses.get(0).getAmount(), "Першою має бути найбільша витрата");
    }
}