package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.Room;
import com.roko.hotelpricefollower.domain.RoomPrice;
import com.roko.hotelpricefollower.repository.RoomPriceRepository;
import com.roko.hotelpricefollower.scraper.parser.dto.RoomPriceData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomPriceService {

    private final RoomPriceRepository roomPriceRepository;
    private final RoomService roomService;

    public RoomPriceService(RoomPriceRepository roomPriceRepository, RoomService roomService) {
        this.roomPriceRepository = roomPriceRepository;
        this.roomService = roomService;
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
}
