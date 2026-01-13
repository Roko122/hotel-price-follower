package com.roko.hotelpricefollower.dto;

import java.time.LocalDate;

public record DepartureDateDto(
        Long id,
        LocalDate departureDate
) {
}
