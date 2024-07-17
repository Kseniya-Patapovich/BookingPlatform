package com.bookingplatform.repository;

import com.bookingplatform.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByVenueId(Long id);
    Optional<Booking> findByBookingDateAndVenueId(LocalDate bookingDate, Long venueId);
    List<Booking> findAllByBookingDate(LocalDate bookingDate);
    boolean existsAllByBookingDate(LocalDate bookingDate);
}
