package com.roko.hotelpricefollower.scraper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HotelScraperTest {

    @Autowired
    private HotelScraper hotelScraper;

    private final String testUrl = "https://www.tjareborg.fi/kanariansaaret/teneriffa/playa-de-las-americas/sunprime-ocean-view?UseBookingFlow=true&QueryDepID=12728&QueryResID=12190&QueryDepDate=20261206&QueryUnits=0&QueryAges=42%2C42&QueryDur=8&ItemId=89669&qf=flowCharter&RoomKey=SUOV_A12BAL_N&selectedTransport=flight%7CCgJURhIkYTljZjU2ZjUtN2QzYS00ZmZkLTk5MTktNDJiMTdkYTJmOWE1&SelectedMeals=noselection&SelectedFlightClass=E#hotel-search-app-root";

    @Test
    public void getHotelPriceMatrixReturnsCorrectHTMLAsAString() {
        String priceMatrix = hotelScraper.scrapePriceMatrix(testUrl);
        assertNotNull(priceMatrix, "priceMatrix is null");
        assertTrue(priceMatrix.contains("Summary__priceContainer__"));
    }
}
