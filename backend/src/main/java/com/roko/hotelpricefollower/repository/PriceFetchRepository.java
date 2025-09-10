package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.PriceFetch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceFetchRepository extends JpaRepository<PriceFetch, Long> {
}
