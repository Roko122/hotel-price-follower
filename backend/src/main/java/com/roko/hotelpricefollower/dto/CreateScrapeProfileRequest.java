package com.roko.hotelpricefollower.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateScrapeProfileRequest(
        @URL(message = "Invalid URL format")
        @Pattern(
                regexp = "^https://tjareborg\\.fi[a-zA-Z0-9\\-._~:/?#\\[\\]@!$&'()*+,;%=]+#hotel-search-app-root$",
                message = "URL must start with https://tjareborg.fi, contain only valid URL characters " +
                        "and end in #hotel-search-app-root"
        )
        String scrapeUrl,

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
