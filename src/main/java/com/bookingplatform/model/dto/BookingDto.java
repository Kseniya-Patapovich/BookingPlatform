package com.bookingplatform.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long venueId;
}
