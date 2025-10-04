package com.salonio.modules.business.infrastructure.persistence.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer durationMinutes;

    private Boolean isActive;

    private UUID businessId;

}
