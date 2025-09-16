package com.roko.hotelpricefollower.service.validation;

import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.exception.NotFoundException;
import com.roko.hotelpricefollower.repository.RoomRepository;
import com.roko.hotelpricefollower.repository.ScrapeProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class RoomPriceValidator {
    private final RoomRepository roomRepository;
    private final ScrapeProfileRepository scrapeProfileRepository;

    public RoomPriceValidator(RoomRepository roomRepository, ScrapeProfileRepository scrapeProfileRepository) {
        this.roomRepository = roomRepository;
        this.scrapeProfileRepository = scrapeProfileRepository;
    }

    public void validateRequest(Long hotelId, Long roomId, Long profileId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException("Room with id " + roomId + " does not exist"));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new NotFoundException("Room with id " + roomId + " does not belong to hotel with id " + hotelId);
        }

        ScrapeProfile scrapeProfile = scrapeProfileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Scrape profile with id " + profileId + " does not exist"));

        if (!scrapeProfile.getHotel().getId().equals(hotelId)) {
            throw new NotFoundException(
                    "ScrapeProfile with id " + profileId + " does not belong to hotel with id " + hotelId
            );
        }
    }
}
