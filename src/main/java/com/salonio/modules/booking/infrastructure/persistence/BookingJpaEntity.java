package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking", uniqueConstraints = {@UniqueConstraint(columnNames = {"startTime", "clientId", "staffId"})})
public class BookingJpaEntity {

    @Id
    @Getter
    @Setter
    private UUID id;

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

    public Booking toDomain() {
        return new Booking(startTime, endTime, clientId, staffId, serviceType, status);
    }

    public static BookingJpaEntity fromDomain(Booking booking) {
        BookingJpaEntity entity = new BookingJpaEntity();
        entity.id = booking.getId();
        entity.startTime = booking.getStartTime();
        entity.endTime = booking.getEndTime();
        entity.clientId = booking.getClientId();
        entity.staffId = booking.getStaffId();
        entity.serviceType = booking.getServiceType();
        entity.status = booking.getStatus();
        return entity;
    }
}

