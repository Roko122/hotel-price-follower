package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.domain.DepartureDate;
import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.dto.CreateHotelRequest;
import com.roko.hotelpricefollower.dto.DepartureDateDto;
import com.roko.hotelpricefollower.dto.FetchTimeDto;
import com.roko.hotelpricefollower.dto.HotelDto;
import com.roko.hotelpricefollower.mapper.DepartureDateMapper;
import com.roko.hotelpricefollower.mapper.HotelMapper;
import com.roko.hotelpricefollower.service.DepartureDateService;
import com.roko.hotelpricefollower.service.HotelService;
import com.roko.hotelpricefollower.service.PriceFetchService;
import com.roko.hotelpricefollower.service.ScrapeTaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final ScrapeTaskService scrapeTaskService;
    private final PriceFetchService priceFetchService;
    private final DepartureDateService departureDateService;
    private final DepartureDateMapper departureDateMapper;

    public HotelController(HotelService hotelService, HotelMapper hotelMapper, ScrapeTaskService scrapeTaskService, PriceFetchService priceFetchService, DepartureDateService departureDateService, DepartureDateMapper departureDateMapper) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
        this.scrapeTaskService = scrapeTaskService;
        this.priceFetchService = priceFetchService;
        this.departureDateService = departureDateService;
        this.departureDateMapper = departureDateMapper;
    }

    @GetMapping
    public List<HotelDto> getHotels() {
        return hotelService.getHotels();
    }

    @GetMapping("{hotelId}/last-fetch")
    public FetchTimeDto getLastFetchTime(@PathVariable Long hotelId) {
        Instant fetchTime = priceFetchService.getLastFetchTime(hotelId);

        return new FetchTimeDto(fetchTime);
    }

    @GetMapping("{hotelId}/departure-dates")
    public List<DepartureDateDto> getDepartureDates(@PathVariable Long hotelId) {
        List<DepartureDate> departureDates = departureDateService.getDepartureDatesByHotel(hotelId);

        return departureDates.stream().map(departureDateMapper::toDepartureDateDto).toList();
    }

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest) {
        Hotel hotelToCreate = hotelMapper.toHotel(createHotelRequest);
        Hotel createdHotel = hotelService.createHotel(hotelToCreate);

        return new ResponseEntity<>(hotelMapper.toHotelDto(createdHotel), HttpStatus.CREATED);
    }

}
