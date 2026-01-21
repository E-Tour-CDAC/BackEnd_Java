package com.example.dto;

public class ItineraryDTO {
    private Integer id;
    private Integer dayNo;
    private String itineraryDetail;

    // getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getDayNo() { return dayNo; }
    public void setDayNo(Integer dayNo) { this.dayNo = dayNo; }

    public String getItineraryDetail() { return itineraryDetail; }
    public void setItineraryDetail(String itineraryDetail) { this.itineraryDetail = itineraryDetail; }
}
