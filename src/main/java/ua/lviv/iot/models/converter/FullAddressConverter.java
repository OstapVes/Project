package ua.lviv.iot.models.converter;

import ua.lviv.iot.models.converter.domain.FullAddress;
import ua.lviv.iot.models.dto.FullAddressDto;

public class FullAddressConverter {
    public static FullAddressDto toDTO(FullAddress fullAddress) {
        return FullAddressDto.builder()
                .id(fullAddress.getId())
                .number(fullAddress.getNumber())
                .street(fullAddress.getStreet())
                .city(fullAddress.getCity().getName())
                .cityId(fullAddress.getCity().getId())
                .country(fullAddress.getCity().getCountry().getName())
                .build();
    }
}
