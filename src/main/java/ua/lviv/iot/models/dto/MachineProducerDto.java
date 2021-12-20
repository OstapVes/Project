package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MachineProducerDto {
    private Integer id;
    private String name;
    private String email;
    private String mobilePhone;
    private Integer fullAddressId;
}
