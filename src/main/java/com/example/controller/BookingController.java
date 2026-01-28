package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookingCreateRequestDTO;
import com.example.dto.BookingResponseDTO;
import com.example.services.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // CREATE BOOKING
    @PostMapping
    public BookingResponseDTO createBooking(
            @RequestBody BookingCreateRequestDTO dto) {

        return bookingService.saveBooking(dto);
    }

    // GET BOOKING SUMMARY
    @GetMapping("/{bookingId}")
    public BookingResponseDTO getBooking(@PathVariable Integer bookingId) {

        return bookingService.getBookingById(bookingId);
    }
    
    
}
