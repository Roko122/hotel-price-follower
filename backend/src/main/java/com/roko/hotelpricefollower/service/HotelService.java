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

    public Hotel createHotel(Hotel hotelToCreate) {
        //check if hotel already exists with given name
        if (hotelRepository.existsByName(hotelToCreate.getName())) {
            throw new IllegalArgumentException("Hotel with name '" + hotelToCreate.getName() + "' already exists");
        }

        return hotelRepository.save(hotelToCreate);
    }
}
