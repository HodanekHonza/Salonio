package com.salonio.availability.internal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {

    // Method 1: For a specific, single slot lookup
    // Useful for a final check just before booking.
    @Query("SELECT a FROM Availability a WHERE a.staffId = :staffId AND a.startTime = :startTime AND a.endTime = :endTime AND a.availability IS TRUE")
    Optional<Availability> findSpecificAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime);


    // Method 2: For listing all available slots in a time range
    // Useful for populating a list for the user on the front end.
    @Query("SELECT a FROM Availability a WHERE a.staffId = :staffId AND a.startTime >= :from AND a.endTime <= :to AND a.availability IS TRUE")
    Page<Availability> findAvailableSlotsInInterval(UUID staffId, LocalDateTime from, LocalDateTime to, Pageable pageable);

}
