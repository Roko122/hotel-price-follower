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

    public String scrapePriceMatrix(String hotelUrl) throws PriceMatrixNotFoundException {
        return this.getPriceMatrix(hotelUrl);
    }

    private String getPriceMatrix(String hotelUrl) {
        driver.get(hotelUrl);

        try {
            //Wait until priceMatrix is loaded
            WebElement priceMatrix = getWait().until(driver ->
                    driver.findElement(By.className("tcne-pm-matrix")));

            closeCookiesPopup();
            openPriceSummary(priceMatrix);

            //return HTML as a String
            return priceMatrix.getDomProperty("outerHTML");
        } catch (TimeoutException e) {
            throw new PriceMatrixNotFoundException("Could not find price matrix, please try again later.\n" + hotelUrl);
        } finally {
            driver.quit();
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
