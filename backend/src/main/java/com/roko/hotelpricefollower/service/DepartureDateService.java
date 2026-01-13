package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.DepartureDate;
import com.roko.hotelpricefollower.repository.DepartureDateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepartureDateService {

    private final DepartureDateRepository departureDateRepository;

    public DepartureDateService(DepartureDateRepository departureDateRepository) {
        this.departureDateRepository = departureDateRepository;
    }

    public DepartureDate getDepartureDateId(LocalDate date) {
        return departureDateRepository.findByDate(date)
                .orElseGet(() -> departureDateRepository.save(new DepartureDate(date)));
    }

    public List<DepartureDate> getDepartureDatesByHotel(Long hotelId) {
        return departureDateRepository.findAllByHotelId(hotelId);
    }
}
