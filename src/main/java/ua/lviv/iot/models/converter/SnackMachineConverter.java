package ua.lviv.iot.models.converter;

import ua.lviv.iot.models.converter.domain.SnackMachine;
import ua.lviv.iot.models.dto.SnackMachineDto;

public class SnackMachineConverter {
    public static SnackMachineDto toDTO(SnackMachine snackMachine) {
        SnackMachineDto snackMachineDto = SnackMachineDto.builder()
                .id(snackMachine.getId())
                .machineModel(snackMachine.getMachineModel().getModel())
                .machineProducerId(snackMachine.getMachineProducer().getId())
                .build();
        if (snackMachine.getFullAddress() != null) {
            snackMachineDto.setFullAddressId(snackMachine.getFullAddress().getId());
        }
        return snackMachineDto;
    }
}
