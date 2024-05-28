package com.bytekeeper.backend.service;

import com.bytekeeper.backend.model.Category;
import com.bytekeeper.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public Category addCategory(Category category) {
        return repository.save(category);
    }

    public Category editCategory(Long id, Category newCategory) {
        return repository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return repository.save(category);
                }).orElse(null);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

}
