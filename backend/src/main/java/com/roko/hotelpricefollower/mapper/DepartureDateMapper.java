package com.roko.hotelpricefollower.mapper;

import com.roko.hotelpricefollower.domain.DepartureDate;
import com.roko.hotelpricefollower.dto.DepartureDateDto;
import org.springframework.stereotype.Component;

@Component
public class DepartureDateMapper {

    public DepartureDateDto toDepartureDateDto(DepartureDate departureDate) {
        return new DepartureDateDto(departureDate.getId(), departureDate.getDate());
    }
}
