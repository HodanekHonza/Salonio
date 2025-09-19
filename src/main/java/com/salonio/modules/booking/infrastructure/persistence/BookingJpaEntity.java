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

    public static Booking toDomain(BookingJpaEntity entity) {
        Booking booking = new Booking(entity.getStartTime(), entity.getEndTime(),
                entity.getClientId(), entity.getStaffId(), entity.getServiceType(), entity.getStatus());
        booking.setId(entity.getId());
        booking.setVersion(entity.getVersion());
        return booking;
    }

    public static BookingJpaEntity fromDomain(Booking booking) {
        BookingJpaEntity entity = new BookingJpaEntity();
        entity.id = booking.getId();
        entity.version = booking.getVersion();
        entity.startTime = booking.getStartTime();
        entity.endTime = booking.getEndTime();
        entity.clientId = booking.getClientId();
        entity.staffId = booking.getStaffId();
        entity.serviceType = booking.getServiceType();
        entity.status = booking.getStatus();
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingJpaEntity)) return false;
        BookingJpaEntity other = (BookingJpaEntity) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

