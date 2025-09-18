package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PriceFetch that = (PriceFetch) o;
        return success == that.success && Objects.equals(id, that.id) && Objects.equals(fetchTime, that.fetchTime) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fetchTime, success, error);
    }

    @Override
    public String toString() {
        return "PriceFetch{" +
                "id=" + id +
                ", fetchTime=" + fetchTime +
                ", success=" + success +
                ", error='" + error + '\'' +
                '}';
    }

    @PrePersist
    private void setFetchTime() {
        this.fetchTime = Instant.now();
    }
}
