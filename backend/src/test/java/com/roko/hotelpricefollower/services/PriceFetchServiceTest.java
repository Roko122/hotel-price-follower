package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.repository.ScrapeProfileRepository;
import com.roko.hotelpricefollower.service.PriceFetchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class PriceFetchServiceTest {

    @Autowired
    private PriceFetchService priceFetchService;

    @Autowired
    private ScrapeProfileRepository scrapeProfileRepository;

    private ScrapeProfile getScrapeProfile() {
        //Initially, there is one ScrapeProfile in the database with the id 1
        return scrapeProfileRepository.findById(1L).get();
    }

    @Test
    public void testThatCreatePriceFetchWorks() {
        PriceFetch priceFetch = priceFetchService.createPriceFetch(this.getScrapeProfile());

        //No entries in database yet, hence expected id is 1
        assertEquals(1L, priceFetch.getId(), "Price fetch id should be 1");
    }

    @Test
    public void testThatUpdatePriceFetchWorks() {
        PriceFetch priceFetch = priceFetchService.createPriceFetch(this.getScrapeProfile());
        priceFetch.setError("test error");
        PriceFetch result = priceFetchService.savePriceFetch(priceFetch);

        assertEquals("test error", result.getError(), "Price fetch error should be 'test error'");
    }

    @Test
    public void testThatSavePriceFetchWorks() {
        Instant now = Instant.now();
        PriceFetch priceFetch = PriceFetch.builder()
                .fetchTime(now)
                .error("test")
                .success(false)
                .scrapeProfile(getScrapeProfile())
                .build();

        PriceFetch result = priceFetchService.savePriceFetch(priceFetch);
        assertEquals("test", result.getError(), "Price fetch error should be 'test'");
        assertEquals(now, result.getFetchTime(), "Price fetch time should be equal");
        assertFalse(result.isSuccess(), "Price fetch success should be false");
    }
}
