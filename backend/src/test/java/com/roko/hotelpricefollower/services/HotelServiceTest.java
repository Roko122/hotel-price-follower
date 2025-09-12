package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Test
    void testThatGetHotelByIdReturnsAHotel() {
        Optional<Hotel> hotel = hotelService.getHotel(1L);
        System.out.println(hotel.get());
    }
}
