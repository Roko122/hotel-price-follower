package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapeProfileRepository extends JpaRepository<ScrapeProfile,Long> {
}
