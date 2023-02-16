package dev.davidzhou.springexpensetracker.repository;

import dev.davidzhou.springexpensetracker.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Optional<Category> findFirstByCategoryNameEqualsIgnoreCase(String categoryName);
    List<Category> findAllByCategoryNameContainingIgnoreCaseOrderByCategoryNameAsc(String charSequence);
}
