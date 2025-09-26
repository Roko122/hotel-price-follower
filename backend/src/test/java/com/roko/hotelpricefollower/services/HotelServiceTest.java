package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Test
    void testThatGetHotelByIdReturnsAHotel() {
        //Database contains one test hotel with id 1
        Hotel hotel = hotelService.getHotel(1L);

        assertEquals(1L, hotel.getId(), "Hotel could not be found");
    }

    @Test
    void testThatGetHotelByIdThrowsExceptionWhenHotelDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> hotelService.getHotel(999L));
    }
}
