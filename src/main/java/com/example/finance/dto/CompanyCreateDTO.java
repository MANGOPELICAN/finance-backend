package com.example.finance.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Used only for incoming "create company / register" requests.
 */
public record CompanyCreateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Password is required")
        String rawPassword
) {}
