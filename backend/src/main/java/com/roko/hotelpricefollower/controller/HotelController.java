package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.dto.CreateHotelRequest;
import com.roko.hotelpricefollower.dto.FetchTimeDto;
import com.roko.hotelpricefollower.dto.HotelDto;
import com.roko.hotelpricefollower.mapper.HotelMapper;
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

    public HotelController(HotelService hotelService, HotelMapper hotelMapper, ScrapeTaskService scrapeTaskService, PriceFetchService priceFetchService) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
        this.scrapeTaskService = scrapeTaskService;
        this.priceFetchService = priceFetchService;
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

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest) {
        Hotel hotelToCreate = hotelMapper.toHotel(createHotelRequest);
        Hotel createdHotel = hotelService.createHotel(hotelToCreate);

        return new ResponseEntity<>(hotelMapper.toHotelDto(createdHotel), HttpStatus.CREATED);
    }

    @GetMapping("/scrape")
    public String scrapeHotels() {
        scrapeTaskService.startScrapes();
        return "scraping done :)";
    }
}
