package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.ScrapeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapeTaskRepository extends JpaRepository<ScrapeTask, Long> {
}
