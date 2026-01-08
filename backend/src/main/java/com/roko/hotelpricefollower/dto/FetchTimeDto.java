package com.roko.hotelpricefollower.dto;

import java.time.Instant;

public record FetchTimeDto(
        Instant lastFetchTime
) {
}
