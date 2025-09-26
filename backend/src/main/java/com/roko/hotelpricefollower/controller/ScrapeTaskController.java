package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.domain.ScrapeTask;
import com.roko.hotelpricefollower.dto.CreateScrapeTaskRequest;
import com.roko.hotelpricefollower.dto.ScrapeTaskDto;
import com.roko.hotelpricefollower.mapper.ScrapeTaskMapper;
import com.roko.hotelpricefollower.service.ScrapeTaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels/{hotelId}/profiles/{profileId}/tasks")
public class ScrapeTaskController {

    private final ScrapeTaskMapper scrapeTaskMapper;
    private final ScrapeTaskService scrapeTaskService;

    public ScrapeTaskController(ScrapeTaskMapper scrapeTaskMapper, ScrapeTaskService scrapeTaskService) {
        this.scrapeTaskMapper = scrapeTaskMapper;
        this.scrapeTaskService = scrapeTaskService;
    }

    @PostMapping
    public ResponseEntity<ScrapeTaskDto> createTask(
            @PathVariable Long hotelId,
            @PathVariable Long profileId,
            @Valid @RequestBody CreateScrapeTaskRequest createScrapeTaskRequest
    ) {
        ScrapeTask scrapeTaskToCreate = scrapeTaskMapper.toScrapeTask(createScrapeTaskRequest);
        ScrapeTask createdScrapeTask = scrapeTaskService.createScrapeTask(hotelId, profileId, scrapeTaskToCreate);
        ScrapeTaskDto createScrapeTaskDto = scrapeTaskMapper.toScrapeTaskDto(createdScrapeTask);

        return new ResponseEntity<>(createScrapeTaskDto, HttpStatus.CREATED);
    }
}
