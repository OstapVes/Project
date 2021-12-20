package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.FullAddressConverter;
import ua.lviv.iot.models.converter.domain.FullAddress;
import ua.lviv.iot.models.dto.FullAddressDto;
import ua.lviv.iot.service.CityService;
import ua.lviv.iot.service.FullAddressService;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/address")
public class FullAddressController {
    @Autowired
    FullAddressService fullAddressService;

    @Autowired
    CityService cityService;

    @GetMapping
    public ResponseEntity<List<FullAddressDto>> getAddressList() {
        List<FullAddressDto> responseFullAddressDtoList = new LinkedList<>();
        for (FullAddress fullAddress: fullAddressService.getAll()) {
            responseFullAddressDtoList.add(FullAddressConverter.toDTO(fullAddress));
        }
        return new ResponseEntity<>(responseFullAddressDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FullAddressDto> getAddress(@PathVariable Integer id) {
        try {
            FullAddress searchedFullAddress = fullAddressService.getById(id);
            return new ResponseEntity<>(FullAddressConverter.toDTO(searchedFullAddress), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FullAddressDto> postAddress(@RequestBody FullAddressDto addressDto) {
        try {
            FullAddress createdFullAddress = FullAddress.builder()
                    .street(addressDto.getStreet())
                    .number(addressDto.getNumber())
                    .city(cityService.getById(addressDto.getCityId()))
                    .build();
            FullAddress responseFullAddress = fullAddressService.create(createdFullAddress);
            return new ResponseEntity<>(FullAddressConverter.toDTO(responseFullAddress), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<FullAddressDto> putAddress(@RequestBody FullAddressDto addressDTO, @PathVariable Integer id) {
        try {
            FullAddress updatedFullAddress = FullAddress.builder()
                    .street(addressDTO.getStreet())
                    .number(addressDTO.getNumber())
                    .city(cityService.getById(addressDTO.getCityId()))
                    .build();
            fullAddressService.updateById(updatedFullAddress, id);
            addressDTO.setId(id);
            addressDTO.setCity(updatedFullAddress.getCity().getName());
            addressDTO.setCountry(updatedFullAddress.getCity().getCountry().getName());
            return new ResponseEntity<>(addressDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<FullAddressDto> deleteAddress(@PathVariable Integer id) {
        try {
            fullAddressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
