package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.dto.HotelDto;
import com.roko.hotelpricefollower.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDto> getHotels() {
        return hotelService.getHotels();
    }
}
