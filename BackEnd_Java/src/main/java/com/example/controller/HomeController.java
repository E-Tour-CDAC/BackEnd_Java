package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryDTO;
import com.example.dto.TourGuideDTO;
import com.example.services.CategoryService;
import com.example.services.TourGuideServices;

@RestController
@RequestMapping("/home")
public class HomeController {

	private final CategoryService categoryService;
	private final TourGuideServices tourGuideServices;

	public HomeController(CategoryService categoryService, TourGuideServices tourGuideServices) {
		this.categoryService = categoryService;
		this.tourGuideServices = tourGuideServices;
	}

	@GetMapping
	public String home() {
		return "Welcome to e-Tour Application";
	}

	@GetMapping("/categories")
	public List<CategoryDTO> getHomeCategories() {
		return categoryService.getHomeCategories();
	}


	@GetMapping("/tour-guides")
	public List<TourGuideDTO> getHomeTourGuides() {
		return tourGuideServices.getAllTourGuides();
	}
}

