package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.dto.RoomDto;
import com.roko.hotelpricefollower.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels/{hotelId}/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDto> getRooms(@PathVariable Long hotelId) {
        return roomService.findAllRoomsByHotelId(hotelId);
    }
}
