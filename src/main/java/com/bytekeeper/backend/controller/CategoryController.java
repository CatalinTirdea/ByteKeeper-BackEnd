package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.bytekeeper.backend.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value="/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value="/getCategories", produces="application/json")
    public List<Categ> getCategories() {
        ArrayList<Categ> jsonArray = new ArrayList<>();
        List<Category> categories = categoryService.getAllCategories();
        for (var category : categories) {
            jsonArray.add(new Categ(category.getName(), category.getId()));
        }

        return jsonArray;
    }

}
