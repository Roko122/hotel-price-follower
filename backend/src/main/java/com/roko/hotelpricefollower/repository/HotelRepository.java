package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.dto.HotelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("""
        SELECT new com.roko.hotelpricefollower.dto.HotelDto(
            h.id,
            h.name,
            h.imageUrl
        )
        FROM Hotel h
    """)
    List<HotelDto> findAllHotelDto();


    boolean existsByName(String name);
}
