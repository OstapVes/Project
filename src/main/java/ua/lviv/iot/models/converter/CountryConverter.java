package ua.lviv.iot.models.converter;

import ua.lviv.iot.models.converter.domain.Country;
import ua.lviv.iot.models.dto.CountryDto;

public class CountryConverter {
    public static CountryDto toDTO(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
