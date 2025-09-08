package com.roko.hotelpricefollower.scraper;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class HotelScraper {

    private final WebDriver driver;
    private final HotelParser hotelParser;

    public HotelScraper(WebDriver driver, HotelParser hotelParser) {
        this.driver = driver;
        this.hotelParser = hotelParser;
    }


}
