package com.bookingplatform.repository;

import com.bookingplatform.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByVenueId(Long id);
    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT 1 FROM booking WHERE booking_date =:bookingDate AND venue_id =:venueId)")
    boolean existByBookingDateAndVenueId(LocalDate bookingDate, long venueId);
    List<Booking> findAllByBookingDate(LocalDate bookingDate);
    boolean existsAllByBookingDate(LocalDate bookingDate);
    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT 1 FROM booking WHERE venue_id = :venueId AND (:startDate < end_date AND :endDate > start_date) AND (:bookingId IS NULL OR id != :bookingId))")
    boolean existsByVenueIdAndDateRange(long venueId, LocalDate startDate, LocalDate endDate, Long bookingId);
}
