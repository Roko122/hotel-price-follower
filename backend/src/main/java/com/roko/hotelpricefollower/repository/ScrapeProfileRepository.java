package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.ScrapeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapeProfileRepository extends JpaRepository<ScrapeProfile,Long> {
}
