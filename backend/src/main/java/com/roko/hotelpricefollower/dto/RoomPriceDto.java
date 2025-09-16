package com.roko.hotelpricefollower.dto;

import com.roko.hotelpricefollower.util.DateTimeUtil;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record RoomPriceDto(BigDecimal price, LocalDate fetchTime) {
    public RoomPriceDto(Long priceInCents, Instant fetchTime) {
        this(new BigDecimal(priceInCents).movePointLeft(2), DateTimeUtil.toLocalDate(fetchTime));
    }
}
