package com.roko.hotelpricefollower.scraper.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class RoomPriceData {
    private String roomType;
    private LocalDate departureDate;
    private ParsedRoomPrice parsedRoomPrice;
}
