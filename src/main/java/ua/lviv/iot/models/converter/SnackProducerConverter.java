package ua.lviv.iot.models.converter;

import ua.lviv.iot.models.converter.domain.SnackProducer;
import ua.lviv.iot.models.dto.SnackProducerDto;

public class SnackProducerConverter {
    public static SnackProducerDto toDTO(SnackProducer snackProducer) {
        return SnackProducerDto.builder()
                .id(snackProducer.getId())
                .name(snackProducer.getName())
                .email(snackProducer.getEmail())
                .mobilePhone(snackProducer.getMobilePhone())
                .fullAddressId(snackProducer.getFullAddress().getId())
                .build();
    }
}
