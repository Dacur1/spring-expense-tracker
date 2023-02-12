package dev.davidzhou.springexpensetracker.controller;

import dev.davidzhou.springexpensetracker.model.Expense;
import dev.davidzhou.springexpensetracker.service.ExpenseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return new ResponseEntity<>(expenseService.allExpenses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Expense>> getSingleExpenseById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(expenseService.singleExpenseById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Expense> createSingleExpense(@RequestBody Expense expense) {
        return new ResponseEntity<>(expenseService.createSingleExpense(expense), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Expense>> updateSingleExpense(@PathVariable ObjectId id, @RequestBody Expense expense) {
        return new ResponseEntity<>(expenseService.updateSingleExpenseById(id, expense), HttpStatus.OK);
    }

}
