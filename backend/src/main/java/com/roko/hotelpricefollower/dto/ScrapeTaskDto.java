package com.roko.hotelpricefollower.dto;

import java.time.LocalDate;

public record ScrapeTaskDto(
        Long id,
        String scrapeUrl,
        LocalDate firstDepartureDate
) {
}
