package com.roko.hotelpricefollower.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatGetHotelsReturns200OkAndListOfHotels() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Sunprime Ocean View")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].imageUrl").hasJsonPath()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$", hasSize(1))
        );
    }
}
