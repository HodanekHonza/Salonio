package com.salonio.modules.availability.infrastructure.persistence;

import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.availability.domain.Availability;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AvailabilityRepositoryAdapter implements AvailabilityPersistencePort {

    private final AvailabilityRepository availabilityRepository;

    @Override
    public Availability save(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public Optional<Availability> findById(UUID id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public Optional<Availability> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime) {
        return availabilityRepository.findSpecificAvailableSlot(staffId, startTime, endTime);
    }

}
