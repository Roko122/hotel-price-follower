package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.dto.CreateHotelRequest;
import com.roko.hotelpricefollower.dto.HotelDto;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
    public Hotel toHotel(CreateHotelRequest createHotelRequest) {
        return Hotel.builder()
                .name(createHotelRequest.name())
                .imageUrl(createHotelRequest.imageUrl())
                .build();
    }

    public HotelDto toHotelDto(Hotel hotel) {
        return new HotelDto(hotel.getId(), hotel.getName(), hotel.getImageUrl());
    }
}
