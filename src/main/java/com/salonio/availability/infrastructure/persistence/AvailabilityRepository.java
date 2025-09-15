package com.salonio.availability.infrastructure.persistence;

import com.salonio.availability.domain.Availability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {

    @Query("SELECT a FROM Availability a WHERE a.staffId = :staffId AND a.startTime = :startTime AND a.endTime = :endTime AND a.availability IS TRUE")
    Optional<Availability> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT a FROM Availability a WHERE a.staffId = :staffId AND a.startTime >= :from AND a.endTime <= :to AND a.availability IS TRUE")
    Page<Availability> findAvailableSlotsInInterval(UUID staffId, LocalDateTime from, LocalDateTime to, Pageable pageable);

}
