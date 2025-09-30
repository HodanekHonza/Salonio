package com.salonio.modules.business.api.dto.business;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record BusinessResponse(UUID id,
                               Integer version,
                               String businessName,
                               LocalDateTime creationDate,
                               String address,
                               String email,
                               String phoneNumber,
                               String websiteUrl,
                               String taxId,
                               String city,
                               String postalCode,
                               String country,
                               Double latitude,
                               Double longitude,
                               String businessType,
                               LocalTime openingTime,
                               LocalTime closingTime,
                               boolean isActive,
                               double rating,
                               int numberOfReviews,
                               int totalBookings,
                               String logoUrl,
                               String description,
                               String currency) {
}
