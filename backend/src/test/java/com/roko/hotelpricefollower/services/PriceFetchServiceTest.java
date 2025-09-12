package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.repository.ScrapeProfileRepository;
import com.roko.hotelpricefollower.service.PriceFetchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PriceFetchServiceTest {

    @Autowired
    private PriceFetchService priceFetchService;

    @Autowired
    private ScrapeProfileRepository scrapeProfileRepository;

    @Test
    public void testThatCreatePriceFetchWorks() {
        //Initially, there is one ScrapeProfile in the database with the id 1
        ScrapeProfile scrapeProfile = scrapeProfileRepository.findById(1L).get();
        PriceFetch priceFetch = priceFetchService.createPriceFetch(scrapeProfile);

        //No entries in database yet, hence expected id is 1
        assertEquals(1L, priceFetch.getId(), "Price fetch id should be 1");
    }
}
