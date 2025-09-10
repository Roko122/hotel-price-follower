package com.roko.hotelpricefollower.repository;

import com.roko.hotelpricefollower.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
