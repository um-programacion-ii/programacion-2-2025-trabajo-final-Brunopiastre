package ar.edu.um.ticketflow.backend.event.infrastructure.web.dto;

import java.time.LocalDateTime;

public class EventDetailDto {

    private Long id;
    private String name;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventDetailDto() {
    }

    public EventDetailDto(Long id, String name, String location, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
