package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.RoomPrice;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long> {

    @Query("""
        SELECT rp
        FROM RoomPrice rp
        JOIN FETCH rp.priceFetch pf
        WHERE pf.scrapeProfile.id = :profileId
            AND rp.room.id = :roomId
            AND rp.departureDate = :departureDate
        ORDER BY pf.fetchTime DESC
    """)
    RoomPrice getMostRecentPrice(@Param("departureDate") LocalDate departureDate,
                                 @Param("roomId") Long roomId,
                                 @Param("profileId") Long profileId,
                                 Limit limit);
}
