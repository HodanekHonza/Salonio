package com.salonio.modules.business.infrastructure.persistence.category.business;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BusinessCategoryJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private com.salonio.modules.business.domain.enums.BusinessCategory name;

    private Integer numberOfBusinesses;

}
