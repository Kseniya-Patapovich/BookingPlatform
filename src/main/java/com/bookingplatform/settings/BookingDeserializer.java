package com.bookingplatform.settings;

import com.bookingplatform.model.Booking;
import com.bookingplatform.model.Venue;
import com.bookingplatform.repository.VenueRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BookingDeserializer extends JsonDeserializer<Booking> {
    private final VenueRepository venueRepository;

    @Override
    public Booking deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        Booking booking = mapper.readValue(jsonParser, Booking.class);
        if (booking.getVenue() != null && booking.getVenue().getId() != null) {
            Venue venue = venueRepository.findById(booking.getVenue().getId())
                    .orElseThrow(() -> new IOException("Venue not found with ID: " + booking.getVenue().getId()));
            booking.setVenue(venue);
        }

        return booking;
    }
}
