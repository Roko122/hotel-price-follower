package com.roko.hotelpricefollower.scraper;

import com.roko.hotelpricefollower.exception.PriceMatrixNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class HotelScraper {

    private final WebDriver driver;
    private final HotelParser hotelParser;

    public HotelScraper(WebDriver driver, HotelParser hotelParser) {
        this.driver = driver;
        this.hotelParser = hotelParser;
    }

    public void getHotelPrices(String hotelUrl) {
        WebElement priceMatrix;

        try {
            priceMatrix = this.getPriceMatrix(hotelUrl);
        } catch (TimeoutException e) {
            throw new PriceMatrixNotFoundException("Could not find price matrix, please try again later.\n" + hotelUrl);
        }
    }

    private WebElement getPriceMatrix(String hotelUrl) {
        driver.get(hotelUrl);

        return getWait().until(driver ->
                driver.findElement(By.className("tcne-pm-main-matrix__block")));
    }

    private Wait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(15L))
                .ignoring(NoSuchElementException.class);
    }


}
