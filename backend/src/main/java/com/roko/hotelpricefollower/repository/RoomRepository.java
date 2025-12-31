package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.dto.RoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByHotelAndType(Hotel hotel, String type);

    List<Room> findAllByHotel(Hotel hotel);

    @Query("""
        SELECT new com.roko.hotelpricefollower.dto.RoomDto(
            r.id,
            r.type
        )
        FROM Room r
    """)
    List<RoomDto> findAllByHotelId(Long hotelId);
}
