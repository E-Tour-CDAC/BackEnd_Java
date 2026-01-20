package com.example.repositories;

import com.example.entities.DepartureMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartureRepository extends JpaRepository<DepartureMaster,Integer> {
}
