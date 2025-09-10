package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.RoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long> {
}
