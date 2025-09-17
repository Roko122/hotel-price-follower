package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.RoomPrice;
import com.roko.hotelpricefollower.repository.RoomPriceRepository;
import com.roko.hotelpricefollower.service.ScrapeProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ScrapeProfileServiceTest {

    @Autowired
    private ScrapeProfileService scrapeProfileService;

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Test
    public void testThatScrapeProfilesScrapesAndSavesAllRoomPrices() {
        scrapeProfileService.startScrapes();
        List<RoomPrice> roomPrices = roomPriceRepository.findAll();

        assertFalse(roomPrices.isEmpty(), "RoomPrice list should not be empty");
        assertEquals(76, roomPrices.size(), "RoomPrice list does not contain all room price data");
    }
}
