package com.roko.hotelpricefollower.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PriceDetailsDto(
        BigDecimal price,
        boolean soldOut,
        String additionalInfo,
        LocalDate fetchDate
) {
}
