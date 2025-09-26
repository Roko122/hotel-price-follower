package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.dto.CreateScrapeProfileRequest;
import com.roko.hotelpricefollower.dto.ScrapeProfileDto;
import org.springframework.stereotype.Component;

@Component
public class ScrapeProfileMapper {

    public ScrapeProfileDto toScrapeProfileDto(ScrapeProfile scrapeProfile) {
        return new ScrapeProfileDto(
                scrapeProfile.getId(),
                scrapeProfile.getDurationWeeks(),
                scrapeProfile.getAdults(),
                scrapeProfile.getChildren()
        );
    }

    public ScrapeProfile toScrapeProfile(CreateScrapeProfileRequest createScrapeProfile) {
        return ScrapeProfile.builder()
                .durationWeeks(createScrapeProfile.durationWeeks())
                .adults(createScrapeProfile.adults())
                .children(createScrapeProfile.children())
                .build();
    }
}
