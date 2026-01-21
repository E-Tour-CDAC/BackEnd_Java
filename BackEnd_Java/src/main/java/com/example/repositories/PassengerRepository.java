package com.example.repositories;

import com.example.entities.Passenger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
	
	List<Passenger> findByBookingId(Integer bookingId);
}
