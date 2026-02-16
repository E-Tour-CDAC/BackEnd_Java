package com.example.controller;

import com.example.dto.BookingResponseDTO;
import com.example.repositories.BookingRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.TourRepository;
import com.example.services.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final TourRepository tourRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final BookingService bookingService;

    public AdminController(TourRepository tourRepository,
            BookingRepository bookingRepository,
            CustomerRepository customerRepository,
            BookingService bookingService) {
        this.tourRepository = tourRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.bookingService = bookingService;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        try {
            stats.put("totalTours", tourRepository.count());
            stats.put("totalBookings", bookingRepository.count());
            stats.put("totalCustomers", customerRepository.count());

            BigDecimal totalRevenue = bookingRepository.sumTotalAmount();
            stats.put("totalRevenue", totalRevenue != null ? totalRevenue : BigDecimal.ZERO);
        } catch (Exception e) {
            stats.put("error", e.getMessage());
            stats.put("totalTours", 0);
            stats.put("totalBookings", 0);
            stats.put("totalCustomers", 0);
            stats.put("totalRevenue", BigDecimal.ZERO);
        }
        return stats;
    }

    @GetMapping("/recent-bookings")
    public List<BookingResponseDTO> getRecentBookings() {
        try {
            return bookingService.getAllBookings().stream()
                    .sorted((b1, b2) -> b2.getBookingId().compareTo(b1.getBookingId()))
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of(); // Return empty list instead of 500
        }
    }
}
