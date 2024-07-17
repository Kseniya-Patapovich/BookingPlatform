package com.bookingplatform.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VenueDto {
    private String name;
    private String address;
    private BigDecimal price;
    private String phone;
}
