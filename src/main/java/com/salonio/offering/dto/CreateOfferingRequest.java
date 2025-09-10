package com.salonio.offering.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateOfferingRequest (@NotNull UUID businessId,
                                     @NotNull @NotBlank String textForm) {
}
