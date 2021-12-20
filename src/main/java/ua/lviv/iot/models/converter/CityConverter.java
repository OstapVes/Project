package ua.lviv.iot.models.converter;

import ua.lviv.iot.models.converter.domain.City;
import ua.lviv.iot.models.dto.CityDto;

public class CityConverter {
    public static CityDto toDTO(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .region(city.getRegion())
                .country(city.getCountry().getName())
                .build();
    }
}
