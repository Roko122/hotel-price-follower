package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.domain.RoomPrice;
import com.roko.hotelpricefollower.dto.PriceDetailsDto;
import com.roko.hotelpricefollower.dto.RoomPriceDto;
import com.roko.hotelpricefollower.dto.RoomPriceSummaryDto;
import com.roko.hotelpricefollower.mapper.RoomPriceMapper;
import com.roko.hotelpricefollower.repository.RoomPriceRepository;
import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import com.roko.hotelpricefollower.service.validation.RoomPriceValidator;
import com.roko.hotelpricefollower.util.DateTimeUtil;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomPriceService {

    private final RoomPriceRepository roomPriceRepository;
    private final RoomService roomService;
    private final RoomPriceValidator roomPriceValidator;
    private final RoomPriceMapper roomPriceMapper;

    public RoomPriceService(RoomPriceRepository roomPriceRepository,
                            RoomService roomService,
                            RoomPriceValidator roomPriceValidator,
                            RoomPriceMapper roomPriceMapper) {

        this.roomPriceRepository = roomPriceRepository;
        this.roomService = roomService;
        this.roomPriceValidator = roomPriceValidator;
        this.roomPriceMapper = roomPriceMapper;
    }

    @Transactional
    public void saveRoomPrices(List<RoomPriceData> roomPriceDataList, Hotel hotel, PriceFetch priceFetch) {
        List<RoomPrice> roomPrices = new ArrayList<>();
        for (RoomPriceData roomPriceData : roomPriceDataList) {
            Room room = roomService.getOrCreateRoom(hotel, roomPriceData.getRoomType());

            RoomPrice roomPrice = createRoomPrice(roomPriceData, room, priceFetch);
            roomPrices.add(roomPrice);
        }
        roomPriceRepository.saveAll(roomPrices);
    }

    @Transactional(readOnly = true)
    public List<RoomPriceSummaryDto> getRoomPriceSummary(Long hotelId,
                                                         Long profileId,
                                                         Long roomId,
                                                         List<LocalDate> departureDateList) {
        //Throws an exception if request is not valid
        roomPriceValidator.validateRequest(hotelId, roomId, profileId);

        ArrayList<RoomPriceSummaryDto> roomPriceSummaries = new ArrayList<>();
        for (LocalDate departureDate : departureDateList) {
            PriceDetailsDto mostRecentPrice = createMostRecentPrice(departureDate, roomId, profileId);
            PriceDetailsDto min30DaysPrice = createMin30DaysPrice(departureDate, roomId, profileId);
            PriceDetailsDto allTimeMinPrice = createAllTimeMin(departureDate, roomId, profileId);

            RoomPriceSummaryDto priceSummary = roomPriceMapper.toRoomPriceSummaryDto(
                    departureDate, mostRecentPrice, min30DaysPrice, allTimeMinPrice);

            roomPriceSummaries.add(priceSummary);
        }

        return roomPriceSummaries;
    }

    public List<RoomPriceDto> getAllRoomPrices(Long hotelId, Long profileId, Long roomId, LocalDate departureDate) {
        //Throws an exception if request is not valid
        roomPriceValidator.validateRequest(hotelId, roomId, profileId);

        return roomPriceRepository.findAllPricesByDepartureDate(departureDate, roomId, profileId);
    }

    private RoomPrice createRoomPrice(RoomPriceData roomPriceData, Room room, PriceFetch priceFetch) {
        Long price = roomPriceData.getParsedRoomPrice().getPrice();

        if (price != null) {
            price *= 100;
        }

        return RoomPrice.builder()
                .priceInCents(price)
                .departureDate(roomPriceData.getDepartureDate())
                .soldOut(roomPriceData.getParsedRoomPrice().getSoldOut())
                .additionalInfo(roomPriceData.getParsedRoomPrice().getAdditionalInformation())
                .room(room)
                .priceFetch(priceFetch)
                .build();
    }

    private PriceDetailsDto createAllTimeMin(LocalDate departureDate, Long roomId, Long profileId) {
        Optional<RoomPrice> allTimeMinPrice = roomPriceRepository.findAllTimeMin(
                departureDate,
                roomId,
                profileId,
                Limit.of(1));

        return allTimeMinPrice
                .map(roomPriceMapper::toPriceDetailsDto)
                .orElse(null);
    }

    private PriceDetailsDto createMin30DaysPrice(LocalDate departureDate, Long roomId, Long profileId) {
        Optional<RoomPrice> min30DaysPrice = roomPriceRepository.findMin30DaysPrice(
                departureDate,
                roomId,
                profileId,
                DateTimeUtil.instant30DaysAgo(),
                DateTimeUtil.instantNow(),
                Limit.of(1));

        return min30DaysPrice
                .map(roomPriceMapper::toPriceDetailsDto)
                .orElse(null);
    }

    private PriceDetailsDto createMostRecentPrice(LocalDate departureDate, Long roomId, Long profileId) {
        Optional<RoomPrice> latestPrice = roomPriceRepository.findMostRecentPrice(
                departureDate,
                roomId,
                profileId,
                Limit.of(1));

        return latestPrice
                .map(roomPriceMapper::toPriceDetailsDto)
                .orElse(null);
    }
}
