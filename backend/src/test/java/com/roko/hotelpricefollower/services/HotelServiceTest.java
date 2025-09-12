package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Test
    void testThatGetHotelByIdReturnsAHotel() {
        //Database contains one test hotel with id 1
        Optional<Hotel> hotel = hotelService.getHotel(1L);

        assertTrue(hotel.isPresent(), "Hotel could not be found");
        assertEquals(1L, hotel.get().getId(), "Hotel could not be found");
    }

    @Test
    void testThatGetHotelByIdReturnsAnEmptyOptionalWhenHotelDoesNotExist() {
        Optional<Hotel> hotel = hotelService.getHotel(999L);
        assertTrue(hotel.isEmpty(), "A hotel was found");
    }
}
