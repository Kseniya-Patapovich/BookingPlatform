package com.bookingplatform.service;

import com.bookingplatform.model.Venue;
import com.bookingplatform.model.dto.VenueDto;
import com.bookingplatform.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found venue with id=" + id));
    }

    @Transactional
    public Long createVenue(VenueDto venueDto) {
        Venue venue = new Venue();
        if (venueDto.getCapacity() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Incorrect capacity!");
        }
        venue.setName(venueDto.getName());
        venue.setAddress(venueDto.getAddress());
        venue.setCapacity(venueDto.getCapacity());
        venueRepository.save(venue);
        return venue.getId();
    }

    @Transactional
    public void updateVenue(Long id, VenueDto venueDto) {
        Venue venue = getVenueById(id);
        if (venueDto.getCapacity() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Incorrect capacity!");
        }
        venue.setName(venueDto.getName());
        venue.setAddress(venueDto.getAddress());
        venue.setCapacity(venueDto.getCapacity());
        venueRepository.save(venue);
    }

    public void deleteVenue(Long id) {
        Venue deleteVenue = getVenueById(id);
        if (!deleteVenue.getBookings().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You cannot delete a venue if there is a booking on it!");
        }
        venueRepository.delete(deleteVenue);
    }
}
