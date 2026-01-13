package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.DepartureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DepartureDateRepository extends JpaRepository<DepartureDate, Long> {
    Optional<DepartureDate> findByDate(LocalDate date);
}
