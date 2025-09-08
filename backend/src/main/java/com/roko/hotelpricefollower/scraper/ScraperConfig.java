package com.roko.hotelpricefollower.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScraperConfig {

    @Bean
    public WebDriver setupChromeDriver() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless-new");
        return new ChromeDriver();
    }
}
