package com.example.finance.dto;

/**
 * Minimal info we expose to the client.
 */
public record CompanyDTO(
        Long id,
        String name
) {}
