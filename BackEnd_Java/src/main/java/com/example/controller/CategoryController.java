package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.services.CategoryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // HOME PAGE
    @GetMapping("/home")
    public List<CategoryDTO> home() {
        return categoryService.getHomeCategories();
    }

    // CATEGORY CLICK
    @GetMapping("/{catCode}")
    public List<CategoryDTO> onClick(@PathVariable String catCode) {
        return categoryService.onCategoryClick(catCode);
    }
}
