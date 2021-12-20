package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.LoaderConverter;
import ua.lviv.iot.models.converter.domain.Loader;
import ua.lviv.iot.models.dto.LoaderDto;
import ua.lviv.iot.service.FullAddressService;
import ua.lviv.iot.service.LoaderService;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/loader")
public class LoaderController {
    @Autowired
    LoaderService loaderService;

    @Autowired
    FullAddressService fullAddressService;

    @GetMapping
    public ResponseEntity<List<LoaderDto>> getLoaderList() {
        List<LoaderDto> responseLoaderDtoList = new LinkedList<>();
        for (Loader loader: loaderService.getAll()) {
            responseLoaderDtoList.add(LoaderConverter.toDTO(loader));
        }
        return new ResponseEntity<>(responseLoaderDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LoaderDto> getLoader(@PathVariable Integer id) {
        try {
            Loader searchedLoader = loaderService.getById(id);
            return new ResponseEntity<>(LoaderConverter.toDTO(searchedLoader), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<LoaderDto> createLoader(@RequestBody LoaderDto loaderDTO) {
        Loader createdLoader = Loader.builder()
                .name(loaderDTO.getName())
                .surname(loaderDTO.getSurname())
                .mobilePhone(loaderDTO.getMobilePhone())
                .company(loaderDTO.getCompany())
                .email(loaderDTO.getEmail())
                .build();
        if (loaderDTO.getFullAddressId() != null) {
            try {
                createdLoader.setFullAddress(fullAddressService.getById(loaderDTO.getFullAddressId()));
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        Loader responseLoader = loaderService.create(createdLoader);
        return new ResponseEntity<>(LoaderConverter.toDTO(responseLoader), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LoaderDto> updateLoader(@RequestBody LoaderDto loaderDTO, @PathVariable Integer id) {
        try {
            Loader updatedLoader = Loader.builder()
                    .name(loaderDTO.getName())
                    .surname(loaderDTO.getSurname())
                    .email(loaderDTO.getEmail())
                    .company(loaderDTO.getCompany())
                    .mobilePhone(loaderDTO.getMobilePhone())
                    .build();
            if (loaderDTO.getFullAddressId() != null) {
                try {
                    updatedLoader.setFullAddress(fullAddressService.getById(loaderDTO.getFullAddressId()));
                } catch (NoSuchElementException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            loaderService.updateById(updatedLoader, id);
            return new ResponseEntity<>(LoaderConverter.toDTO(updatedLoader), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<LoaderDto> deleteLoader(@PathVariable Integer id) {
        try {
            loaderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
