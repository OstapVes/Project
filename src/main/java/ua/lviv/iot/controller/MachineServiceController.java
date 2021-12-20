package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.MachineServiceConverter;
import ua.lviv.iot.models.converter.domain.MachineService;
import ua.lviv.iot.models.dto.MachineServiceDto;
import ua.lviv.iot.service.MachineServiceService;
import ua.lviv.iot.service.SnackMachineService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/snack_machine")
public class MachineServiceController {
    @Autowired
    MachineServiceService machineServiceService;

    @Autowired
    SnackMachineService snackMachineService;

    @GetMapping(path = "/service")
    public ResponseEntity<List<MachineServiceDto>> getMachineServiceList() {
        List<MachineServiceDto> machineServiceDtoList = new LinkedList<>();
        for (MachineService machineService: machineServiceService.getAll()) {
            machineServiceDtoList.add(MachineServiceConverter.toDTO(machineService));
        }
        return new ResponseEntity<>(machineServiceDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/service")
    public ResponseEntity<MachineServiceDto> getMachineService(@PathVariable Integer id) {
        try {
            MachineService searchedMachineService = machineServiceService.getById(id);
            return new ResponseEntity<>(MachineServiceConverter.toDTO(searchedMachineService), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "{id}/service")
    public ResponseEntity<MachineServiceDto> postMachineService
            (@PathVariable Integer id, @RequestBody MachineServiceDto machineServiceDto) {
        try {
            MachineService createdMachineService = MachineService.builder()
                    .machineId(id)
                    .snackMachine(snackMachineService.getById(id))
                    .build();
            if (machineServiceDto.getLastLoad() != null) {
                createdMachineService.setLastLoad(LocalDate.parse(machineServiceDto.getLastLoad()));
            }
            if (machineServiceDto.getLastCashGathering() != null) {
                createdMachineService.setLastCashGathering(LocalDate.parse(machineServiceDto.getLastCashGathering()));
                createdMachineService.setGatheredCash(machineServiceDto.getGatheredCash());
            }
            if (machineServiceDto.getLastCoinLoad() != null) {
                createdMachineService.setLastCoinLoad(LocalDate.parse(machineServiceDto.getLastCoinLoad()));
                createdMachineService.setLoadedCoins(machineServiceDto.getLoadedCoins());
            }
            Integer createdServiceId = machineServiceService.create(createdMachineService).getMachineId();
            machineServiceDto.setMachineId(createdServiceId);
            return new ResponseEntity<>(machineServiceDto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "{id}/service")
    public ResponseEntity<MachineServiceDto> putMachineService
            (@PathVariable Integer id, @RequestBody MachineServiceDto machineServiceDto) {
        try {
            MachineService updatedMachineService = new MachineService();
            if (machineServiceDto.getLastLoad() != null) {
                updatedMachineService.setLastLoad(LocalDate.parse(machineServiceDto.getLastLoad()));
            }
            if (machineServiceDto.getLastCashGathering() != null) {
                updatedMachineService.setLastCashGathering(LocalDate.parse(machineServiceDto.getLastCashGathering()));
                updatedMachineService.setGatheredCash(machineServiceDto.getGatheredCash());
            }
            if (machineServiceDto.getLastCoinLoad() != null) {
                updatedMachineService.setLastCoinLoad(LocalDate.parse(machineServiceDto.getLastCoinLoad()));
                updatedMachineService.setLoadedCoins(machineServiceDto.getLoadedCoins());
            }
            machineServiceService.updateById(updatedMachineService, id);
            machineServiceDto.setMachineId(id);
            return new ResponseEntity<>(machineServiceDto, HttpStatus.OK);
        } catch (NoSuchElementException | DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{id}/service")
    public ResponseEntity<MachineServiceDto> deleteMachineService(@PathVariable Integer id) {
        try {
            machineServiceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
