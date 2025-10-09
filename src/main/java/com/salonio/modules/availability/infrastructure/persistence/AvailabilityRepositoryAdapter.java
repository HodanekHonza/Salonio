package com.salonio.modules.availability.infrastructure.persistence;

import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.availability.domain.Availability;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AvailabilityRepositoryAdapter implements AvailabilityPersistencePort {

    private final AvailabilityRepository availabilityRepository;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityRepositoryAdapter.class);

    @Override
    public Availability save(Availability availability) {
        final AvailabilityJpaEntity availabilityJpaEntity = AvailabilityMapper.fromDomain(availability);

        final AvailabilityJpaEntity savedAvailabilityJpaEntity = availabilityRepository.save(availabilityJpaEntity);

        return AvailabilityMapper.toDomain(savedAvailabilityJpaEntity);
    }

    @Override
    public Optional<Availability> findById(UUID id) {
        logger.debug("Finding availability with id: {}", id);
        return availabilityRepository.findById(id)
                .map(AvailabilityMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting availability with id: {}", id);
        availabilityRepository.deleteById(id);
    }

    @Override
    public Optional<Availability> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime,
                                                            LocalDateTime endTime) {
        logger.debug("Finding available slot for staff: {} between {} and {}", staffId, startTime, endTime);
        return availabilityRepository.findSpecificAvailableSlot(staffId, startTime, endTime)
                .map(AvailabilityMapper::toDomain);
    }

    @Override
    public Optional<Availability> findSpecificSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime) {
        logger.debug("Finding slot for staff: {} between {} and {}", staffId, startTime, endTime);
        return availabilityRepository.findSpecificSlot(staffId, startTime, endTime)
                .map(AvailabilityMapper::toDomain);
    }

    @Override
    public Page<Availability> findAvailabilityByBusinessIdAndDate(UUID businessId, LocalDate date, Pageable pageable) {
        logger.debug("Finding available slots for business: {}. Date: {}. ", businessId, date);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        return availabilityRepository.findAvailabilityByBusinessIdAndStartTimeAndEndTime(businessId, startOfDay, endOfDay, pageable)
                .map(AvailabilityMapper::toDomain);
    }

}
