package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.SnackMachineConverter;
import ua.lviv.iot.models.converter.domain.SnackMachine;
import ua.lviv.iot.models.dto.SnackMachineDto;
import ua.lviv.iot.service.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/snack_machine")
public class SnackMachineController {
    @Autowired
    SnackMachineService snackMachineService;

    @Autowired
    FullAddressService fullAddressService;

    @Autowired
    MachineProducerService machineProducerService;

    @Autowired
    MachineModelService machineModelService;

    @Autowired
    MachineServiceService machineServiceService;

    @GetMapping
    public ResponseEntity<List<SnackMachineDto>> getSnackMachineList() {
        List<SnackMachineDto> snackMachineDtoList = new LinkedList<>();
        for (SnackMachine snackMachine: snackMachineService.getAll()) {
            snackMachineDtoList.add(SnackMachineConverter.toDTO(snackMachine));
        }
        return new ResponseEntity<>(snackMachineDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SnackMachineDto> getSnackMachine(@PathVariable Integer id) {
        try {
            SnackMachine searchedSnackMachine = snackMachineService.getById(id);
            return new ResponseEntity<>(SnackMachineConverter.toDTO(searchedSnackMachine), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SnackMachineDto> postSnackMachine(@RequestBody SnackMachineDto snackMachineDto) {
        try {
            SnackMachine createdSnackMachine = SnackMachine.builder()
                    .machineModel(machineModelService.getByModel(snackMachineDto.getMachineModel()))
                    .machineProducer(machineProducerService.getById(snackMachineDto.getMachineProducerId()))
                    .build();
            if (snackMachineDto.getFullAddressId() != null) {
                createdSnackMachine.setFullAddress(fullAddressService.getById(snackMachineDto.getFullAddressId()));
            }
            SnackMachine createdMachine = snackMachineService.create(createdSnackMachine);
            createdSnackMachine.setId(createdMachine.getId());
            return new ResponseEntity<>(SnackMachineConverter.toDTO(createdSnackMachine), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SnackMachineDto> putSnackMachine
            (@RequestBody SnackMachineDto snackMachineDto, @PathVariable Integer id) {
        try {
            SnackMachine updatedSnackMachine = SnackMachine.builder()
                    .machineProducer(machineProducerService.getById(snackMachineDto.getMachineProducerId()))
                    .machineModel(machineModelService.getByModel(snackMachineDto.getMachineModel()))
                    .build();
            if (snackMachineDto.getFullAddressId() != null) {
                updatedSnackMachine.setFullAddress(fullAddressService.getById(snackMachineDto.getFullAddressId()));
            }
            snackMachineService.updateById(updatedSnackMachine, id);
            snackMachineDto.setId(id);
            return new ResponseEntity<>(snackMachineDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SnackMachineDto> deleteSnackMachine(@PathVariable Integer id) {
        try {
            snackMachineService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
