package com.example.finance.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReportDTO(
        Long id,

        @NotNull(message = "periodStart is required")
        LocalDate periodStart,

        @NotNull(message = "periodEnd is required")
        LocalDate periodEnd
) {}
