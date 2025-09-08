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

    public HotelScraper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPriceMatrix(String hotelUrl) {
        driver.get(hotelUrl);

        try {
            return getWait().until(driver ->
                    driver.findElement(By.className("tcne-pm-main-matrix__block")));
        } catch (TimeoutException e) {
            throw new PriceMatrixNotFoundException("Could not find price matrix, please try again later.\n" + hotelUrl);
        } finally {
            driver.quit();
        }
    }

    private Wait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(5L))
                .ignoring(NoSuchElementException.class);
    }
}
