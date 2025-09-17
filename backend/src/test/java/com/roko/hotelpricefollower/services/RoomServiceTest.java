package com.roko.hotelpricefollower.services;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.repository.HotelRepository;
import com.roko.hotelpricefollower.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel getHotel() {
        //Initially, database contains one hotel with id 1
        return hotelRepository.findById(1L).get();
    }

    @Test
    public void testThatGetOrCreateRoomCreatesARoomWhenItDoesNotExist() {
        String roomType = "Test Deluxe Room";
        Room room = roomService.getOrCreateRoom(getHotel(), roomType);

        //First room should get the id 1
        assertEquals(1L, room.getId(), "Room should have id of 1");
        assertEquals(roomType, room.getType(), "Room type should be 'Test Deluxe Room'");
    }

    @Test
    public void testThatGetOrCreateRoomCreatesARoomWhenItExists() {
        String roomType = "Test Deluxe Room";
        Room room = roomService.getOrCreateRoom(getHotel(), roomType);
        Room result = roomService.getOrCreateRoom(getHotel(), roomType);

        //Test that the same room does not get created twice
        assertEquals(room.getId(), result.getId(), "Room should have id of 1");
    }
}
