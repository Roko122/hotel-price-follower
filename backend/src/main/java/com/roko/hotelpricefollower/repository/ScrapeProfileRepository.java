package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapeProfileRepository extends JpaRepository<ScrapeProfile,Long> {
    List<ScrapeProfile> findAllByHotel(Hotel hotel);

    boolean existsByHotelAndDurationWeeksAndAdultsAndChildren(Hotel hotel,
                                                              Integer durationWeeks,
                                                              Integer adults,
                                                              Integer children);
}
