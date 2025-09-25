package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeTask;
import com.roko.hotelpricefollower.repository.ScrapeTaskRepository;
import com.roko.hotelpricefollower.scraper.HotelScraper;
import com.roko.hotelpricefollower.scraper.parser.HotelParser;
import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import com.roko.hotelpricefollower.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScrapeTaskService {
    private final PriceFetchService priceFetchService;
    private final HotelScraper hotelScraper;
    private final HotelParser hotelParser;
    private final RoomPriceService roomPriceService;
    private final ScrapeTaskRepository scrapeTaskRepository;

    public ScrapeTaskService(
            PriceFetchService priceFetchService,
            HotelScraper hotelScraper,
            HotelParser hotelParser,
            RoomPriceService roomPriceService,
            ScrapeTaskRepository scrapeTaskRepository) {


        this.priceFetchService = priceFetchService;
        this.hotelScraper = hotelScraper;
        this.hotelParser = hotelParser;
        this.roomPriceService = roomPriceService;
        this.scrapeTaskRepository = scrapeTaskRepository;
    }

    @Transactional
    public void startScrapes() {
        List<ScrapeTask> scrapeTasks = scrapeTaskRepository.findAll();
        LocalDate now = LocalDate.now();

        for (ScrapeTask scrapeTask : scrapeTasks) {

            //check if departure date is in the past
            if (scrapeTask.getFirstDepartureDate().isBefore(now)) {
                continue; //scrape_url is not working anymore
            }

            Optional<PriceFetch> mostRecentPriceFetch = priceFetchService.getMostRecentPriceFetch(scrapeTask);
            //check if prices has been fetched today
            if (mostRecentPriceFetch.map(this::scrapedToday).orElse(false)) {
                continue; //prices already fetched today
            }

            startScrape(scrapeTask);
        }
    }

    private void startScrape(ScrapeTask scrapeTask) {
        PriceFetch priceFetch = priceFetchService.createPriceFetch(scrapeTask);

        try {
            String priceMatrix = fetchPriceMatrix(scrapeTask.getScrapeUrl());
            List<RoomPriceData> roomPriceDataList = parseRoomPrices(priceMatrix);

            roomPriceService.saveRoomPrices(roomPriceDataList, scrapeTask.getScrapeProfile().getHotel(), priceFetch);
            priceFetch.setSuccess(true);
        } catch (Exception exception) {
            priceFetch.setSuccess(false);
            priceFetch.setError(exception.getMessage());
            throw exception;
        } finally {
            priceFetchService.savePriceFetch(priceFetch);
        }
    }

    private boolean scrapedToday(PriceFetch priceFetch) {
        LocalDate now = LocalDate.now(DateTimeUtil.zoneIdHelsinki());
        LocalDate lastFetchDate = DateTimeUtil.toLocalDate(priceFetch.getFetchTime());

        return now.isEqual(lastFetchDate);
    }

    private String fetchPriceMatrix(String hotelUrl) {
        return hotelScraper.scrapePriceMatrix(hotelUrl);
    }

    private List<RoomPriceData> parseRoomPrices(String priceMatrix) {
        return hotelParser.parseRoomPrices(priceMatrix);
    }
}
