package com.salonio.availability.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Availability {

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

    @Getter
    @Setter
    private LocalDateTime startTime;

    @Getter
    @Setter
    private LocalDateTime endTime;

    @Getter
    @Setter
    private UUID staffId;

    // TODO BusinessId

    // TODO ClientId as well if its availability false

    @Getter
    @Setter
    private boolean availability;

}
