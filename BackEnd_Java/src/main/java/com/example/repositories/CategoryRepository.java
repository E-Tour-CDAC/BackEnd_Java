package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.CategoryDTO;
import com.example.entites.CategoryMaster;

public interface CategoryRepository extends JpaRepository<CategoryMaster, Integer> {

	List<CategoryMaster> findBySubcatCode(String subcatCode);
}