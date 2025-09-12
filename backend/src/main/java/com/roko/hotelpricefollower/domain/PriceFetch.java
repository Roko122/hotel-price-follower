package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "price_fetches",
        indexes = {
            @Index(name = "idx_fetch_time", columnList = "fetch_time")
        }
    )
public class PriceFetch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fetch_time", nullable = false)
    private Instant fetchTime;

    @Column(name = "success", nullable = false)
    private boolean success;

    @Column(name = "error")
    private String error;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scrape_profile_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ScrapeProfile scrapeProfile;
}
