package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.MachineModelConverter;
import ua.lviv.iot.models.converter.domain.MachineModel;
import ua.lviv.iot.models.dto.MachineModelDto;
import ua.lviv.iot.service.MachineModelService;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/machine_model")
public class MachineModelController {
    @Autowired
    MachineModelService machineModelService;

    @GetMapping
    public ResponseEntity<List<MachineModelDto>> getMachineModelList() {
        List<MachineModelDto> machineModelDtoList = new LinkedList<>();
        for (MachineModel machineModel: machineModelService.getAll()) {
            machineModelDtoList.add(MachineModelConverter.toDTO(machineModel));
        }
        return new ResponseEntity<>(machineModelDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{model}")
    public ResponseEntity<MachineModelDto> getMachineModel(@PathVariable String model) {
        try {
            MachineModel searchedMachineModel = machineModelService.getByModel(model);
            return new ResponseEntity<>(MachineModelConverter.toDTO(searchedMachineModel), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MachineModelDto> postMachineModel(@RequestBody MachineModelDto machineModelDto) {
        machineModelService.create(MachineModelConverter.toEntity(machineModelDto));
        return new ResponseEntity<>(machineModelDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{model}")
    public ResponseEntity<MachineModelDto> putMachineModel
            (@RequestBody MachineModelDto machineModelDto, @PathVariable String model) {
        try {
            MachineModel machineModel = MachineModelConverter.toEntity(machineModelDto);
            machineModelService.updateByModel(machineModel, model);
            return new ResponseEntity<>(machineModelDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{model}")
    public ResponseEntity<MachineModelDto> deleteMachineModel (@PathVariable String model) {
        try {
            machineModelService.deleteByModel(model);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
