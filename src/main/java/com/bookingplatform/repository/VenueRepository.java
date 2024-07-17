package com.bookingplatform.repository;

import com.bookingplatform.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    boolean existsById(Long id);
}
