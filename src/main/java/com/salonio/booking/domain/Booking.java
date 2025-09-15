package com.salonio.booking.domain;

import com.salonio.booking.domain.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"startTime", "clientId", "staffId"})})
public class Booking {

    @Id
    @Getter
    private UUID id;

    @PrePersist
    public void assignId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Version
    @Getter
    private Integer version;

    @Setter
    @Getter
    private LocalDateTime startTime;

    @Setter
    @Getter
    private LocalDateTime endTime;

    @Setter
    @Getter
    private UUID clientId;

    @Setter
    @Getter
    private UUID staffId;

    @Setter
    @Getter
    private String serviceType;

    @Setter
    @Getter
    private BookingStatus status;

    public Booking(LocalDateTime startTime, LocalDateTime endTime, UUID clientId,
                   UUID staffId, String serviceType, BookingStatus status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceType = serviceType;
        this.status = status;
    }

    public Booking() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking other = (Booking) o;
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
