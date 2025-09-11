package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.RoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long> {
}
