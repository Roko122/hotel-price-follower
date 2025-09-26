package com.roko.hotelpricefollower.service.validation;

import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.exception.NotFoundException;
import com.roko.hotelpricefollower.repository.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public class RoomPriceValidator {
    private final RoomRepository roomRepository;

    public RoomPriceValidator(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void validateRequest(Long hotelId, Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException("Room with id " + roomId + " does not exist"));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new NotFoundException("Room with id " + roomId + " does not belong to hotel with id " + hotelId);
        }
    }
}
