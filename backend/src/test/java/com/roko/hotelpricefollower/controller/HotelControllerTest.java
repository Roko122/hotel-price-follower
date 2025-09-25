package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@Import(TestUtil.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestUtil testUtil;

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

    @Test
    public void testThatCreateHotelReturns201CreatedAndCreatedHotel() throws Exception{
        String hotelName = "Test hotel";
        String imageUrl = "https://img.tjareborg.fi/image/upload/testUrl.png";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testUtil.getJwtToken())
                        .content(testUtil.generateCreateHotelRequestJson(hotelName, imageUrl))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(hotelName)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.imageUrl").value(imageUrl)
        );
    }

    @Test
    public void testThatCreateHotelReturns401UnauthorizedWithoutJwtToken() throws Exception{
        String hotelName = "Test hotel";
        String imageUrl = "https://img.tjareborg.fi/image/upload/testUrl.png";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUtil.generateCreateHotelRequestJson(hotelName, imageUrl))
        ).andExpect(
                MockMvcResultMatchers.status().isUnauthorized()
        );
    }

    @Test
    public void testThatCreateHotelReturns400BadRequestWithInvalidName() throws Exception{
        String hotelName = "test";
        String imageUrl = "https://img.tjareborg.fi/image/upload/testUrl.png";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testUtil.getJwtToken())
                        .content(testUtil.generateCreateHotelRequestJson(hotelName, imageUrl))
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message", containsString("Hotel name should be"))
        );
    }

    @Test
    public void testThatCreateHotelReturns400BadRequestWithInvalidImageUrl() throws Exception{
        String hotelName = "Test hotel";
        String imageUrl = "https://img.google.fi/image/upload/testUrl.png";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testUtil.getJwtToken())
                        .content(testUtil.generateCreateHotelRequestJson(hotelName, imageUrl))
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message", containsString("URL must start with"))
        );
    }

    @Test
    public void testThatCreateHotelReturns409ConflictWhenTryingToAddAHotelWithAnExistingName() throws Exception{
        String hotelName = "Test hotel";
        String imageUrl = "https://img.tjareborg.fi/image/upload/testUrl.png";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testUtil.getJwtToken())
                        .content(testUtil.generateCreateHotelRequestJson(hotelName, imageUrl))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + testUtil.getJwtToken())
                        .content(testUtil.generateCreateHotelRequestJson(hotelName, imageUrl))
        ).andExpect(
                MockMvcResultMatchers.status().isConflict()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message", containsString("Hotel with name"))
        );
    }
}
