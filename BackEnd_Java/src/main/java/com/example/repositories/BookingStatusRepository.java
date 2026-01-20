package com.example.repositories;

import com.example.entities.BookingStatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingStatusRepository extends JpaRepository<BookingStatusMaster,Integer> {
}
