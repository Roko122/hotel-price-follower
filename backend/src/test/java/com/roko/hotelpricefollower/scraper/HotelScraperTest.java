package com.roko.hotelpricefollower.scraper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HotelScraperTest {

    @Autowired
    private HotelScraper hotelScraper;

    private final String testUrl = "https://www.tjareborg.fi/kanariansaaret/teneriffa/playa-de-las-americas/sunprime-ocean-view?UseBookingFlow=true&QueryDepID=12728&QueryResID=12190&QueryDepDate=20251207&QueryUnits=0&QueryAges=42%2C42&QueryDur=8&ItemId=89669&qf=flowCharter&selectedTransport=flight%7CCgJURhIkMzAyYjUzMDgtOTcxZS00OWE2LWFkMTYtODhiMzBjYjNmY2Ex&SelectedFlightClass=&RoomKey=hotel%7CCgJURhIDVEZTGgRTVU9WIgZBMTJCQUw&SelectedMeals=#hotel-search-app-root";

//    @Test
//    public void getHotelPriceMatrixReturnsAnElement() {
//        WebElement priceMatrix = hotelScraper.getPriceMatrix(testUrl);
//        Assertions.assertNotNull(priceMatrix, "Price matrix was not present -> priceMatrix is null");
//    }
}
