package com.example.repositories;

import com.example.entities.CustomerMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerMaster,Integer> {
}
