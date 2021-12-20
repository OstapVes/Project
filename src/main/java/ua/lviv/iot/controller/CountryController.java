package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.converter.CountryConverter;
import ua.lviv.iot.models.converter.domain.Country;
import ua.lviv.iot.models.dto.CountryDto;
import ua.lviv.iot.service.CountryService;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDto>> getCountryList() {
        List<CountryDto> responseCountryDtoList = new LinkedList<>();
        for (Country country: countryService.getAll()) {
            responseCountryDtoList.add(CountryConverter.toDTO(country));
        }
        return new ResponseEntity<>(responseCountryDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer id) {
        try {
            Country searchedCountry = countryService.getById(id);
            CountryDto responseCountryDto = CountryConverter.toDTO(searchedCountry);
            return new ResponseEntity<>(responseCountryDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CountryDto> postCountry(@RequestBody CountryDto countryDto) {
        Country createdCountry = Country.builder()
                .name(countryDto.getName())
                .build();
        Country responseCountry = countryService.create(createdCountry);
        return new ResponseEntity<>(CountryConverter.toDTO(responseCountry), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CountryDto> putCountry(@PathVariable Integer id, @RequestBody CountryDto countryDto) {
        try {
            Country updatedCountryValues = Country.builder()
                    .name(countryDto.getName())
                    .build();
            countryService.updateById(updatedCountryValues, id);
            countryDto.setId(id);
            return new ResponseEntity<>(countryDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> deleteCountry(@PathVariable Integer id) {
        try {
            countryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
