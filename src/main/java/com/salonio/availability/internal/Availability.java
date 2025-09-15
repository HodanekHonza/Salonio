package com.salonio.availability.internal;

import com.salonio.availability.event.AvailabilitySlotConfirmedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class Availability {

    @Id
    private UUID id;

    @PrePersist
    public void assignId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Version
    private Integer version;

    @Setter
    private LocalDateTime startTime;

    @Setter
    private LocalDateTime endTime;

    @Setter
    private UUID staffId;

    @Setter
    private UUID businessId;

    @Setter
    private boolean availability;

    @Setter
    private UUID bookingId;

    @Setter
    private UUID clientId;


    public AvailabilitySlotConfirmedEvent confirm(UUID bookingId, UUID clientId) {
        if (!availability) throw new IllegalStateException("No available available for staff " + staffId);
        this.availability = false;
        this.bookingId = bookingId;
        this.clientId = clientId;
        return new AvailabilitySlotConfirmedEvent(bookingId);
    }

}
