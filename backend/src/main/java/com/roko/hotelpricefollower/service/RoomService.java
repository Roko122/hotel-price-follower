package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getOrCreateRoom(Hotel hotel, String roomType) {
        return roomRepository.findByHotelAndType(hotel, roomType)
                .orElseGet(() -> {
                    Room room = Room.builder()
                            .hotel(hotel)
                            .type(roomType)
                            .build();

                    roomRepository.save(room);
                    return room;
                });
    }
}
