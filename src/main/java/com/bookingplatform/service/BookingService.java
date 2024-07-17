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

    public List<Booking> getAllByBookingDate(LocalDate date) {
        if (!bookingRepository.existsAllByBookingDate(date)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found bookings on date " + date);
        }
        return bookingRepository.findAllByBookingDate(date);
    }

    public List<Booking> getAllByVenueId(Long id) {
        if (!venueRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found venue with id=" + id);
        }
        List<Booking> bookings = bookingRepository.findAllByVenueId(id);
        if (bookings.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue with id=" + id + " doesn't have any bookings!");
        }
        return bookings;
    }

    @Transactional
    public Long createBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        validBookingDto(bookingDto);
        Venue venue = getVenue(bookingDto.getVenueId());
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setNumberOfParticipants(bookingDto.getNumberOfParticipants());
        booking.setName(bookingDto.getName());
        booking.setVenue(venue);
        bookingRepository.save(booking);
        return booking.getId();
    }

    @Transactional
    public void updateBooking(Long id, BookingDto bookingDto) {
        Booking booking = getBookingById(id);
        Venue venue = getVenue(bookingDto.getVenueId());
        validBookingDto(bookingDto);
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setNumberOfParticipants(bookingDto.getNumberOfParticipants());
        booking.setName(bookingDto.getName());
        booking.setVenue(venue);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found booking with id=" + id);
        }
        bookingRepository.deleteById(id);
    }

    private Venue getVenue(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found venue with id=" + id));
    }

    private void validBookingDto(BookingDto bookingDto) {
        if (bookingDto.getBookingDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Date cannot be in past!");
        }
        if (bookingDto.getNumberOfParticipants() > getVenue(bookingDto.getVenueId()).getCapacity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough capacity in venue with id=" + bookingDto.getVenueId());
        }
        if (bookingRepository.findByBookingDateAndVenueId(bookingDto.getBookingDate(), bookingDto.getVenueId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Venue already booked on this date!");
        }
        if (bookingDto.getNumberOfParticipants() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Incorrect number of participant!");
        }
    }
}
