package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(
        name = "scrape_tasks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_dept_date_scrape_profile",
                        columnNames = {"first_departure_date", "scrape_profile_id"})
        }
)
public class ScrapeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scrape_url", length = 1000, nullable = false, unique = true)
    private String scrapeUrl;

    @Column(name = "first_departure_date", nullable = false)
    private LocalDate firstDepartureDate;

    @JoinColumn(name = "scrape_profile_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ScrapeProfile scrapeProfile;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScrapeTask that = (ScrapeTask) o;
        return Objects.equals(id, that.id) && Objects.equals(scrapeUrl, that.scrapeUrl) && Objects.equals(firstDepartureDate, that.firstDepartureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scrapeUrl, firstDepartureDate);
    }

    @Override
    public String toString() {
        return "ScrapeTask{" +
                "id=" + id +
                ", scrapeUrl='" + scrapeUrl + '\'' +
                ", firstDepartureDate=" + firstDepartureDate +
                '}';
    }
}
