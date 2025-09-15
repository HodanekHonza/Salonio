package com.salonio.booking.application.port.out;

import com.salonio.booking.domain.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingPersistencePort {
    Booking save(Booking booking);
    void deleteById(UUID id);
    Optional<Booking> findById(UUID id);
    List<Booking> findByClientId(UUID clientId);
}
