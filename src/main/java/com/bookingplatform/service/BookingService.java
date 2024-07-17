package com.bookingplatform.service;

import com.bookingplatform.model.Booking;
import com.bookingplatform.model.Venue;
import com.bookingplatform.model.dto.BookingDto;
import com.bookingplatform.repository.BookingRepository;
import com.bookingplatform.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final VenueRepository venueRepository;

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found booking with id=" + id));
    }

    public List<Booking> getAllByVenueId(Long id) {
        List<Booking> bookings = bookingRepository.findAllByVenueId(id);
        if (bookings.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue doesn't have any bookings!");
        }
        return bookings;
    }

    @Transactional
    public Long createBooking(BookingDto bookingDto) {
        Booking client = new Booking();
        Venue venue = getVenue(bookingDto.getVenueId());
        if (bookingDto.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Start date cannot be in past!");
        }
        if (bookingDto.getEndDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "End date cannot be in past!");
        }
        if (bookingDto.getEndDate().isBefore(bookingDto.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "End date cannot be before start date!");
        }
        client.setName(bookingDto.getName());
        client.setStartDate(bookingDto.getStartDate());
        client.setEndDate(bookingDto.getEndDate());
        client.setVenue(venue);
        bookingRepository.save(client);
        return client.getId();
    }

    @Transactional
    public void updateBooking(Long id, BookingDto bookingDto) {
        Booking booking = getBookingById(id);
        Venue venue = getVenue(bookingDto.getVenueId());
        if (bookingDto.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Start date cannot be in past!");
        }
        if (bookingDto.getEndDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "End date cannot be in past!");
        }
        if (bookingDto.getEndDate().isBefore(bookingDto.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "End date cannot be before start date!");
        }
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());
        booking.setName(bookingDto.getName());
        booking.setVenue(venue);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    private Venue getVenue(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found venue with id=" + id));
    }
}
