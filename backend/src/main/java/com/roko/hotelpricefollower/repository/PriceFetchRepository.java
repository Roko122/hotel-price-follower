package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface PriceFetchRepository extends JpaRepository<PriceFetch, Long> {
    Optional<PriceFetch> findFirstByScrapeTaskOrderByFetchTimeDesc(ScrapeTask scrapeTask);

    @Query("""
    SELECT MAX(pf.fetchTime)
        FROM PriceFetch pf
        JOIN pf.scrapeTask st
        JOIN st.scrapeProfile sp
        WHERE sp.hotel.id = :hotelId AND pf.success = true
    """)
    Instant findLastFetchTimeByHotel(Long hotelId);
}
