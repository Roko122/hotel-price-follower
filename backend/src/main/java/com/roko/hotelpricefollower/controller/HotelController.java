package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.dto.CreateHotelRequest;
import com.roko.hotelpricefollower.dto.HotelDto;
import com.roko.hotelpricefollower.mapper.HotelMapper;
import com.roko.hotelpricefollower.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    public HotelController(HotelService hotelService, HotelMapper hotelMapper) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    @GetMapping
    public List<HotelDto> getHotels() {
        return hotelService.getHotels();
    }

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody CreateHotelRequest createHotelRequest) {
        Hotel hotelToCreate = hotelMapper.toHotel(createHotelRequest);
        Hotel createdHotel = hotelService.createHotel(hotelToCreate);

        return ResponseEntity.ok(hotelMapper.toHotelDto(createdHotel));
    }
}
