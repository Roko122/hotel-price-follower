package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.parser.dto.ParsedRoomPrice;
import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HotelParser {

    private final DateParser dateParser;
    private final RoomPriceParser roomPriceParser;

    public HotelParser(DateParser dateParser, RoomPriceParser roomPriceParser) {
        this.dateParser = dateParser;
        this.roomPriceParser = roomPriceParser;
    }

    public List<RoomPriceData> parseRoomPrices(String priceMatrix) {
        Document priceMatrixDoc = Jsoup.parse(priceMatrix);

        List<LocalDate> parsedDates = dateParser.parseDates(priceMatrixDoc);
        Map<String, List<ParsedRoomPrice>> parsedRoomsAndPrices = roomPriceParser.parseRoomsAndPrices(priceMatrixDoc);

        return combineRoomPricesAndDates(parsedDates, parsedRoomsAndPrices);
    }

    private List<RoomPriceData> combineRoomPricesAndDates(List<LocalDate> parsedDates,
                                                          Map<String, List<ParsedRoomPrice>> parsedRoomsAndPrices) {
        List<RoomPriceData> roomPricesData = new ArrayList<>();

        parsedRoomsAndPrices.forEach((roomName, parsedRoomPrices) -> {
            //parsedRoomPrices and parsedDates are always of the same length
            for (int i = 0; i < parsedDates.size(); i++) {
                ParsedRoomPrice parsedRoomPrice = parsedRoomPrices.get(i);
                LocalDate parsedDate = parsedDates.get(i);
                RoomPriceData roomPriceData = new RoomPriceData(roomName, parsedDate, parsedRoomPrice);

                roomPricesData.add(roomPriceData);
            }
        });

        return roomPricesData;
    }
}
