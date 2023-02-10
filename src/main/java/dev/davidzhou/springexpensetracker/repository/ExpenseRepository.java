package dev.davidzhou.springexpensetracker.repository;

import dev.davidzhou.springexpensetracker.model.Expense;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, ObjectId> {
}
