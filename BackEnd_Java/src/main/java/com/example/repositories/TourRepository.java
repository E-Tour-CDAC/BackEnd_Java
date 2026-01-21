package com.example.repositories;

import com.example.entities.TourMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourMaster,Integer> {
}
