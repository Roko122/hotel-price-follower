package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.repository.ScrapeProfileRepository;
import com.roko.hotelpricefollower.service.PriceFetchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    private PriceFetch savePriceFetch(Instant time) {
        PriceFetch priceFetch = PriceFetch.builder()
                .fetchTime(time)
                .error("test")
                .success(false)
                .scrapeProfile(getScrapeProfile())
                .build();

        return priceFetchService.savePriceFetch(priceFetch);
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
        PriceFetch result = this.savePriceFetch(now);

        assertEquals(1L, result.getId(), "Price fetch should have id");
        assertEquals("test", result.getError(), "Price fetch error should be 'test'");
        assertEquals(now, result.getFetchTime(), "Price fetch time should be equal");
        assertFalse(result.isSuccess(), "Price fetch success should be false");
    }

    @Test
    public void testThatGetMostRecentPriceFetchWorks() {
        Instant now = Instant.now();
        Instant time2 = now.plusSeconds(30);
        Instant time3 = now.minusSeconds(120);
        Instant time4 = now.minusSeconds(45);

        this.savePriceFetch(now);
        this.savePriceFetch(time2);
        this.savePriceFetch(time3);
        this.savePriceFetch(time4);

        Optional<PriceFetch> result = priceFetchService.getMostRecentPriceFetch(getScrapeProfile());

        assertTrue(result.isPresent(), "Most recent PriceFetch should be present");

        assertEquals(time2.getEpochSecond(), result.get().getFetchTime().getEpochSecond(),
                "Price fetch time should be equal");
    }
}
