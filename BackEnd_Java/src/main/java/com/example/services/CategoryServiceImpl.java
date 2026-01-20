package com.example.services;

import com.example.dto.CategoryDTO;

import com.example.entites.CategoryMaster;
import com.example.repositories.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // HOME PAGE
    @Override
    public List<CategoryDTO> getHomeCategories() {

        return categoryRepository.findBySubcatCode("^")
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // CATEGORY CLICK
    @Override
    public List<CategoryDTO> onCategoryClick(String catCode) {

        // CHILD categories are identified by subcat_code = parent cat_code
        List<CategoryMaster> children =
                categoryRepository.findBySubcatCode(catCode);

        return children.stream()
        		.map(this::mapToDTO)
                .toList();
    }


    // ENTITY → DTO
    private CategoryDTO mapToDTO(CategoryMaster c) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCatCode(c.getCatCode());
        dto.setCategoryName(c.getCategoryName());
        dto.setImagePath(c.getImagePath());
        dto.setJumpFlag(c.getJumpFlag());
        return dto;
    }
}
