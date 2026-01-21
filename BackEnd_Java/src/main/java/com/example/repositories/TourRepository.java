package com.example.repositories;

import com.example.entities.TourMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<TourMaster, Integer> {
    // Custom queries can be added later for search, etc.
}
