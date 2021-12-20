package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SnackDto {
    private Integer id;
    private String trademark;
    private String type;
    private Integer price;
    private String producer;
}
