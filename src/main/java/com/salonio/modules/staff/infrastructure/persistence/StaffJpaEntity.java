package com.salonio.modules.staff.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class StaffJpaEntity {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private UUID userId;

    private String firstName;

    private String lastName;

    private UUID businessId;

    private String phoneNumber;

    @Email
    private String email;

    @Email
    private String workEmail;

    private String workPhoneNumber;

    private String role;
    private String specialization;

    private Boolean isActive = true;

    private LocalDate hireDate;
    private LocalDate terminationDate;

    private BigDecimal salary;
    private String employmentType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

