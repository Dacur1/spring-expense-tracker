package dev.davidzhou.springexpensetracker.controller;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getSingleCategoryById(@PathVariable ObjectId id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getSingleCategoryByName(@PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getCategoryByCategoryName(categoryName));
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody String categoryName) {
        categoryService.addSingleCategory(categoryName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable ObjectId id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search/{charSequence}")
    public ResponseEntity<List<Category>> getAllCategoriesByChar(String charSequence) {
        return ResponseEntity.ok(categoryService.getAllCategoriesByChar(charSequence));
    }
}
