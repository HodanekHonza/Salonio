package com.salonio.modules.business.infrastructure.persistence.category.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service-category")
public class ServiceCategoryJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private String name;

    private String description;

    private boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    private String icon;

    private String colorCode;

}
