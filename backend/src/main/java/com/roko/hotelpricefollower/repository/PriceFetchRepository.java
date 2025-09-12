package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceFetchRepository extends JpaRepository<PriceFetch, Long> {
    Optional<PriceFetch> findFirstByScrapeProfileOrderByFetchTimeDesc(ScrapeProfile scrapeProfile);
}
