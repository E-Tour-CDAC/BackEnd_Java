package com.example.controller;

import com.example.entities.BookingHeader;
import com.example.services.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // 1️⃣ CREATE / SAVE BOOKING
    @PostMapping
    public BookingHeader createBooking(@RequestBody BookingHeader booking) {

        // Controller assumes:
        // customer, tour, status, amount are already set
        return bookingService.saveBooking(booking);
    }

    // 2️⃣ GET BOOKING BY ID (SUMMARY)
    @GetMapping("/{bookingId}")
    public BookingHeader getBooking(@PathVariable Integer bookingId) {

        return bookingService.getBookingById(bookingId);
    }
}
