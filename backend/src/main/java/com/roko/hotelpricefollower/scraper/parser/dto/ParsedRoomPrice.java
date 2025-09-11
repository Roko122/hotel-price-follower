package com.roko.hotelpricefollower.scraper.parser.dto;

import lombok.Data;

@Data
public class ParsedRoomPrice {
    private Long price;
    private Boolean soldOut;
    private String additionalInformation;

    public ParsedRoomPrice() {
        this.soldOut = false;
    }
}
