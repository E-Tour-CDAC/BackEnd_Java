package com.example.controller;

import com.example.dto.TourDTO;
import com.example.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    // 🔹 HOME PAGE
    @GetMapping
    public List<TourDTO> getHomePageTours() {
        return tourService.getHomePageTours();
    }

    // 🔹 SUBCATEGORY PAGE
    @GetMapping("/{subcat}")
    public List<TourDTO> getToursBySubCategory(
            @PathVariable String subcat) {
        return tourService.getToursBySubCategory(subcat);
    }
}