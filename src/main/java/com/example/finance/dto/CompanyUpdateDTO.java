package com.example.finance.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Used for full-update (PUT) of an existing company.
 * - name is required
 * - rawPassword is optional; if null/blank we keep the current hash
 */
public record CompanyUpdateDTO(
        @NotBlank(message = "Name is required")
        String name,

        String rawPassword      // optional
) {}
