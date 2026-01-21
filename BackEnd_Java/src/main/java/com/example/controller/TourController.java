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

    // Get all tours
    @GetMapping
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    // Get tour by id
    @GetMapping("/{id}")
    public TourDTO getTourById(@PathVariable Integer id) {
        return tourService.getTourById(id);
    }
}
