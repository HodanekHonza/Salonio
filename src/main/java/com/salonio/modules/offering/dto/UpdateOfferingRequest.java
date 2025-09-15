package com.salonio.modules.offering.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateOfferingRequest(@NotNull UUID businessId,
                                    @NotNull @NotBlank String textForm) {
}
