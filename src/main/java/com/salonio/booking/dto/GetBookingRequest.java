package com.salonio.booking.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record GetBookingRequest(@NotNull UUID id) {
}
