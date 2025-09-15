package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByClientId(UUID clientId);

    List<Booking> findByStartTime(LocalDateTime startTime);

    List<Booking> findByStartTimeAndClientId(LocalDateTime startTime, UUID clientId);

    List<Booking> findByStartTimeAndStaffId(LocalDateTime startTime, UUID staffId);

    @Query("SELECT b FROM Booking b WHERE b.staffId = :staffId AND b.startTime BETWEEN :from AND :to")
    Page<Booking> findBookingsForStaffBetween(UUID staffId, LocalDateTime from, LocalDateTime to, Pageable pageable);

    List<Booking> getBookingById(UUID id);
}