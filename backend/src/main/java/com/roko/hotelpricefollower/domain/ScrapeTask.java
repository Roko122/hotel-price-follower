package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "scrape_tasks")
public class ScrapeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scrape_url", length = 1000, nullable = false)
    private String scrapeUrl;

    @Column(name = "first_departure_date", nullable = false)
    private LocalDate firstDepartureDate;

    @JoinColumn(name = "scrape_profile_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ScrapeProfile scrapeProfile;
}
