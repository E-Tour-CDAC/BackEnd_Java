package com.example.controller;


import com.example.services.SearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/by-date")
    public ResponseEntity<?> byDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        if (from.isAfter(to)) {
            return ResponseEntity.badRequest().body("from must be <= to");
        }
        return ResponseEntity.ok(searchService.searchByDate(from, to));
    }

    @GetMapping("/by-duration")
    public ResponseEntity<?> byDuration(
            @RequestParam int minDays,
            @RequestParam int maxDays
    ) {
        if (minDays <= 0 || maxDays <= 0) {
            return ResponseEntity.badRequest().body("days must be > 0");
        }
        if (minDays > maxDays) {
            return ResponseEntity.badRequest().body("minDays must be <= maxDays");
        }
        return ResponseEntity.ok(searchService.searchByDuration(minDays, maxDays));
    }

    @GetMapping("/by-cost")
    public ResponseEntity<?> byCost(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate,
            @RequestParam(defaultValue = "SINGLE") CostType costType
    ) {
        if (min.compareTo(BigDecimal.ZERO) < 0 || max.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().body("min/max must be >= 0");
        }
        if (min.compareTo(max) > 0) {
            return ResponseEntity.badRequest().body("min must be <= max");
        }

        return ResponseEntity.ok(searchService.searchByCost(min, max, travelDate, costType));
    }

    public enum CostType {
        SINGLE, EXTRA, CWB, CWOB
    }
}
