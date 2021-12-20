package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.SnackConverter;
import ua.lviv.iot.models.converter.domain.Snack;
import ua.lviv.iot.models.dto.SnackDto;
import ua.lviv.iot.service.SnackProducerService;
import ua.lviv.iot.service.SnackService;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/snack")
public class SnackController {
    @Autowired
    SnackService snackService;

    @Autowired
    SnackProducerService snackProducerService;

    @GetMapping
    public ResponseEntity<List<SnackDto>> getSnackList() {
        List<SnackDto> responseSnackDtoList = new LinkedList<>();
        for (Snack snack: snackService.getAll()) {
            responseSnackDtoList.add(SnackConverter.toDTO(snack));
        }
        return new ResponseEntity<>(responseSnackDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SnackDto> getSnack(@PathVariable Integer id) {
        try {
            Snack searchedSnack = snackService.getById(id);
            return new ResponseEntity<>(SnackConverter.toDTO(searchedSnack), HttpStatus.CREATED);
        } catch (NoSuchElementException e ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SnackDto> postSnack(@RequestBody SnackDto snackDTO) {
        Snack createdSnack = Snack.builder()
                .trademark(snackDTO.getTrademark())
                .type(snackDTO.getType())
                .price(snackDTO.getPrice())
                .build();
        try {
            createdSnack.setSnackProducer(snackProducerService.getByName(snackDTO.getProducer()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Snack responseSnack = snackService.create(createdSnack);
        return new ResponseEntity<>(SnackConverter.toDTO(responseSnack), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SnackDto> putSnack(@RequestBody SnackDto snackDTO, @PathVariable Integer id) {
        Snack updatedSnack = Snack.builder()
                .trademark(snackDTO.getTrademark())
                .type(snackDTO.getType())
                .price(snackDTO.getPrice())
                .build();
        try {
            updatedSnack.setSnackProducer(snackProducerService.getByName(snackDTO.getProducer()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        snackService.updateById(updatedSnack, id);
        snackDTO.setId(id);
        return new ResponseEntity<>(snackDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SnackDto> deleteSnack(@PathVariable Integer id) {
        try {
            snackService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
