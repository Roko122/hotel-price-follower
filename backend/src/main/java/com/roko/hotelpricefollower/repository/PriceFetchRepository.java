package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.PriceFetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceFetchRepository extends JpaRepository<PriceFetch, Long> {
}
