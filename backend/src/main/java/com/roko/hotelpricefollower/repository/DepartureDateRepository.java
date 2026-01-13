package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.DepartureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartureDateRepository extends JpaRepository<DepartureDate, Long> {
    Optional<DepartureDate> findByDate(LocalDate date);

    @Query("""
        SELECT DISTINCT dd
            FROM RoomPrice rp
            JOIN rp.departureDate dd
            JOIN rp.room r
            WHERE r.hotel.id = :hotelId
            ORDER BY dd.date
    """)
    List<DepartureDate> findAllByHotelId(Long hotelId);
}
