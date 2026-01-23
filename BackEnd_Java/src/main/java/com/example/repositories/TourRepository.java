package com.example.repositories;

import com.example.entities.TourMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<TourMaster, Integer> {

	// SINGLE category
	List<TourMaster> findByCategory_Id(Integer categoryId);

	// MULTIPLE categories
	List<TourMaster> findByCategory_IdIn(List<Integer> categoryIds);

}