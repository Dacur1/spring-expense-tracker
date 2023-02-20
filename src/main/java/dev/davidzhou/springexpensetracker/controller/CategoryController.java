package dev.davidzhou.springexpensetracker.controller;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getSingleCategoryByName(@PathVariable String categoryName) {
        return categoryService.getCategoryByCategoryName(categoryName);
    }
    @GetMapping("/search/{charSequence}")
    public ResponseEntity<List<Category>> getAllCategoriesByChar(@PathVariable String charSequence) {
        return categoryService.getAllCategoriesByChar(charSequence);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Map<String, String> payload) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.addSingleCategory(
                        payload.get("categoryName")));
    }

    @PutMapping("/update/{categoryName}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable String categoryName,
            @RequestBody Map<String, String> payload) {

        String newCategoryName = payload.get("newCategoryName");

        return categoryService.updateCategory(categoryName, newCategoryName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable ObjectId id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
