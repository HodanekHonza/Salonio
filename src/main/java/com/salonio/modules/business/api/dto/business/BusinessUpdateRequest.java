package com.salonio.modules.business.api.dto.business;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record BusinessUpdateRequest(
        @NotBlank(message = "Business name is required")
        @Size(min = 2, max = 100, message = "Business name must be between 2 and 100 characters")
        String businessName,

        @NotNull(message = "Creation date is required")
        @PastOrPresent(message = "Creation date cannot be in the future")
        LocalDateTime creationDate,

        @NotBlank(message = "Address is required")
        @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
        String address,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format (E.164 standard)")
        String phoneNumber,

        @Pattern(regexp = "^(https?://)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$",
                message = "Invalid website URL format")
        @Size(max = 255, message = "Website URL must not exceed 255 characters")
        String websiteUrl,

        @Pattern(regexp = "^[A-Z0-9]{8,15}$", message = "Tax ID must be 8-15 alphanumeric characters")
        String taxId,

        @NotBlank(message = "City is required")
        @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
        String city,

        @NotBlank(message = "Postal code is required")
        @Pattern(regexp = "^[A-Za-z0-9\\s-]{3,10}$", message = "Invalid postal code format")
        String postalCode,

        @NotBlank(message = "Country is required")
        @Size(min = 2, max = 2, message = "Country must be a 2-letter ISO code")
        @Pattern(regexp = "^[A-Z]{2}$", message = "Country must be uppercase ISO 3166-1 alpha-2 code")
        String country,

        @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
        @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
        Double latitude,

        @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
        @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
        Double longitude,

        @NotBlank(message = "Business type is required")
        @Size(min = 2, max = 50, message = "Business type must be between 2 and 50 characters")
        String businessType,

        LocalTime openingTime,

        LocalTime closingTime,

        boolean isActive,

        @DecimalMin(value = "0.0", message = "Rating must be >= 0")
        @DecimalMax(value = "5.0", message = "Rating must be <= 5")
        Double rating,

        @Min(value = 0, message = "Number of reviews cannot be negative")
        Integer numberOfReviews,

        @Min(value = 0, message = "Total bookings cannot be negative")
        Integer totalBookings,

        @Pattern(regexp = "^(https?://).*\\.(jpg|jpeg|png|gif|svg|webp)$",
                message = "Logo URL must be a valid image URL")
        @Size(max = 500, message = "Logo URL must not exceed 500 characters")
        String logoUrl,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be a 3-letter ISO code")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be uppercase ISO 4217 code (e.g., USD, EUR)")
        String currency
) {

    @AssertTrue(message = "Opening time must be before closing time")
    private boolean isValidOperatingHours() {
        if (openingTime == null || closingTime == null) {
            return true;
        }
        return openingTime.isBefore(closingTime);
    }

    @AssertTrue(message = "Both latitude and longitude must be provided together")
    private boolean isValidLocation() {
        return (latitude == null && longitude == null) || (latitude != null && longitude != null);
    }

}
