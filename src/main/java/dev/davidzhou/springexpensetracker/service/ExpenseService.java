package dev.davidzhou.springexpensetracker.service;

import dev.davidzhou.springexpensetracker.model.Expense;
import dev.davidzhou.springexpensetracker.repository.ExpenseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> allExpenses() {
        return expenseRepository.findAll();
    }

    public Expense createSingleExpense(Expense expense) {
        return expenseRepository.insert(expense);
    }

    public Optional<Expense> singleExpenseById(ObjectId id) {
        return expenseRepository.findById(id);
    }

    public Optional<Expense> updateSingleExpenseById(ObjectId id, Expense expense) {

        return null;
    }
}
