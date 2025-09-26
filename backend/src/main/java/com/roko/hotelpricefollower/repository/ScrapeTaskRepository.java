package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.domain.ScrapeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ScrapeTaskRepository extends JpaRepository<ScrapeTask, Long> {
    boolean existsByScrapeProfileAndFirstDepartureDate(ScrapeProfile scrapeProfile, LocalDate firstDepartureDate);
}
