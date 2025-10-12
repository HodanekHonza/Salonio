package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.domain.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking", uniqueConstraints = {@UniqueConstraint(columnNames = {"startTime", "clientId", "staffId", "status"})})
public class BookingJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID clientId;

    private UUID staffId;

    private String serviceType;

    private BookingStatus status;

}

