package com.salonio.modules.client.domain;

import com.salonio.modules.client.api.dto.ClientUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Client {

    private UUID id;

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

    public Client(UUID userId, Integer version, String firstName, String lastName, String phoneNumber, String email, LocalDate dateOfBirth, String gender, String addressLine, String city, String postalCode, String country, Boolean isActive, String notes, Integer loyaltyPoints, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.version = version;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.addressLine = addressLine;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.isActive = isActive;
        this.notes = notes;
        this.loyaltyPoints = loyaltyPoints;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Client(UUID userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
    }

    public Client updateEntity(ClientUpdateRequest request) {
        if (request.firstName() != null) this.firstName = request.firstName();
        if (request.lastName() != null) this.lastName = request.lastName();
        if (request.phoneNumber() != null) this.phoneNumber = request.phoneNumber();
        if (request.email() != null) this.email = request.email();
        if (request.dateOfBirth() != null) this.dateOfBirth = request.dateOfBirth();
        if (request.gender() != null) this.gender = request.gender();
        if (request.addressLine() != null) this.addressLine = request.addressLine();
        if (request.city() != null) this.city = request.city();
        if (request.postalCode() != null) this.postalCode = request.postalCode();
        if (request.country() != null) this.country = request.country();
        if (request.isActive() != null) this.isActive = request.isActive();
        if (request.notes() != null) this.notes = request.notes();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client client)) return false;
        return Objects.equals(id, client.id)
                && Objects.equals(userId, client.userId)
                && Objects.equals(version, client.version)
                && Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName)
                && Objects.equals(phoneNumber, client.phoneNumber)
                && Objects.equals(email, client.email)
                && Objects.equals(dateOfBirth, client.dateOfBirth)
                && Objects.equals(gender, client.gender)
                && Objects.equals(addressLine, client.addressLine)
                && Objects.equals(city, client.city)
                && Objects.equals(postalCode, client.postalCode)
                && Objects.equals(country, client.country)
                && Objects.equals(isActive, client.isActive)
                && Objects.equals(notes, client.notes)
                && Objects.equals(loyaltyPoints, client.loyaltyPoints)
                && Objects.equals(createdAt, client.createdAt)
                && Objects.equals(updatedAt, client.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                userId,
                version,
                firstName,
                lastName,
                phoneNumber,
                email,
                dateOfBirth,
                gender,
                addressLine,
                city,
                postalCode,
                country,
                isActive,
                notes,
                loyaltyPoints,
                createdAt,
                updatedAt
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("version", version)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("phoneNumber", phoneNumber)
                .append("email", email)
                .append("dateOfBirth", dateOfBirth)
                .append("gender", gender)
                .append("addressLine", addressLine)
                .append("city", city)
                .append("postalCode", postalCode)
                .append("country", country)
                .append("isActive", isActive)
                .append("notes", notes)
                .append("loyaltyPoints", loyaltyPoints)
                .append("createdAt", createdAt)
                .append("updatedAt", updatedAt)
                .toString();
    }

}
