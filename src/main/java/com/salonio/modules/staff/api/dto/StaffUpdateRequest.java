package com.salonio.modules.staff.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record StaffUpdateRequest(

        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-ZÀ-ž\\s'-]+$", message = "First name can only contain letters, spaces, hyphens and apostrophes")
        String firstName,

        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-ZÀ-ž\\s'-]+$", message = "Last name can only contain letters, spaces, hyphens and apostrophes")
        String lastName,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format (E.164 standard)")
        String phoneNumber,

        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        String email,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @Pattern(regexp = "^(Male|Female|Other|Prefer not to say)$",
                message = "Gender must be one of: Male, Female, Other, Prefer not to say")
        String gender,

        @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
        String addressLine,

        @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
        @Pattern(regexp = "^[a-zA-ZÀ-ž\\s'-]+$", message = "City can only contain letters, spaces, hyphens and apostrophes")
        String city,

        @Pattern(regexp = "^[A-Za-z0-9\\s-]{3,10}$", message = "Invalid postal code format")
        String postalCode,

        @Size(min = 2, max = 2, message = "Country must be a 2-letter ISO code")
        @Pattern(regexp = "^[A-Z]{2}$", message = "Country must be uppercase ISO 3166-1 alpha-2 code")
        String country,

        Boolean isActive,

        @Size(max = 1000, message = "Notes must not exceed 1000 characters")
        String notes

) {
    @AssertTrue(message = "Staff member must be at least 16 years old")
    private boolean isValidAge() {
        if (dateOfBirth == null) {
            return true;
        }

        LocalDate today = LocalDate.now();
        int age = today.getYear() - dateOfBirth.getYear();

        if (dateOfBirth.plusYears(age).isAfter(today)) {
            age--;
        }

        return age >= 16 && age <= 100;
    }
}