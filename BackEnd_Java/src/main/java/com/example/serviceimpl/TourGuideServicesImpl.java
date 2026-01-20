package com.example.serviceimpl;

import com.example.dto.TourGuideDTO;
import com.example.entities.TourGuide;
import com.example.entities.TourMaster;
import com.example.repositories.TourGuideRepository;
import com.example.services.TourGuideServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourGuideServicesImpl implements TourGuideServices {

    private final TourGuideRepository tourGuideRepository;

    public TourGuideServicesImpl(TourGuideRepository tourGuideRepository) {
        this.tourGuideRepository = tourGuideRepository;
    }

    // ---------- GET ALL ----------
    @Override
    public List<TourGuideDTO> getAllTourGuides() {
        return tourGuideRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ---------- GET BY ID ----------
    @Override
    public TourGuideDTO getTourGuideById(Integer id) {
        Optional<TourGuide> guide = tourGuideRepository.findById(id);
        return guide.map(this::mapToDTO).orElse(null);
    }

    // ---------- CREATE ----------
//    @Override
//    public TourGuideDTO createTourGuide(TourGuideDTO dto) {
//
//        TourGuide guide = new TourGuide();
//        guide.setName(dto.getName());
//        guide.setEmail(dto.getEmail());
//        guide.setPhone(dto.getPhone());
//
//        // 🔑 attach TourMaster ONLY by ID (no repository)
//        TourMaster tour = new TourMaster();
//        tour.setId(dto.getTourId());
//        guide.setTour(tour);
//
//        return mapToDTO(tourGuideRepository.save(guide));
//    }

    // ---------- DELETE ----------
    @Override
    public void deleteTourGuide(Integer id) {
        tourGuideRepository.deleteById(id);
    }

    // ---------- ENTITY → DTO ----------
    private TourGuideDTO mapToDTO(TourGuide guide) {
        TourGuideDTO dto = new TourGuideDTO();
        dto.setId(guide.getId());
        dto.setName(guide.getName());
        dto.setEmail(guide.getEmail());
        dto.setPhone(guide.getPhone());
        dto.setTourId(guide.getTour().getId());
        return dto;
    }
}
