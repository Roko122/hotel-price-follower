package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.RoomPrice;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

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
    Optional<RoomPrice> findMostRecentPrice(@Param("departureDate") LocalDate departureDate,
                                  @Param("roomId") Long roomId,
                                  @Param("profileId") Long profileId,
                                  Limit limit);

    @Query("""
        SELECT rp
        FROM RoomPrice rp
        JOIN FETCH rp.priceFetch pf
        WHERE pf.scrapeProfile.id = :profileId
            AND rp.room.id = :roomId
            AND rp.departureDate = :departureDate
            AND pf.fetchTime BETWEEN :from AND :to
        ORDER BY rp.priceInCents ASC, pf.fetchTime DESC
    """)
    Optional<RoomPrice> findMin30DaysPrice(@Param("departureDate") LocalDate departureDate,
                                 @Param("roomId") Long roomId,
                                 @Param("profileId") Long profileId,
                                 @Param("from") Instant from,
                                 @Param("to") Instant to,
                                 Limit limit);

    @Query("""
        SELECT rp
        FROM RoomPrice rp
        JOIN FETCH rp.priceFetch pf
        WHERE pf.scrapeProfile.id = :profileId
            AND rp.room.id = :roomId
            AND rp.departureDate = :departureDate
        ORDER BY rp.priceInCents ASC, pf.fetchTime DESC
    """)
    Optional<RoomPrice> findAllTimeMin(@Param("departureDate") LocalDate departureDate,
                                       @Param("roomId") Long roomId,
                                       @Param("profileId") Long profileId,
                                       Limit limit);
}
