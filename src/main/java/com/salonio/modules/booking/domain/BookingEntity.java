package com.salonio.modules.booking.domain;

import com.salonio.modules.booking.domain.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingEntity {

    @Getter
    private final UUID id;

    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID clientId;

    private UUID staffId;

    private String serviceType;

    private BookingStatus status;

    public BookingEntity(LocalDateTime startTime, LocalDateTime endTime, UUID clientId,
                         UUID staffId, String serviceType) {
        this.id = UUID.randomUUID();
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceType = serviceType;
        this.status = BookingStatus.PENDING;
    }

    public BookingEntity(UUID id, LocalDateTime startTime, LocalDateTime endTime,
                         UUID clientId, UUID staffId, String serviceType, BookingStatus status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceType = serviceType;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingEntity)) return false;
        BookingEntity other = (BookingEntity) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void confirm() {
        if (this.status == BookingStatus.CONFIRMED) throw new RuntimeException("Booking already confirmed");
        this.status = BookingStatus.CONFIRMED;
    }

}
