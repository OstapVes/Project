package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoaderDto {
    private Integer id;
    private String name;
    private String surname;
    private String mobilePhone;
    private String company;
    private String email;
    private Integer fullAddressId;
}
