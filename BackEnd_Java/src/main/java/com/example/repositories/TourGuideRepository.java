package com.example.repositories;

import com.example.entities.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TourGuideRepository extends JpaRepository<TourGuide, Integer> {

}
