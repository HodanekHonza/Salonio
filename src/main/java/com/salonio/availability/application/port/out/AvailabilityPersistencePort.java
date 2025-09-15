package com.salonio.availability.application.port.out;

import com.salonio.availability.domain.Availability;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityPersistencePort {
    Availability save(Availability availability);

    Optional<Availability> findById(UUID id);

    void deleteById(UUID id);

    Optional<Availability> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime);
}
