package com.salonio.modules.availability.infrastructure.persistence;

import com.salonio.modules.availability.domain.Availability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "availability")
public class AvailabilityJpaEntity {
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

    public static Availability toDomain(AvailabilityJpaEntity entity) {
        Availability availability = new Availability(entity.getStartTime(), entity.getEndTime(),
                entity.getStaffId(), entity.getBusinessId(), entity.isAvailability(), entity.getBookingId(), entity.getClientId());
        availability.setId(entity.getId());
        availability.setVersion(entity.getVersion());
        return availability;
    }

    public static AvailabilityJpaEntity fromDomain(Availability booking) {
        AvailabilityJpaEntity entity = new AvailabilityJpaEntity();
        entity.id = booking.getId();
        entity.version = booking.getVersion();
        entity.startTime = booking.getStartTime();
        entity.endTime = booking.getEndTime();
        entity.staffId = booking.getStaffId();
        entity.businessId = booking.getBusinessId();
        entity.availability= booking.isAvailability();
        entity.bookingId = booking.getBookingId();
        entity.clientId = booking.getClientId();
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityJpaEntity other)) return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
