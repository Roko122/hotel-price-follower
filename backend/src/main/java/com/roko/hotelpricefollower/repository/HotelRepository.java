package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
