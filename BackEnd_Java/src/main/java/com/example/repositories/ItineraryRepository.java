package com.example.repositories;

import com.example.entities.ItineraryMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<ItineraryMaster,Integer> {
}
