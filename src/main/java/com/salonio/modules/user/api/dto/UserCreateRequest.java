package com.salonio.modules.user.api.dto;

import com.salonio.modules.user.domain.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(

        @NotNull
        @NotBlank
        @Size(min = 3, max = 30, message = "Username must be 3–30 characters long.")
        @Pattern(
                regexp = "^[a-zA-Z0-9_.-]+$",
                message = "Username can only contain letters, digits, '.', '-', and '_'"
        )
        String username,

        @NotNull
        @NotBlank
        @Size(min = 8, max = 100, message = "Password must be at least 8 characters long.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "Password must contain uppercase, lowercase, number, and special character."
        )
        String password,

        @NotNull(message = "User type must be provided.")
        UserType userType
) {}
