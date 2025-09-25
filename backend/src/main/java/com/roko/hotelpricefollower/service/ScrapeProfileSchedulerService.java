package com.roko.hotelpricefollower.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScrapeProfileSchedulerService {

    private final ScrapeTaskService scrapeTaskService;

    public ScrapeProfileSchedulerService(ScrapeTaskService scrapeTaskService) {
        this.scrapeTaskService = scrapeTaskService;
    }

    @Scheduled(cron = "${scrape.schedule.cron}", zone = "${scrape.schedule.zone}")
    public void scrapeOnceADay() {
        scrapeTaskService.startScrapes();
    }
}
