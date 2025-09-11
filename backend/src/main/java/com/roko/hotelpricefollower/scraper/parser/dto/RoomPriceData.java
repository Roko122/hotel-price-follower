package com.roko.hotelpricefollower.scraper.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoomPriceData {
    private String roomType;
    private LocalDate departureDate;
    private ParsedRoomPrice parsedRoomPrice;
}
