package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.MachineProducerConverter;
import ua.lviv.iot.models.converter.domain.MachineProducer;
import ua.lviv.iot.models.dto.MachineProducerDto;
import ua.lviv.iot.service.FullAddressService;
import ua.lviv.iot.service.MachineProducerService;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/machine_producer")
public class MachineProducerController {
    @Autowired
    MachineProducerService machineProducerService;

    @Autowired
    FullAddressService fullAddressService;

    @GetMapping
    public ResponseEntity<List<MachineProducerDto>> getMachineProducerList() {
        List<MachineProducerDto> machineProducerDtoList = new LinkedList<>();
        for (MachineProducer machineProducer: machineProducerService.getAll()) {
            machineProducerDtoList.add(MachineProducerConverter.toDTO(machineProducer));
        }
        return new ResponseEntity<>(machineProducerDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MachineProducerDto> getMachineProducer(@PathVariable Integer id) {
        try {
            MachineProducer searchedMachineProducer = machineProducerService.getById(id);
            return new ResponseEntity<>(MachineProducerConverter.toDTO(searchedMachineProducer), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<MachineProducerDto> postMachineProducer(@RequestBody MachineProducerDto machineProducerDto) {
       try {
           MachineProducer createdMachineProducer = MachineProducer.builder()
                   .name(machineProducerDto.getName())
                   .email(machineProducerDto.getEmail())
                   .mobilePhone(machineProducerDto.getMobilePhone())
                   .fullAddress(fullAddressService.getById(machineProducerDto.getFullAddressId()))
                   .build();
           Integer newMachineProducerId = machineProducerService.create(createdMachineProducer).getId();
           machineProducerDto.setId(newMachineProducerId);
           return new ResponseEntity<>(machineProducerDto, HttpStatus.CREATED);
       } catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MachineProducerDto> putMachineProducer
            (@PathVariable Integer id, @RequestBody MachineProducerDto machineProducerDto) {
        try {
            MachineProducer updatedMachineProducer = MachineProducer.builder()
                    .name(machineProducerDto.getName())
                    .email(machineProducerDto.getEmail())
                    .mobilePhone(machineProducerDto.getMobilePhone())
                    .build();
            try {
                updatedMachineProducer.setFullAddress(fullAddressService.getById(machineProducerDto.getFullAddressId()));
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            machineProducerService.updateById(updatedMachineProducer, id);
            machineProducerDto.setId(id);
            return new ResponseEntity<>(machineProducerDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<MachineProducerDto> deleteMachineProducer(@PathVariable Integer id){
        try {
            machineProducerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
