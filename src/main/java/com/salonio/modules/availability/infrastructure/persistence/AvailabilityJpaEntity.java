package com.salonio.modules.availability.infrastructure.persistence;

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

    @Version
    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID staffId;

    private UUID businessId;

    private boolean availability;

    private UUID bookingId;

    private UUID clientId;

}
