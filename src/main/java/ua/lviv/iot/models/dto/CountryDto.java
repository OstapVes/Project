package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDto {
    private Integer id;
    private String name;
}
