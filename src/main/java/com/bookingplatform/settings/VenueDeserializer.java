package com.bookingplatform.settings;

import com.bookingplatform.model.Booking;
import com.bookingplatform.model.Venue;
import com.bookingplatform.repository.BookingRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VenueDeserializer extends JsonDeserializer<Venue> {
    private final BookingRepository bookingRepository;

    @Override
    public Venue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        Venue venue = mapper.readValue(jsonParser, Venue.class);

        if (venue.getBookings() != null && !venue.getBookings().isEmpty()) {
            List<Booking> bookings = new ArrayList<>();
            for (Booking booking : venue.getBookings()) {
                if (booking.getId() != null) {
                    Booking fullBooking = bookingRepository.findById(booking.getId())
                            .orElseThrow(() -> new IOException("Booking not found with ID: " + booking.getId()));
                    bookings.add(fullBooking);
                }
            }
            venue.setBookings(bookings);
        }

        return venue;
    }
}
