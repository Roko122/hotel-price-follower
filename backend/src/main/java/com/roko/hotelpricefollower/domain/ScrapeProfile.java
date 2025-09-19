package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "scrape_profiles")
public class ScrapeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scrape_url", length = 1000, nullable = false)
    private String scrapeUrl;

    @Column(name = "first_departure_date", nullable = false)
    private LocalDate firstDepartureDate;

    @Column(name = "duration_weeks", nullable = false)
    private int durationWeeks;

    @Column(name = "adults", nullable = false)
    private int adults;

    @Column(name = "children", nullable = false)
    private int children;

    @JoinColumn(name = "hotel_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    @OneToMany(mappedBy = "scrapeProfile", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PriceFetch> priceFetches;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScrapeProfile that = (ScrapeProfile) o;
        return durationWeeks == that.durationWeeks && adults == that.adults && children == that.children && Objects.equals(id, that.id) && Objects.equals(scrapeUrl, that.scrapeUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scrapeUrl, durationWeeks, adults, children);
    }

    @Override
    public String toString() {
        return "ScrapeProfile{" +
                "id=" + id +
                ", scrapeUrl='" + scrapeUrl + '\'' +
                ", durationWeeks=" + durationWeeks +
                ", adults=" + adults +
                ", children=" + children +
                '}';
    }
}
