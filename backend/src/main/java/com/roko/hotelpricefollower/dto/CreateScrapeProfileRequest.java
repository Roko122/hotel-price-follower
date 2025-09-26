package com.roko.hotelpricefollower.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateScrapeProfileRequest(
        @NotNull(message = "Duration cannot be null")
        @Size(min = 1, max = 4, message = "Duration in weeks must be between {min} and {max}")
        Integer durationWeeks,

        @NotNull(message = "Amount of adults cannot be null")
        @Size(min = 1, max = 8, message = "Amount of adults must be between {min} and {max}")
        Integer adults,

        @NotNull(message = "Amount of children cannot be null")
        @Size(min = 0, max = 7, message = "Amount of children must be between {min} and {max}")
        Integer children
) {
}
