package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.parser.dto.ParsedRoomPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RoomPriceParserTest {
    @Autowired
    private RoomPriceParser roomPriceParser;

    private File exampleHTML;
    private Document priceMatrixDoc;

    public RoomPriceParserTest() throws IOException {
        this.exampleHTML = new File("src/test/resources/html/price_matrix_four_dates.html");
        this.priceMatrixDoc = Jsoup.parse(exampleHTML, "UTF-8");
    }

    @Test
    public void testThatRoomPriceParserWorks() {
        Map<String, List<ParsedRoomPrice>> result = roomPriceParser.parseRoomsAndPrices(priceMatrixDoc);
        assertFalse(result.isEmpty(), "Result should not be empty");
        System.out.println(result);

        assertTrue(result.containsKey("CLASSIC STUDIO - 1 huone, parveke allasalueelle päin"),
                "Result should contain specific room");
    }

}
