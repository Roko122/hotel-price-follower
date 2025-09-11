package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HotelParserTest {

    @Autowired
    private HotelParser hotelParser;

    private File exampleHTML;
    private String priceMatrixString;

    public HotelParserTest() throws IOException {
        this.exampleHTML = new File("src/test/resources/html/price_matrix.html");
        this.priceMatrixString = Jsoup.parse(exampleHTML, "UTF-8").toString();
    }

    @Test
    public void testThatHotelParserReturnsAListOfRoomPriceData() {
        List<RoomPriceData> roomPriceDataList = hotelParser.parseRoomPrices(priceMatrixString);
        assertFalse(roomPriceDataList.isEmpty(), "RoomPriceDataList is empty");
        assertEquals(76, roomPriceDataList.size(),
                "RoomPriceDataList doesn't contain all room price data");
    }
}
