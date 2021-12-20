package ua.lviv.iot.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MachineServiceDto {
    private Integer machineId;
    private String lastLoad;
    private String lastCashGathering;
    private Integer gatheredCash;
    private String lastCoinLoad;
    private Integer loadedCoins;
}
