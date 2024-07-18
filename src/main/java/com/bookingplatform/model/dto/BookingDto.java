package com.bookingplatform.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {
    @NotNull
    private String name;
    @FutureOrPresent
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;
    @NotNull
    private Integer numberOfParticipants;
    @NotNull
    private Long venueId;
}
