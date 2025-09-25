package com.roko.hotelpricefollower.dto;

public record ScrapeProfileDto(
        Long id,
        int durationWeeks,
        int adults,
        int children,
        boolean isActive
) {
}
