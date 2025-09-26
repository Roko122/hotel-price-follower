package com.roko.hotelpricefollower.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateScrapeProfileRequest(
        @NotNull(message = "Duration cannot be null")
        @Min(value = 1, message = "Duration in weeks must be at least {value}")
        @Max(value = 4, message = "Duration in weeks must be at most {value}")
        Integer durationWeeks,

        @NotNull(message = "Amount of adults cannot be null")
        @Min(value = 1, message = "Amount of adults must be at least {value}")
        @Max(value = 8, message = "Amount of adults must be at most {value}")
        Integer adults,

        @NotNull(message = "Amount of children cannot be null")
        @Min(value = 0, message = "Amount of children must be at least {value}")
        @Max(value = 7, message = "Amount of children must be at most {value}")
        Integer children
) {
}
