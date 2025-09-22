package com.salonio.modules.availability.domain;

import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.infrastructure.persistence.AvailabilityJpaEntity;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.infrastructure.persistence.BookingJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Availability {

    @Id
    private UUID id;

    @PrePersist
    public void assignId() {
        if (id == null) {
            id = UUID.randomUUID(); // TODO change
        }
    }

    @Version
    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID staffId;

    private UUID businessId;

    private boolean availability;

    private UUID bookingId;

    private UUID clientId;

    public Availability(LocalDateTime startTime, LocalDateTime endTime, UUID  staffId,
                 UUID businessId, boolean availability, UUID bookingId, UUID clientId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.staffId = staffId;
        this.businessId = businessId;
        this.availability = availability;
        this.bookingId = bookingId;
        this.clientId = clientId;
    }


    public Availability confirm(UUID bookingId, UUID clientId) {
        if (!availability) throw new IllegalStateException("No available available for staff " + staffId);
        this.availability = false;
        this.bookingId = bookingId;
        this.clientId = clientId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Availability other)) return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
