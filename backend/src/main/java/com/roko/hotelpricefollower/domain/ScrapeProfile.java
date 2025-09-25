package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "scrape_profiles")
public class ScrapeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration_weeks", nullable = false)
    private Integer durationWeeks;

    @Column(name = "adults", nullable = false)
    private Integer adults;

    @Column(name = "children", nullable = false)
    private Integer children;

    @JoinColumn(name = "hotel_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScrapeProfile that = (ScrapeProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(durationWeeks, that.durationWeeks) && Objects.equals(adults, that.adults) && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, durationWeeks, adults, children);
    }

    @Override
    public String toString() {
        return "ScrapeProfile{" +
                "id=" + id +
                ", durationWeeks=" + durationWeeks +
                ", adults=" + adults +
                ", children=" + children +
                ", hotel=" + hotel +
                '}';
    }
}
