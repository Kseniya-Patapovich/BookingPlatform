package com.bookingplatform.settings;

import com.bookingplatform.model.Booking;
import com.bookingplatform.model.Venue;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public SimpleModule simpleModule(BookingDeserializer bookingDeserializer, VenueDeserializer venueDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Booking.class, bookingDeserializer);
        module.addDeserializer(Venue.class, venueDeserializer);
        return module;
    }
}
