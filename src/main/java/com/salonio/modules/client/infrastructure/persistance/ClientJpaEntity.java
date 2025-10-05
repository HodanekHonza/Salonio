package com.salonio.modules.client.infrastructure.persistance;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class ClientJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private UUID userId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @Email
    private String email;

    private LocalDate dateOfBirth;

    private String gender;

    private String addressLine;

    private String city;

    private String postalCode;

    private String country;

    private Boolean isActive = true;

    @Column(length = 1000)
    private String notes;

    private Integer loyaltyPoints = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
