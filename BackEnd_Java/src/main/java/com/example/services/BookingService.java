package com.example.services;

import com.example.dto.BookingCreateRequestDTO;
import com.example.dto.BookingResponseDTO;

public interface BookingService {

    BookingResponseDTO saveBooking(BookingCreateRequestDTO dto);

    BookingResponseDTO getBookingById(Integer bookingId);
}
