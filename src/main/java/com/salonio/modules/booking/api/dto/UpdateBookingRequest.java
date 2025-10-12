package com.salonio.modules.booking.api.dto;

import com.salonio.modules.booking.domain.enums.BookingStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateBookingRequest(

        @NotNull(message = "Client ID is required")
        UUID clientId,

        @NotNull(message = "Staff ID is required")
        UUID staffId,

        @NotNull(message = "Start time is required")
        LocalDateTime startTime,

        @NotNull(message = "End time is required")
        LocalDateTime endTime,

        @NotBlank(message = "Service type is required")
        @Size(min = 2, max = 50, message = "Service type must be between 2 and 50 characters")
        @Pattern(regexp = "^[A-Z_]+$", message = "Service type must be uppercase with underscores (e.g., HAIRCUT, HAIR_COLORING)")
        String serviceType,

        @NotNull(message = "Booking status is required")
        BookingStatus status

) {
    @AssertTrue(message = "End time must be after start time")
    private boolean isEndTimeAfterStartTime() {
        if (startTime == null || endTime == null) {
            return true;
        }
        return endTime.isAfter(startTime);
    }

    @AssertTrue(message = "Booking duration must be between 15 minutes and 8 hours")
    private boolean isValidDuration() {
        if (startTime == null || endTime == null) {
            return true;
        }
        long durationMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
        return durationMinutes >= 15 && durationMinutes <= 480;
    }

    @AssertTrue(message = "Cannot cancel a booking that has already started")
    private boolean isValidCancellation() {
        if (status != BookingStatus.CANCELLED || startTime == null) {
            return true;
        }
        return startTime.isAfter(LocalDateTime.now());
    }

}