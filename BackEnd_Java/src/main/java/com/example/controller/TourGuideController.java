package com.example.controller;

import com.example.dto.TourGuideDTO;
import com.example.services.TourGuideServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-guides")
public class TourGuideController {

    private final TourGuideServices tourGuideServices;

    public TourGuideController(TourGuideServices tourGuideServices) {
        this.tourGuideServices = tourGuideServices;
    }

//    @PostMapping
//    public TourGuideDTO createTourGuide(@RequestBody TourGuideDTO dto) {
//        return tourGuideServices.createTourGuide(dto);
//    }

    @GetMapping("/{id}")
    public TourGuideDTO getTourGuideById(@PathVariable Integer id) {
        return tourGuideServices.getTourGuideById(id);
    }

    @GetMapping
    public List<TourGuideDTO> getAllTourGuides() {
        return tourGuideServices.getAllTourGuides();
    }

    @DeleteMapping("/{id}")
    public void deleteTourGuide(@PathVariable Integer id) {
        tourGuideServices.deleteTourGuide(id);
    }
}

