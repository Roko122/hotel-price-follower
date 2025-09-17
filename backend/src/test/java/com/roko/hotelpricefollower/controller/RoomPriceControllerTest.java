package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.service.ScrapeProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScrapeProfileService scrapeProfileService;

    private final String[] dates = {"2026-03-22", "2026-03-29", "2026-04-05", "2026-04-12"};

    @BeforeEach
    public void setup() {
        scrapeProfileService.startScrapes();
    }

    @Test
    public void testThatGetRoomPriceSummaryReturnsCorrectJsonWith4Dates() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/hotels/1/profiles/1/rooms/1/prices/summary")
                        .param("departureDates", dates)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].departureDate").value(dates[0])
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$", hasSize(dates.length))
        );
    }

    @Test
    public void testThatGetRoomPriceSummaryReturnsCorrectJsonWith1Date() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/hotels/1/profiles/1/rooms/1/prices/summary")
                        .param("departureDates", dates[2])
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].departureDate").value(dates[2])
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$", hasSize(1))
        );
    }

    @Test
    public void testThatGetAllRoomPricesReturnsCorrectJson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/hotels/1/profiles/1/rooms/1/prices")
                        .param("departureDate", dates[0])
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].price").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].fetchTime").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$", hasSize(1))
        );
    }
}
