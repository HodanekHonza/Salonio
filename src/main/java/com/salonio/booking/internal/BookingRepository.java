package com.salonio.booking.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByServiceType(String name);

    List<Booking> findByStartTime(LocalDateTime startTime);

    List<Booking> findByStartTimeAndClientId(LocalDateTime startTime, UUID clientId);

    List<Booking> findByStartTimeAndStaffId(LocalDateTime startTime, UUID staffId);

    @Query("SELECT b FROM Booking b WHERE b.staffId = :staffId AND b.startTime BETWEEN :from AND :to")
    List<Booking> findBookingsForStaffBetween(UUID staffId, LocalDateTime from, LocalDateTime to);

}