package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.api.AvailabilityApi;
import com.salonio.modules.availability.api.dto.AvailabilityBulkResponse;
import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.modules.availability.application.factory.AvailabilityFactory;
import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.availability.domain.Availability;
import com.salonio.modules.availability.exception.AvailabilityExceptions;
import com.salonio.modules.availability.infrastructure.persistence.AvailabilityMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AvailabilityService implements AvailabilityApi {

    private final AvailabilityPersistencePort availabilityPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Transactional
    @Override
    public AvailabilityResponse createAvailability(CreateAvailabilityRequest createAvailabilityRequest) {
        final Availability savedAvailability = createSingleAvailability(createAvailabilityRequest);
        return AvailabilityMapper.toResponse(savedAvailability);
    }

    @Transactional
    @Override
    public AvailabilityBulkResponse createBulkAvailability(List<CreateAvailabilityRequest> createAvailabilityRequestsList) {
        final List<AvailabilityResponse> responses = createAvailabilityRequestsList.stream()
                .map(this::createSingleAvailability)
                .map(AvailabilityMapper::toResponse)
                .toList();
        return AvailabilityMapper.createBulkResponse(responses.size(), responses);
    }

    @Override
    public AvailabilityResponse getAvailability(UUID availabilityId) {
        final Availability availability = findAvailabilityById(availabilityId);
        return AvailabilityMapper.toResponse(availability);
    }

    @Override
    public Page<AvailabilityResponse> listAvailabilityByDateAndBusinessId(UUID businessId, LocalDate date, Pageable pageable) {
        final Page<Availability> foundAvailabilityPage = availabilityPersistencePort
                .findAvailabilityByBusinessIdAndDate(businessId, date, pageable);
        return foundAvailabilityPage.map(AvailabilityMapper::toResponse);
    }

    @Transactional
    @Override
    public AvailabilityResponse updateAvailability(UUID availabilityId, UpdateAvailabilityRequest updateAvailabilityRequest) {
        final Availability existingAvailability = findAvailabilityById(availabilityId);
        checkAvailabilityValidity(existingAvailability);

        final Availability updatedAvailability = applyUpdate(updateAvailabilityRequest, existingAvailability);
        final Availability savedAvailability = saveAvailability(updatedAvailability);

        return AvailabilityMapper.toResponse(savedAvailability);
    }

    @Transactional
    @Override
    public void deleteAvailability(UUID availabilityId) {
        // add validity check as well
        try {
            availabilityPersistencePort.deleteById(availabilityId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting availability failed");
            throw new AvailabilityExceptions.AvailabilityNotFoundException("Availability with id " + availabilityId + " not found");
        }
    }

    private Availability createSingleAvailability(CreateAvailabilityRequest createAvailabilityRequest) {
        checkIfAvailabilityAlreadyExists(createAvailabilityRequest);
        final Availability newAvailability = AvailabilityFactory.create(createAvailabilityRequest);
        return saveAvailability(newAvailability);
    }

    private void checkIfAvailabilityAlreadyExists(CreateAvailabilityRequest createAvailabilityRequest) {
        final var staffId = createAvailabilityRequest.staffId();
        final var startTime = createAvailabilityRequest.startTime();
        final var endTime = createAvailabilityRequest.endTime();
        final Optional<Availability> foundSlot = availabilityPersistencePort.findSpecificSlot(
                staffId,
                startTime,
                endTime
        );
        if (foundSlot.isPresent()) {
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Availability already exists! StaffId: " + staffId + ", originalStartTime: "
                            + startTime + ", originalEndTime: " + endTime);
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

    private Availability applyUpdate(UpdateAvailabilityRequest updateAvailabilityRequest, Availability existingAvailability) {
        try {
            return existingAvailability.updateEntity(updateAvailabilityRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating availability failed");
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Availability with id was modified concurrently. Please retry.", e);
        }

    }

    private void checkAvailabilityValidity(Availability existingAvailability) {
        if (!existingAvailability.isAvailability()) {
            throw new AvailabilityExceptions.AvailabilityConflictException("Cant directly update slot that is taken");
        }
    }

}