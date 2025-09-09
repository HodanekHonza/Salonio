package com.salonio.availability.internal;

import com.salonio.availability.AvailabilityApi;
import com.salonio.availability.dto.AvailabilityResponse;
import com.salonio.availability.dto.CreateAvailabilityRequest;
import com.salonio.availability.dto.UpdateAvailabilityRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ConcurrentModificationException;
import java.util.UUID;

@Service
public class AvailabilityService implements AvailabilityApi {

    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityMapper availabilityMapper;

    public AvailabilityService(AvailabilityRepository availabilityRepository, AvailabilityMapper availabilityMapper) {
        this.availabilityRepository = availabilityRepository;
        this.availabilityMapper = availabilityMapper;
    }

    @Transactional
    @Override
    public AvailabilityResponse createAvailability(CreateAvailabilityRequest createAvailabilityRequest) {
        final var availability = new Availability();
        availabilityRepository.save(availability);
        return availabilityMapper.toResponse(availability);
    }

    @Override
    public AvailabilityResponse getAvailability(UUID availabilityId) {
        final Availability availability = availabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Availability with id " + availabilityId + " not found"));
        return availabilityMapper.toResponse(availability);
    }

    @Override
    public AvailabilityResponse updateAvailability(UpdateAvailabilityRequest updateAvailabilityRequest) {
        final var existingAvailability = availabilityRepository.findById(updateAvailabilityRequest.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Availability with id " + updateAvailabilityRequest.clientId() + " not found"));

        availabilityMapper.updateEntity(updateAvailabilityRequest, existingAvailability);

        Availability updatedAvailability;
        try {
            updatedAvailability = availabilityRepository.save(existingAvailability);
        } catch (Exception e) {
            throw new ConcurrentModificationException("Booking with id was modified concurrently. Please retry.", e);
        }

        return availabilityMapper.toResponse(updatedAvailability);
    }

    @Override
    public void deleteAvailability(UUID availabilityId) {
        availabilityRepository.deleteById(availabilityId);
    }


//    public AvailabilityResponse listAvailabilityByStaffIdAndFixedDate(UUID staffId) {
//        return new AvailabilityResponse();
//    }
//
//    public AvailabilityResponse listAvailabilityByStaffIdAndIntervalDate(UUID staffId) {
//        return new AvailabilityResponse();
//    }
//
}
