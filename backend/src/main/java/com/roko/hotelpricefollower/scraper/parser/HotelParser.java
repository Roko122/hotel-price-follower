package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.domain.RoomPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class HotelParser {

    private DateParser dateParser;
    private RoomParser roomParser;
    private PriceParser priceParser;

    public HotelParser(DateParser dateParser, RoomParser roomParser, PriceParser priceParser) {
        this.dateParser = dateParser;
        this.roomParser = roomParser;
        this.priceParser = priceParser;
    }

    public List<RoomPrice> roomPrices(String priceMatrix) {
        Document priceMatrixDoc = Jsoup.parse(priceMatrix);
        List<LocalDate> parsedDates = dateParser.parseDates(priceMatrixDoc);


        return null;
    }
}
