package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.repository.ScrapeProfileRepository;
import com.roko.hotelpricefollower.scraper.HotelScraper;
import com.roko.hotelpricefollower.scraper.parser.HotelParser;
import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ScrapeProfileService {
    private final ScrapeProfileRepository scrapeProfileRepository;
    private final PriceFetchService priceFetchService;
    private final HotelScraper hotelScraper;
    private final HotelParser hotelParser;
    private final RoomPriceService roomPriceService;

    public ScrapeProfileService(ScrapeProfileRepository scrapeProfileRepository,
                                PriceFetchService priceFetchService,
                                HotelScraper hotelScraper,
                                HotelParser hotelParser,
                                RoomPriceService roomPriceService) {

        this.scrapeProfileRepository = scrapeProfileRepository;
        this.priceFetchService = priceFetchService;
        this.hotelScraper = hotelScraper;
        this.hotelParser = hotelParser;
        this.roomPriceService = roomPriceService;
    }

    public void startScrapes() {
        List<ScrapeProfile> scrapeProfiles = scrapeProfileRepository.findAll();

        for (ScrapeProfile scrapeProfile : scrapeProfiles) {
            Optional<PriceFetch> mostRecentPriceFetch = priceFetchService.getMostRecentPriceFetch(scrapeProfile);

            //check if prices has been fetched today, if not -> scrape prices
            if (!mostRecentPriceFetch.map(this::scrapedToday).orElse(false)) {
                startScrape(scrapeProfile);
            }
        }
    }

    public Optional<ScrapeProfile> getScrapeProfile(Long id) {
        return scrapeProfileRepository.findById(id);
    }

    private void startScrape(ScrapeProfile scrapeProfile) {
        PriceFetch priceFetch = priceFetchService.createPriceFetch(scrapeProfile);

        try {
            String priceMatrix = fetchPriceMatrix(scrapeProfile.getScrapeUrl());
            List<RoomPriceData> roomPriceDataList = parseRoomPrices(priceMatrix);

            roomPriceService.saveRoomPrices(roomPriceDataList, scrapeProfile.getHotel(), priceFetch);
            priceFetch.setSuccess(true);
        } catch (Exception e) {
            priceFetch.setSuccess(false);
            priceFetch.setError(e.getMessage());
        } finally {
            priceFetchService.savePriceFetch(priceFetch);
        }
    }

    private boolean scrapedToday(PriceFetch priceFetch) {
        LocalDate now = LocalDate.now(ZoneId.of("Europe/Helsinki"));
        LocalDate lastFetchDate = priceFetch.getFetchTime().atZone(ZoneId.of("Europe/Helsinki")).toLocalDate();

        return now.isEqual(lastFetchDate);
    }

    private String fetchPriceMatrix(String hotelUrl) {
        return hotelScraper.scrapePriceMatrix(hotelUrl);
    }

    private List<RoomPriceData> parseRoomPrices(String priceMatrix) {
        return hotelParser.parseRoomPrices(priceMatrix);
    }
}
