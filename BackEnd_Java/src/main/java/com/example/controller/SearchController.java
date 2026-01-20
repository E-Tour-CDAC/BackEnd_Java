package com.example.controller;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/by-date")
    public ResponseEntity<?> byDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        if (from.isAfter(to)) {
            return ResponseEntity.badRequest().body("from must be <= to");
        }

        return ResponseEntity.ok(Map.of(
                "from", from,
                "to", to,
                "uses", "departure_master.depart_date",
                "join", "tour_master.departure_id = departure_master.departure_id"
        ));
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

        return ResponseEntity.ok(Map.of(
                "minDays", minDays,
                "maxDays", maxDays,
                "uses", "departure_master.no_of_days",
                "join", "tour_master.departure_id = departure_master.departure_id"
        ));
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

        return ResponseEntity.ok(Map.of(
                "min", min,
                "max", max,
                "travelDate", travelDate,
                "costType", costType.name(),
                "uses", "cost_master (valid_from/valid_to + cost column)",
                "join", "tour_master.category_id = cost_master.category_id",
                "validityRule", "valid_from <= travelDate <= valid_to"
        ));
    }

    // Allowed cost columns based on your cost_master table
    public enum CostType {
        SINGLE, // single_person_cost
        EXTRA,  // extra_person_cost
        CWB,    // child_with_bed_cost
        CWOB    // child_without_bed_cost
    }
}
