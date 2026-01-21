package com.example.serviceimpl;

import com.example.controller.SearchController.CostType;
import com.example.services.SearchService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    private final JdbcTemplate jdbcTemplate;

    public SearchServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> searchByDate(LocalDate from, LocalDate to) {
        String sql = """
            SELECT
                t.tour_id,
                t.category_id,
                t.departure_id,
                d.depart_date,
                d.end_date,
                d.no_of_days
            FROM tour_master t
            JOIN departure_master d
                ON t.departure_id = d.departure_id
            WHERE d.depart_date BETWEEN ? AND ?
            ORDER BY d.depart_date
            """;

        return jdbcTemplate.queryForList(sql, from, to);
    }

    @Override
    public List<Map<String, Object>> searchByDuration(int minDays, int maxDays) {
        String sql = """
            SELECT
                t.tour_id,
                t.category_id,
                t.departure_id,
                d.depart_date,
                d.end_date,
                d.no_of_days
            FROM tour_master t
            JOIN departure_master d
                ON t.departure_id = d.departure_id
            WHERE d.no_of_days BETWEEN ? AND ?
            ORDER BY d.no_of_days, d.depart_date
            """;

        return jdbcTemplate.queryForList(sql, minDays, maxDays);
    }

    @Override
    public List<Map<String, Object>> searchByCost(BigDecimal min, BigDecimal max, LocalDate travelDate, CostType costType) {

        if (costType == null) costType = CostType.SINGLE;

        String costColumn = switch (costType) {
            case SINGLE -> "c.single_person_cost";
            case EXTRA  -> "c.extra_person_cost";
            case CWB    -> "c.child_with_bed_cost";
            case CWOB   -> "c.child_without_bed_cost";
        };

        String sql = """
            SELECT
                t.tour_id,
                t.category_id,
                t.departure_id,
                d.depart_date,
                d.end_date,
                d.no_of_days,
                c.cost_id,
                c.valid_from,
                c.valid_to,
                %s AS selected_cost
            FROM tour_master t
            JOIN departure_master d
                ON t.departure_id = d.departure_id
            JOIN cost_master c
                ON t.category_id = c.category_id
            WHERE ? BETWEEN c.valid_from AND c.valid_to
              AND %s BETWEEN ? AND ?
            ORDER BY selected_cost
            """.formatted(costColumn, costColumn);

        return jdbcTemplate.queryForList(sql, travelDate, min, max);
    }
}
