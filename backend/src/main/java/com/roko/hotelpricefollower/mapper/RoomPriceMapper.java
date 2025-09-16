package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.RoomPrice;
import com.roko.hotelpricefollower.dto.PriceDetailsDto;
import com.roko.hotelpricefollower.dto.RoomPriceSummaryDto;
import com.roko.hotelpricefollower.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class RoomPriceMapper {

    public PriceDetailsDto toPriceDetailsDto(RoomPrice roomPrice) {
        BigDecimal price = new BigDecimal(roomPrice.getPriceInCents()).movePointLeft(2);
        LocalDate priceFetchedDate = DateTimeUtil.toLocalDate(roomPrice.getPriceFetch().getFetchTime());

        return new PriceDetailsDto(
                price,
                roomPrice.isSoldOut(),
                roomPrice.getAdditionalInfo(),
                priceFetchedDate
        );
    }

    public RoomPriceSummaryDto toRoomPriceSummaryDto(LocalDate departureDate,
                                                         PriceDetailsDto mostRecentPrice,
                                                         PriceDetailsDto min30DaysPrice,
                                                         PriceDetailsDto allTimeMinPrice) {
        return new RoomPriceSummaryDto(
                departureDate,
                mostRecentPrice,
                min30DaysPrice,
                allTimeMinPrice
        );
    }
}
