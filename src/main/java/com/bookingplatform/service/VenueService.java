package com.bookingplatform.service;

import com.bookingplatform.model.Booking;
import com.bookingplatform.model.Venue;
import com.bookingplatform.model.dto.VenueDto;
import com.bookingplatform.repository.BookingRepository;
import com.bookingplatform.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;
    private final BookingRepository bookingRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found venue with id=" + id));
    }

    @Transactional
    public Long createVenue(VenueDto venueDto) {
        Venue venue = new Venue();
        /*List<Booking> bookings = new ArrayList<>();
        if (venueDto.getBookingsId() != null && !venueDto.getBookingsId().isEmpty()) {
            for (Long ids : venueDto.getBookingsId()) {
                bookings.add(bookingRepository.findById(ids).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
            }
        }
        venue.setBookings(bookings);*/
        venue.setName(venueDto.getName());
        venue.setAddress(venueDto.getAddress());
        venue.setPrice(venueDto.getPrice());
        venue.setPhone(venueDto.getPhone());
        venueRepository.save(venue);
        return venue.getId();
    }

    @Transactional
    public void updateVenue(Long id, VenueDto venueDto) {
        Venue venue = getVenueById(id);
        /*List<Booking> bookings = new ArrayList<>();
        if (venueDto.getBookingsId() != null && !venueDto.getBookingsId().isEmpty()) {
            for (Long ids : venueDto.getBookingsId()) {
                bookings.add(bookingRepository.findById(ids).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
            }
        }
        venue.setBookings(bookings);*/
        venue.setName(venueDto.getName());
        venue.setAddress(venueDto.getAddress());
        venue.setPhone(venueDto.getPhone());
        venue.setPrice(venueDto.getPrice());
        venueRepository.save(venue);
    }

    /*@Transactional
    public void updateBookingList(Long id, List<Long> list) {

       *//* if (venueUpdateDto.getBookingList() == null || venueUpdateDto.getBookingList().isEmpty()) {
            throw new IllegalArgumentException("Booking IDs must not be null or empty");
        }

        List<Long> nonNullBookingIds = venueUpdateDto.getBookingList().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (nonNullBookingIds.isEmpty()) {
            throw new IllegalArgumentException("Booking IDs must not be null or empty");
        }*//*

        Venue venue = getVenueById(id);
        List<Booking> bookings = bookingRepository.findAllById(list);
        venue.setBookings(bookings);
        venueRepository.save(venue);
    }*/


    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}
