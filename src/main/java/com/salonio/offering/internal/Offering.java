package com.salonio.offering.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
public class Offering {

    @Id
    @Getter
    private UUID id;

    @PrePersist
    public void assignId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Getter @Setter
    private String textForm;

    @Getter @Setter
    private UUID businessId;

    Offering(String textForm, UUID businessId) {
        this.textForm = textForm;
        this.businessId = businessId;
    }

}
