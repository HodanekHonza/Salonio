package com.salonio.modules.availability.api.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAvailabilityRequest(

        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        LocalDateTime startTime,

        @NotNull(message = "End time is required")
        LocalDateTime endTime,

        @NotNull(message = "Staff ID is required")
        UUID staffId,

        @NotNull(message = "Business ID is required")
        UUID businessId,

        boolean availability,

        UUID bookingId,

        UUID clientId
) {
    @AssertTrue(message = "End time must be after start time")
    private boolean isEndTimeAfterStartTime() {
        if (startTime == null || endTime == null) {
            return true; // Let @NotNull handle null validation
        }
        return endTime.isAfter(startTime);
    }

    @AssertTrue(message = "Booking must have a client ID when availability is false")
    private boolean isBookingValid() {
        if (!availability && bookingId != null) {
            return clientId != null;
        }
        return true;
    }

    @AssertTrue(message = "Time slot duration must be between 15 minutes and 24 hours")
    private boolean isValidDuration() {
        if (startTime == null || endTime == null) {
            return true;
        }
        long minutes = java.time.Duration.between(startTime, endTime).toMinutes();
        return minutes >= 15 && minutes <= 1440;
    }

}