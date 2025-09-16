package com.roko.hotelpricefollower.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RoomPriceDto(
        BigDecimal price,
        LocalDate fetchTime
){
}
