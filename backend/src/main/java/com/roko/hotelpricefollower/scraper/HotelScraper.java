package com.roko.hotelpricefollower.scraper;

import com.roko.hotelpricefollower.domain.RoomPrice;
import com.roko.hotelpricefollower.exception.PriceMatrixNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class HotelScraper {

    private final WebDriver driver;
    private final HotelParser hotelParser;

    public HotelScraper(WebDriver driver, HotelParser hotelParser) {
        this.driver = driver;
        this.hotelParser = hotelParser;
    }

    public List<RoomPrice> getRoomPrices(String hotelUrl) throws PriceMatrixNotFoundException {
        WebElement priceMatrix = getPriceMatrix(hotelUrl);
        //TODO: get list of RoomPrice objects from HotelParser

        return null;
    }

    private WebElement getPriceMatrix(String hotelUrl) {
        driver.get(hotelUrl);

        try {
            //Wait until priceMatrix is loaded
            WebElement priceMatrix = getWait().until(driver ->
                    driver.findElement(By.className("tcne-pm-matrix")));

            closeCookiesPopup();
            openPriceSummary(priceMatrix);

            return priceMatrix;
        } catch (TimeoutException e) {
            driver.quit();
            throw new PriceMatrixNotFoundException("Could not find price matrix, please try again later.\n" + hotelUrl);
        }
    }

    private void closeCookiesPopup() {
        //Disallow cookies to hide overlay blocking other clicks
        driver.findElement(By.xpath("//button[span[text()='Hylkää kaikki']]")).click();
    }

    private void openPriceSummary(WebElement priceMatrix) {
        priceMatrix.findElement(By.className("tcne-pm-price-details__link")).click();
    }

    private Wait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(5L))
                .ignoring(NoSuchElementException.class);
    }
}
