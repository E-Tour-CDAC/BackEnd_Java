package com.example.services.impl;

import com.example.dto.*;
import com.example.entities.*;
import com.example.repositories.*;
import com.example.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private DepartureRepository departureRepository;

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Override
    public List<TourDTO> getAllTours() {
        return tourRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO getTourById(Integer id) {
        return tourRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + id));
    }

    private TourDTO convertToDTO(TourMaster tour) {
        TourDTO dto = new TourDTO();
        dto.setId(tour.getId());
        dto.setCategoryName(tour.getCategory().getCategoryName());
        dto.setDepartureId(tour.getDeparture().getId());

        // Itineraries
        List<ItineraryDTO> itineraries = itineraryRepository.findByCategoryId(tour.getCategory().getId())
                .stream().map(it -> {
                    ItineraryDTO iDto = new ItineraryDTO();
                    iDto.setId(it.getId());
                    iDto.setDayNo(it.getDayNo());
                    iDto.setItineraryDetail(it.getItineraryDetail());
                    return iDto;
                }).collect(Collectors.toList());
        dto.setItineraries(itineraries);

        // Costs
        List<CostDTO> costs = costRepository.findByCategoryId(tour.getCategory().getId())
                .stream().map(c -> {
                    CostDTO cDto = new CostDTO();
                    cDto.setId(c.getId());
                    cDto.setSinglePersonCost(c.getSinglePersonCost());
                    cDto.setExtraPersonCost(c.getExtraPersonCost());
                    cDto.setChildWithBedCost(c.getChildWithBedCost());
                    cDto.setChildWithoutBedCost(c.getChildWithoutBedCost());
                    cDto.setValidFrom(c.getValidFrom());
                    cDto.setValidTo(c.getValidTo());
                    return cDto;
                }).collect(Collectors.toList());
        dto.setCosts(costs);

        // Departure Dates
        List<DepartureDTO> departures = departureRepository.findByCategoryId(tour.getCategory().getId())
                .stream().map(d -> {
                    DepartureDTO dDto = new DepartureDTO();
                    dDto.setId(d.getId());
                    dDto.setDepartDate(d.getDepartDate());
                    dDto.setEndDate(d.getEndDate());
                    dDto.setNoOfDays(d.getNoOfDays());
                    return dDto;
                }).collect(Collectors.toList());
        dto.setDepartures(departures);

        // Tour Guides
        List<TourGuideDTO> guides = tourGuideRepository.findByTourId(tour.getId())
                .stream().map(g -> {
                    TourGuideDTO gDto = new TourGuideDTO();
                    gDto.setId(g.getId());
                    gDto.setName(g.getName());
                    gDto.setEmail(g.getEmail());
                    gDto.setPhone(g.getPhone());
                    return gDto;
                }).collect(Collectors.toList());
        dto.setGuides(guides);

        return dto;
    }
}