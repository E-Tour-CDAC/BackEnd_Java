package com.example.services;

import com.example.dto.TourGuideDTO;
import java.util.List;

public interface TourGuideServices {

//    TourGuideDTO createTourGuide(TourGuideDTO dto);

    TourGuideDTO getTourGuideById(Integer id);

    List<TourGuideDTO> getAllTourGuides();

    void deleteTourGuide(Integer id);
}
