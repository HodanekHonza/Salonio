package com.salonio.modules.business.api.dto.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record BusinessCreateRequest(
        @NotBlank String businessName,
        @NotNull LocalDateTime creationDate,
        @NotBlank String address,
        @Email String email,
        @Pattern(regexp = "\\+?[0-9\\- ]+")
        String phoneNumber,
        String websiteUrl,
        String taxId,
        @NotBlank String city,
        @NotBlank String postalCode,
        @NotBlank String country,
        Double latitude,
        Double longitude,
        @NotBlank String businessType,
        LocalTime openingTime,
        LocalTime closingTime,
        boolean isActive,
        Double rating,
        Integer numberOfReviews,
        Integer totalBookings,
        String logoUrl,
        String description,
        @NotBlank String currency
) {}
