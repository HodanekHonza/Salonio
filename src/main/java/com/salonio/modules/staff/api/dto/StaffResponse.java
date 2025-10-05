package com.salonio.modules.staff.api.dto;

import jakarta.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record StaffResponse(
        UUID id,
        Integer version,
        UUID userId,
        String firstName,
        String lastName,
        UUID businessId,
        String phoneNumber,
        @Email String email,
        @Email String workEmail,
        String workPhoneNumber,
        String role,
        String specialization,
        Boolean isActive,
        LocalDate hireDate,
        LocalDate terminationDate,
        BigDecimal salary,
        String employmentType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
