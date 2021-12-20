package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MachineModelDto {
    private String model;
    private Integer capacity;
    private Integer electricityConsumption;
}
