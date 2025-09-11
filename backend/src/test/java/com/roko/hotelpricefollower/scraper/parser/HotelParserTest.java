package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class HotelParserTest {

    @Autowired
    private HotelParser hotelParser;

    private final File exampleHTML4Dates;
    private final String priceMatrixString4Dates;
    private final File exampleHTML1Date;
    private final String priceMatrixString1Date;

    public HotelParserTest() throws IOException {
        this.exampleHTML4Dates = new File("src/test/resources/html/price_matrix_four_dates.html");
        this.priceMatrixString4Dates = Jsoup.parse(exampleHTML4Dates, "UTF-8").toString();
        this.exampleHTML1Date = new File("src/test/resources/html/price_matrix_one_date.html");
        this.priceMatrixString1Date = Jsoup.parse(exampleHTML1Date, "UTF-8").toString();
    }

    @Test
    public void testThatHotelParserReturnsAListOfRoomPriceDataWith4Dates() {
        List<RoomPriceData> roomPriceDataList = hotelParser.parseRoomPrices(priceMatrixString4Dates);
        assertFalse(roomPriceDataList.isEmpty(), "RoomPriceDataList is empty");
        assertEquals(76, roomPriceDataList.size(),
                "RoomPriceDataList doesn't contain all room price data");
    }

    @Test
    public void testThatHotelParserReturnsAListOfRoomPriceDataWith1Date() {
        List<RoomPriceData> roomPriceDataList = hotelParser.parseRoomPrices(priceMatrixString1Date);
        System.out.println(roomPriceDataList);
        assertFalse(roomPriceDataList.isEmpty(), "RoomPriceDataList is empty");
        assertEquals(18, roomPriceDataList.size(),
                "RoomPriceDataList doesn't contain all room price data");
    }
}
