package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryDTO;
import com.example.dto.TourGuideDTO;
import com.example.services.CategoryService;
<<<<<<< HEAD
import com.example.services.TourGuideServices;
import com.example.services.TourService;
=======

>>>>>>> c468b0a0cce32968be2185e99f4a8e860809e298

@RestController
@RequestMapping("/home")
public class HomeController {

	private final CategoryService categoryService;
<<<<<<< HEAD
	private final TourService tourService;
=======
>>>>>>> c468b0a0cce32968be2185e99f4a8e860809e298


	public HomeController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public String home() {
		return "Welcome to e-Tour Application";
	}

	@GetMapping("/categories")
	public List<CategoryDTO> getHomeCategories() {
		return categoryService.getHomeCategories();
	}
}

