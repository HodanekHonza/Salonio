package com.salonio.modules.client.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        Integer version,
        UUID userId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        LocalDate dateOfBirth,
        String gender,
        String addressLine,
        String city,
        String postalCode,
        String country,
        Boolean isActive,
        String notes,
        Integer loyaltyPoints,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
