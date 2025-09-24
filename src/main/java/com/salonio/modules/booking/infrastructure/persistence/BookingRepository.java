package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingJpaEntity, UUID> {
    Page<BookingJpaEntity> findByClientId(UUID clientId, Pageable pageable);

    Page<BookingJpaEntity> findByStaffId(UUID clientId, Pageable pageable);

    List<BookingJpaEntity> findByStartTime(LocalDateTime startTime);

    List<BookingJpaEntity> findByStartTimeAndClientId(LocalDateTime startTime, UUID clientId);

    List<BookingJpaEntity> findByStartTimeAndStaffId(LocalDateTime startTime, UUID staffId);

    @Query("SELECT b FROM BookingJpaEntity b WHERE b.staffId = :staffId AND b.startTime BETWEEN :from AND :to")
    Page<BookingJpaEntity> findBookingsForStaffBetween(UUID staffId, LocalDateTime from, LocalDateTime to, Pageable pageable);

}