package com.example.services;

import com.example.controller.SearchController.CostType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Map<String, Object>> searchByDate(LocalDate from, LocalDate to);

    List<Map<String, Object>> searchByDuration(int minDays, int maxDays);

    List<Map<String, Object>> searchByCost(BigDecimal min, BigDecimal max, LocalDate travelDate, CostType costType);
}
