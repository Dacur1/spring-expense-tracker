package dev.davidzhou.springexpensetracker.service;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addSingleCategory(String categoryName){
        categoryRepository.insert(new Category(categoryName));
    }

    public void updateCategory(Category category) {
        Category categoryToUpdate = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Category with id " + category.getId() + " does not exist."));
        categoryToUpdate.setCategoryName(category.getCategoryName());

        categoryRepository.save(categoryToUpdate);
    }

    public Category getCategoryByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).orElseThrow(() -> new RuntimeException(
                "Category with name " + categoryName + " does not exist."));
    }

    public Category getCategoryById(ObjectId id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Category with id " + id + " does not exist."));
    }

    public List<Category> getAllCategoriesByChar(String charSequence) {
        return categoryRepository.findAllByCategoryNameContainingIgnoreCaseOrderByCategoryNameAsc(charSequence);
    }
    public void deleteCategory(ObjectId id) {
        categoryRepository.deleteById(id);
    }
}
