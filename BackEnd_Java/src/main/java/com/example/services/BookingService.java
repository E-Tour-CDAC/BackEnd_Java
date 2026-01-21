package com.example.services;
import com.example.entities.BookingHeader;

public interface BookingService{

    BookingHeader saveBooking(BookingHeader booking);

    BookingHeader getBookingById(Integer bookingId);
}
