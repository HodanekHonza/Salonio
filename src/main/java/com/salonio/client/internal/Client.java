package com.salonio.client.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.Getter;
import java.util.UUID;

@Entity
public class Client {

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

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

}
