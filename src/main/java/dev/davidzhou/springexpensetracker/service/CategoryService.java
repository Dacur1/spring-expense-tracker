package dev.davidzhou.springexpensetracker.service;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addSingleCategory(String categoryName){
        return categoryRepository.insert(new Category(categoryName));
    }

    public ResponseEntity<Category> updateCategory(String categoryName, String newCategoryName) {
        Category category = categoryRepository.findFirstByCategoryNameEqualsIgnoreCase(categoryName)
                .orElse(null);

        if (category == null) {
            return ResponseEntity.status(404).build();
        }

        if (category.getCategoryName().equals(newCategoryName)) {
            return ResponseEntity.status(400).build();
        }

        category.setCategoryName(newCategoryName);
        categoryRepository.save(category);


        return ResponseEntity.ok(category);
    }

    public ResponseEntity<Category> getCategoryByCategoryName(String categoryName) {
        Category category = categoryRepository.findFirstByCategoryNameEqualsIgnoreCase(categoryName)
                .orElse(null);

        if (category == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(category);
    }

    public ResponseEntity<List<Category>> getAllCategoriesByChar(String charSequence) {
        List<Category> categories = categoryRepository.findAllByCategoryNameContainingIgnoreCaseOrderByCategoryNameAsc(charSequence);

        if (categories.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(categories);
    }
    public void deleteCategory(ObjectId id) {
        categoryRepository.deleteById(id);
    }
}
