package dev.davidzhou.springexpensetracker.service;

import dev.davidzhou.springexpensetracker.model.Category;
import dev.davidzhou.springexpensetracker.model.Expense;
import dev.davidzhou.springexpensetracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public Category createSingleCategory(String categoryName){
        return categoryRepository.insert(new Category(categoryName));
    }

    public List<Category> createMultipleCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
}
