package com.salonio.modules.availability.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<AvailabilityJpaEntity, UUID> {

    @Query("SELECT a FROM AvailabilityJpaEntity a WHERE a.staffId = :staffId AND a.startTime = :startTime AND a.endTime = :endTime AND a.availability IS TRUE")
    Optional<AvailabilityJpaEntity> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime);

    Page<AvailabilityJpaEntity> findAvailabilityByBusinessIdAndDate(UUID businessId, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);

}
