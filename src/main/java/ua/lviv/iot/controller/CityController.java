package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.CityConverter;
import ua.lviv.iot.models.converter.domain.City;
import ua.lviv.iot.models.dto.CityDto;
import ua.lviv.iot.service.CityService;
import ua.lviv.iot.service.CountryService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/city")
public class CityController {
    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getCityList() {
        List<CityDto> responseCityDtoList = new ArrayList<>();
        for (City city: cityService.getAll()) {
            responseCityDtoList.add(CityConverter.toDTO(city));
        }
        return new ResponseEntity<>(responseCityDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable Integer id) {
        try {
            City searchedCity = cityService.getById(id);
            return new ResponseEntity<>(CityConverter.toDTO(searchedCity), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CityDto> postCity(@RequestBody CityDto cityDto) {
        City createdCity = City.builder()
                .name(cityDto.getName())
                .region(cityDto.getRegion())
                .country(countryService.getByName(cityDto.getCountry()))
                .build();
        City responseCity = cityService.create(createdCity);
        return new ResponseEntity<>(CityConverter.toDTO(responseCity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CityDto> putCity(@RequestBody CityDto cityDto, @PathVariable Integer id) {
        try {
            City updatedCityValues = City.builder()
                    .name(cityDto.getName())
                    .region(cityDto.getRegion())
                    .country(countryService.getByName(cityDto.getCountry()))
                    .build();
            cityService.updateById(updatedCityValues, id);
            cityDto.setId(id);
            return new ResponseEntity<>(cityDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CityDto> deleteCity(@PathVariable Integer id) {
        try {
            cityService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
