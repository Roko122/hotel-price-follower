package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.dto.CreateScrapeProfileRequest;
import com.roko.hotelpricefollower.dto.ScrapeProfileDto;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    private LocalDate parseFirstDepartureDate(String url) {
        URI uri = URI.create(url);
        List<String> query = List.of(uri.getQuery().split("&"));
        String date = query.stream()
                .map(s -> s.split("=", 2))
                .collect(
                        Collectors.toMap(a -> a[0], a -> a[1])
                ).get("QueryDepDate");

        return LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
    }
}
