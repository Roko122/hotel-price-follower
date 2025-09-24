package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.Hotel;
import com.roko.hotelpricefollower.dto.HotelDto;
import com.roko.hotelpricefollower.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Optional<Hotel> getHotel(Long hotelId) {
        return hotelRepository.findById(hotelId);
    }

    public List<HotelDto> getHotels() {
        return hotelRepository.findAllHotelDto();
    }
}
