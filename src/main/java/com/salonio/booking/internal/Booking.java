package com.salonio.booking.internal;

import com.salonio.booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"startTime", "clientId", "staffId"})})
class Booking {

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

//    @Setter
//    @Getter
//    private LocalDateTime endTime;


    @Setter
    @Getter
    private Duration duration;

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

    public Booking(LocalDateTime startTime, Duration duration, UUID clientId,
                   UUID staffId, String serviceType, BookingStatus status) {
        this.startTime = startTime;
        this.duration = duration;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceType = serviceType;
        this.status = status;
    }

    public Booking() {

    }

}
