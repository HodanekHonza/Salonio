package com.salonio.modules.client.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ClientUpdateRequest(

        @Size(max = 50)
        String firstName,

        @Size(max = 50)
        String lastName,

        @Size(max = 20)
        String phoneNumber,

        @Email
        String email,

        LocalDate dateOfBirth,

        @Size(max = 10)
        String gender,

        @Size(max = 255)
        String addressLine,

        @Size(max = 100)
        String city,

        @Size(max = 20)
        String postalCode,

        @Size(max = 50)
        String country,

        Boolean isActive,

        @Size(max = 1000)
        String notes

) {
}
