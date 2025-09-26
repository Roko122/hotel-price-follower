package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.dto.CreateScrapeProfileRequest;
import com.roko.hotelpricefollower.dto.ScrapeProfileDto;
import com.roko.hotelpricefollower.mapper.ScrapeProfileMapper;
import com.roko.hotelpricefollower.service.ScrapeProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels/{hotelId}/profiles")
public class ScrapeProfileController {
    private final ScrapeProfileService scrapeProfileService;
    private final ScrapeProfileMapper scrapeProfileMapper;

    public ScrapeProfileController(ScrapeProfileService scrapeProfileService, ScrapeProfileMapper scrapeProfileMapper) {
        this.scrapeProfileService = scrapeProfileService;
        this.scrapeProfileMapper = scrapeProfileMapper;
    }

    @GetMapping
    public ResponseEntity<List<ScrapeProfileDto>> getProfiles(@PathVariable Long hotelId) {
        List<ScrapeProfile> scrapeProfiles = scrapeProfileService.getAllScrapeProfilesByHotel(hotelId);
        List<ScrapeProfileDto> scrapeProfileDtos = scrapeProfiles.stream()
                .map(scrapeProfileMapper::toScrapeProfileDto)
                .toList();

        return ResponseEntity.ok(scrapeProfileDtos);
    }

    @PostMapping
    public ResponseEntity<ScrapeProfileDto> createProfile(
            @PathVariable Long hotelId,
            @Valid @RequestBody CreateScrapeProfileRequest createScrapeProfileRequest) {

        ScrapeProfile toCreate = scrapeProfileMapper.toScrapeProfile(createScrapeProfileRequest);
        ScrapeProfile createdScrapeProfile = scrapeProfileService.createScrapeProfile(hotelId, toCreate);

        return new ResponseEntity<>(scrapeProfileMapper.toScrapeProfileDto(createdScrapeProfile), HttpStatus.CREATED);
    }
}
