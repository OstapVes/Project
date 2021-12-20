package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FullAddressDto {
    private Integer id;
    private String street;
    private String number;
    private String country;
    private String city;
    private Integer cityId;
}
