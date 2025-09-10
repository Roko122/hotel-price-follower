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

    private final String testUrl = "https://www.tjareborg.fi/kanariansaaret/teneriffa/playa-de-las-americas/sunprime-ocean-view?UseBookingFlow=true&QueryDepID=12728&QueryResID=12190&QueryDepDate=20251207&QueryUnits=0&QueryAges=42%2C42&QueryDur=8&ItemId=89669&qf=flowCharter&selectedTransport=flight%7CCgJURhIkMzAyYjUzMDgtOTcxZS00OWE2LWFkMTYtODhiMzBjYjNmY2Ex&SelectedFlightClass=&RoomKey=hotel%7CCgJURhIDVEZTGgRTVU9WIgZBMTJCQUw&SelectedMeals=#hotel-search-app-root";

    @Test
    public void getHotelPriceMatrixReturnsCorrectHTMLAsAString() {
        String priceMatrix = hotelScraper.scrapePriceMatrix(testUrl);
        assertNotNull(priceMatrix, "priceMatrix is null");
        assertTrue(priceMatrix.contains("tcne-pm-matrix"));
    }
}
