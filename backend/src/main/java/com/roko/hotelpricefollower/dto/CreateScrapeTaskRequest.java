package com.roko.hotelpricefollower.dto;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public record CreateScrapeTaskRequest(
        @URL(message = "Invalid URL format")
        @Pattern(
                regexp = "^https://tjareborg\\.fi[a-zA-Z0-9\\-._~:/?#\\[\\]@!$&'()*+,;%=]+#hotel-search-app-root$",
                message = "URL must start with https://tjareborg.fi, contain only valid URL characters " +
                        "and end in #hotel-search-app-root"
        )
        String scrapeUrl
) {
}
