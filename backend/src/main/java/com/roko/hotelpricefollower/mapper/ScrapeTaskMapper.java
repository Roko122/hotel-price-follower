package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.ScrapeTask;
import com.roko.hotelpricefollower.dto.CreateScrapeTaskRequest;
import com.roko.hotelpricefollower.dto.ScrapeTaskDto;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScrapeTaskMapper {

    public ScrapeTaskDto toScrapeTaskDto(ScrapeTask scrapeTask) {
        return new ScrapeTaskDto(
                scrapeTask.getId(),
                scrapeTask.getScrapeUrl(),
                scrapeTask.getFirstDepartureDate()
        );
    }

    public ScrapeTask toScrapeTask(CreateScrapeTaskRequest createScrapeTaskRequest) {
        String scrapeUrl = createScrapeTaskRequest.scrapeUrl();

        return ScrapeTask.builder()
                .scrapeUrl(scrapeUrl)
                .firstDepartureDate(parseFirstDepartureDate(scrapeUrl))
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
