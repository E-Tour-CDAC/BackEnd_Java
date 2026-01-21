package com.example.services;



import com.example.entities.BookingHeader;
import com.example.repositories.BookingRepository;
import com.example.services.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServicesImpl implements BookingService{

    private final BookingRepository bookingRepository;

    public BookingServicesImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // SAVE BOOKING
    @Override
    public BookingHeader saveBooking(BookingHeader booking) {

        // booking date set if not already set
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDate.now());
        }

        return bookingRepository.save(booking);
    }

    // GET BOOKING SUMMARY
    @Override
    public BookingHeader getBookingById(Integer bookingId) {

        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}
