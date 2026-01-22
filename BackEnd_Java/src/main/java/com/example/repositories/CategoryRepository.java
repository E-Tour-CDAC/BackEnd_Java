package com.example.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.entities.CategoryMaster;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryMaster, Integer> {

	List<CategoryMaster> findBySubcatCode(String subcatCode);
}