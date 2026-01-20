package com.example.repositories;

import com.example.entities.BookingHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingHeader,Integer> {
}
