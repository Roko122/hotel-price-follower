package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByHotelAndType(Hotel hotel, String type);
}
