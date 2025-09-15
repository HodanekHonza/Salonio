package com.salonio.availability.application.service;

import com.salonio.availability.api.AvailabilityApi;
import com.salonio.availability.api.dto.AvailabilityResponse;
import com.salonio.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.availability.application.factory.AvailabilityFactory;
import com.salonio.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.availability.domain.Availability;
import com.salonio.availability.exception.AvailabilityExceptions;
import com.salonio.availability.infrastructure.persistence.AvailabilityMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ConcurrentModificationException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AvailabilityService implements AvailabilityApi {

    private final AvailabilityPersistencePort availabilityPersistencePort;
    private final AvailabilityMapper availabilityMapper;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Transactional
    @Override
    public AvailabilityResponse createAvailability(CreateAvailabilityRequest createAvailabilityRequest) {
        final Availability newAvailability = AvailabilityFactory.create(createAvailabilityRequest);
        final Availability savedAvailability = saveAvailability(newAvailability);
        return availabilityMapper.toResponse(savedAvailability);
    }

    @Override
    public AvailabilityResponse getAvailability(UUID availabilityId) {
        final Availability availability = findAvailabilityById(availabilityId);
        return availabilityMapper.toResponse(availability);
    }

    @Transactional
    @Override
    public AvailabilityResponse updateAvailability(UUID availabilityId, UpdateAvailabilityRequest updateAvailabilityRequest) {
        final Availability existingAvailability = findAvailabilityById(availabilityId);

        final Availability updatedAvailability = updateAvailabilityEntity(updateAvailabilityRequest, existingAvailability);

        return availabilityMapper.toResponse(updatedAvailability);
    }

    @Transactional
    @Override
    public void deleteAvailability(UUID availabilityId) {
        try {
            availabilityPersistencePort.deleteById(availabilityId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting availability failed");
            throw new AvailabilityExceptions.AvailabilityNotFoundException("Availability with id " + availabilityId + " not found");
        }
    }

    private Availability findAvailabilityById(UUID availabilityId) {
        return availabilityPersistencePort.findById(availabilityId)
                .orElseThrow(() -> {
                    logger.error("Finding availability with id {} failed", availabilityId);
                    return new AvailabilityExceptions.AvailabilityNotFoundException("Availability with id " + availabilityId + " not found");
                });
    }

    private Availability saveAvailability(Availability newAvailability) {
        try {
            return availabilityPersistencePort.save(newAvailability);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving availability failed");
            throw new AvailabilityExceptions.AvailabilityConflictException("Saving availability conflict");
        }
    }

    private Availability updateAvailabilityEntity(UpdateAvailabilityRequest updateAvailabilityRequest, Availability existingAvailability) {
        try {
            return availabilityMapper.updateEntity(updateAvailabilityRequest, existingAvailability);
        } catch (ConcurrentModificationException e) {
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Availability with id was modified concurrently. Please retry.", e);
        }
    }

}