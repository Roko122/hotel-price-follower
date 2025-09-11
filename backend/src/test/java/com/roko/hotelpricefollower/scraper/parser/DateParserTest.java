package com.roko.hotelpricefollower.scraper.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DateParserTest {

    @Autowired
    private DateParser dateParser;

    private File exampleHTML;
    private Document priceMatrixDoc;

    public DateParserTest() throws IOException {
        this.exampleHTML = new File("src/test/resources/html/price_matrix_four_dates.html");
        this.priceMatrixDoc = Jsoup.parse(exampleHTML, "UTF-8");
    }

    @Test
    public void testThatDateParserWorks() {
        List<LocalDate> result = dateParser.parseDates(priceMatrixDoc);
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(result.contains(LocalDate.of(2026, 1, 4)),
                "Result should contain a specific date");

    }
}
