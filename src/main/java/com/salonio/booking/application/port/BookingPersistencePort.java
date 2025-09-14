package com.salonio.booking.application.port;

import com.salonio.booking.domain.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingPersistencePort {
    Booking save(Booking booking);
    Optional<Booking> findById(UUID id);
    List<Booking> findByClientId(UUID clientId);
    List<Booking> findByStartTimeAndStaffId(LocalDateTime startTime, UUID staffId);
}
