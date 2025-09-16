package com.roko.hotelpricefollower.dto;

import java.time.LocalDate;

public record RoomPriceSummaryDto(
        LocalDate departureDate,
        PriceDetailsDto latestPrice,
        PriceDetailsDto min30days,
        PriceDetailsDto allTimeMin
) {
}
