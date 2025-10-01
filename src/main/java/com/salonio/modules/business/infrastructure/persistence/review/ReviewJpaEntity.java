package com.salonio.modules.business.infrastructure.persistence.review;

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
public class ReviewJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private String text;

    private UUID clientId;

    private UUID businessId;

    private Integer rating;

}
