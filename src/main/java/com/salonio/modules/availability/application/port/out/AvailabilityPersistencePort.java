package com.salonio.modules.availability.application.port.out;

import com.salonio.modules.availability.domain.Availability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityPersistencePort {

    Availability save(Availability availability);

    void saveAll(List<Availability> availability);

    Optional<Availability> findById(UUID id);

    void deleteById(UUID id);

    // TODO add businessId as well
    Optional<Availability> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime);

    Optional<Availability> findSpecificSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime);

    Page<Availability> findAvailabilityByBusinessIdAndDate(UUID businessId, LocalDate date, Pageable pageable);

    Page<Availability> findAvailabilityByBusinessIdAndStartEndDate(UUID businessId, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
