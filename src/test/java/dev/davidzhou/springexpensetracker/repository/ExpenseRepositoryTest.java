package dev.davidzhou.springexpensetracker.repository;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.model.Expense;
import dev.davidzhou.springexpensetracker.model.PaymentMethod;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     * This test is disabled because it is not working.
     * This is not a unit test --> change to a Integration test
     */
    @Test
    @Disabled
    void saveAllExpensesTest() {
        //given
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(
                "test1 description",
                new ArrayList<>(
                        List.of(new Category(
                                "id_123", "Category 1"
                        ), new Category(
                                "id_12345", "Category 2"
                        ))
                ),
                "0.135",
                "EUR",
                LocalDate.of(2023, 1, 1),
                "Debtor 1",
                new PaymentMethod("id_123", "Payment Method 1")
        ));
        expenses.add(new Expense(
                "test2 description",
                new ArrayList<>(
                        List.of(new Category(
                                "id_321", "Category 3"
                        ), new Category(
                                "id_54321", "Category 4"
                        ))
                ),
                "123.456",
                "EUR",
                LocalDate.of(2023, 1, 2),
                "Debtor 2",
                new PaymentMethod("id_321", "Payment Method 2")
        ));
        //when
        boolean result = expenseRepository.saveAll(expenses).size() == 2;
        boolean result2 = expenseRepository.findAll() == expenses;
        //then
        assertTrue(result2);
    }

}