package com.example.services;

import com.example.dto.TourDTO;
import java.util.List;

public interface TourService {

    List<TourDTO> getAllTours();

    TourDTO getTourById(Integer id);

    // ✅ NEW
    List<TourDTO> getToursByCategoryId(Integer categoryId);
    
 // In TourService.java
    List<TourDTO> getToursByIds(List<Integer> tourIds);
   

        List<TourDTO> getHomePageTours();

        List<TourDTO> getToursBySubCategory(String subCategoryCode);
    

}