package com.example.repositories;


import com.example.entities.PaymentMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentMaster,Integer> {
}
