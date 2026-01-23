package com.example.dto;

import java.util.List;

public class TourDTO {
    private Integer id;
    private String categoryName;
    private Integer departureId;
    private List<ItineraryDTO> itineraries;
    private List<CostDTO> costs;
    private List<DepartureDTO> departures;
    private List<TourGuideDTO> guides;

    // getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Integer getDepartureId() { return departureId; }
    public void setDepartureId(Integer departureId) { this.departureId = departureId; }

    public List<ItineraryDTO> getItineraries() { return itineraries; }
    public void setItineraries(List<ItineraryDTO> itineraries) { this.itineraries = itineraries; }

    public List<CostDTO> getCosts() { return costs; }
    public void setCosts(List<CostDTO> costs) { this.costs = costs; }

    public List<DepartureDTO> getDepartures() { return departures; }
    public void setDepartures(List<DepartureDTO> departures) { this.departures = departures; }

    public List<TourGuideDTO> getGuides() { return guides; }
    public void setGuides(List<TourGuideDTO> guides) { this.guides = guides; }
}
