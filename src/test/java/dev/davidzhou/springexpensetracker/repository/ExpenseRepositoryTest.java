package dev.davidzhou.springexpensetracker.repository;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.model.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void getAllExpensesTest() {
        //given

        //when

        //then

    }

}