package com.example.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Used only for incoming "create company / register" requests.
 */
public record CompanyCreateDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String rawPassword
) {}