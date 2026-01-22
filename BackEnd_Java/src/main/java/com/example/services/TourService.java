package com.example.services;

import com.example.dto.TourDTO;

import java.util.List;

public interface TourService {
    List<TourDTO> getAllTours();
    TourDTO getTourById(Integer id);
}
