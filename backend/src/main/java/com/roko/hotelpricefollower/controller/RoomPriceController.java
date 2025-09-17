package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.dto.RoomPriceDto;
import com.roko.hotelpricefollower.dto.RoomPriceSummaryDto;
import com.roko.hotelpricefollower.service.RoomPriceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels/{hotelId}/profiles/{profileId}/rooms/{roomId}/prices")
public class RoomPriceController {

    private final RoomPriceService roomPriceService;

    public RoomPriceController(RoomPriceService roomPriceService) {
        this.roomPriceService = roomPriceService;
    }

    @GetMapping("/summary")
    public List<RoomPriceSummaryDto> getRoomPriceSummary(@PathVariable Long hotelId,
                                                         @PathVariable Long profileId,
                                                         @PathVariable Long roomId,
                                                         @RequestParam(name = "departureDates") List<LocalDate> departureDateList) {

        return roomPriceService.getRoomPriceSummary(hotelId, profileId, roomId, departureDateList);
    }

    @GetMapping
    public List<RoomPriceDto> getAllRoomPrices(@PathVariable Long hotelId,
                                               @PathVariable Long profileId,
                                               @PathVariable Long roomId,
                                               @RequestParam LocalDate departureDate) {

        return roomPriceService.getAllRoomPrices(hotelId, profileId, roomId, departureDate);
    }
}
