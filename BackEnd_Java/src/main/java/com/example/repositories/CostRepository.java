package com.example.repositories;

import com.example.entities.CostMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostRepository extends JpaRepository<CostMaster,Integer> {
}
