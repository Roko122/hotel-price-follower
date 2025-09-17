package com.roko.hotelpricefollower.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScrapeProfileSchedulerService {

    private final ScrapeProfileService scrapeProfileService;

    public ScrapeProfileSchedulerService(ScrapeProfileService scrapeProfileService) {
        this.scrapeProfileService = scrapeProfileService;
    }

    @Scheduled(cron = "${scrape.schedule.cron}", zone = "${scrape.schedule.zone}")
    public void scrapeOnceADay() {
        scrapeProfileService.startScrapes();
    }
}
