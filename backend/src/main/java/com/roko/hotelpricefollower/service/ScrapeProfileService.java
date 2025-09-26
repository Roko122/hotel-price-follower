package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.repository.ScrapeProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapeProfileService {

    private final ScrapeProfileRepository scrapeProfileRepository;
    private final HotelService hotelService;

    public ScrapeProfileService(ScrapeProfileRepository scrapeProfileRepository, HotelService hotelService) {
        this.scrapeProfileRepository = scrapeProfileRepository;
        this.hotelService = hotelService;
    }

    public List<ScrapeProfile> getAllScrapeProfilesByHotel(Long hotelId) {
        Hotel hotel = this.findHotel(hotelId);

        return scrapeProfileRepository.findAllByHotel(hotel);
    }

    public ScrapeProfile createScrapeProfile(Long hotelId, ScrapeProfile toCreate) {
        Hotel hotel = this.findHotel(hotelId);

        if (scrapeProfileExists(hotel, toCreate)) {
            throw new IllegalArgumentException("ScrapeProfile already exists with given parameters");
        }

        return scrapeProfileRepository.save(toCreate);
    }

    private boolean scrapeProfileExists(Hotel hotel, ScrapeProfile toCreate) {
        return scrapeProfileRepository.existsByHotelAndDurationWeeksAndAdultsAndChildren(
                hotel,
                toCreate.getDurationWeeks(),
                toCreate.getAdults(),
                toCreate.getChildren()
        );
    }

    private Hotel findHotel(Long hotelId) {
        return hotelService.getHotel(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel id " + hotelId + " does not exist"));
    }
}
