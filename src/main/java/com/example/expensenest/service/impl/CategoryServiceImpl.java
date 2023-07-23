package com.example.expensenest.service.impl;

import com.example.expensenest.entity.Category;
import com.example.expensenest.repository.CategoryRepository;
import com.example.expensenest.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl (CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    public boolean addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }

    public Category getCategoryById(int categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
}
