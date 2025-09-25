package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.dto.ScrapeProfileDto;
import org.springframework.stereotype.Component;

@Component
public class ScrapeProfileMapper {

    public ScrapeProfileDto toScrapeProfileDto(ScrapeProfile scrapeProfile) {
        return new ScrapeProfileDto(
                scrapeProfile.getId(),
                scrapeProfile.getDurationWeeks(),
                scrapeProfile.getAdults(),
                scrapeProfile.getChildren(),
                scrapeProfile.isActive()
        );
    }
}
