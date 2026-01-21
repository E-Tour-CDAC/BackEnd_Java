package com.example.services.impl;

import com.example.dto.BookingCreateRequestDTO;
import com.example.dto.BookingResponseDTO;
import com.example.entities.BookingHeader;
import com.example.repositories.BookingRepository;
import com.example.services.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServicesImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServicesImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // SAVE BOOKING (BOOKING-ONLY)
    @Override
    public BookingResponseDTO saveBooking(BookingCreateRequestDTO dto) {

        BookingHeader booking = new BookingHeader();

        // booking-only fields
        booking.setBookingDate(LocalDate.now());
        booking.setNoOfPax(dto.getNoOfPax());
        booking.setTourAmount(dto.getTourAmount());
        booking.setTaxes(dto.getTaxes());
        booking.setTotalAmount(dto.getTourAmount().add(dto.getTaxes()));

        BookingHeader saved = bookingRepository.save(booking);

        return mapToResponseDTO(saved);
    }

    // GET BOOKING SUMMARY
    @Override
    public BookingResponseDTO getBookingById(Integer bookingId) {

        BookingHeader booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return mapToResponseDTO(booking);
    }

    // 🔁 Mapper (BOOKING-ONLY)
    private BookingResponseDTO mapToResponseDTO(BookingHeader booking) {

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setNoOfPax(booking.getNoOfPax());
        dto.setTotalAmount(booking.getTotalAmount());

        return dto;
    }
}
