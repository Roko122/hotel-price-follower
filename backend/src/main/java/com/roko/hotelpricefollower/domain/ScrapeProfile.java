package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "scrape_profiles")
public class ScrapeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scrape_url", length = 1000, nullable = false)
    private String scrapeUrl;

    @Column(name = "duration_weeks", nullable = false)
    private int durationWeeks;

    @Column(name = "adults", nullable = false)
    private int adults;

    @Column(name = "children", nullable = false)
    private int children;

    @JoinColumn(name = "hotel_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Hotel hotel;

    @OneToMany(mappedBy = "scrapeProfile", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PriceFetch> priceFetches;
}
