package com.salonio.availability.internal;

import com.salonio.availability.AvailabilityApi;
import com.salonio.availability.dto.AvailabilityResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class AvailabilityService implements AvailabilityApi {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Transactional
    @Override
    public AvailabilityResponse createAvailability() {
        final var availability = new Availability();
        availabilityRepository.save(availability);
        return new AvailabilityResponse();
    }

    @Override
    public AvailabilityResponse getAvailability(UUID availabilityId) {
        final Availability availability = availabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Availability with id " + availabilityId + " not found"));
        return new AvailabilityResponse();
    }

    @Override
    public AvailabilityResponse updateAvailability() {
        return new AvailabilityResponse();
    }

    @Override
    public void deleteAvailability() {
    }

    public AvailabilityResponse listAvailabilityByStaffIdAndFixedDate(UUID staffId) {
        return new AvailabilityResponse();
    }

    public AvailabilityResponse listAvailabilityByStaffIdAndIntervalDate(UUID staffId) {
        return new AvailabilityResponse();
    }

}
