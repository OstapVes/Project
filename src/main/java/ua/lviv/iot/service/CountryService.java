package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.CountryRepository;
import ua.lviv.iot.models.converter.domain.Country;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Integer id) {
        return countryRepository.findById(id).get();
    }

    public Country getByName(String name) {
        return countryRepository.findByName(name);
    }

    @Transactional
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public void updateById(Country country, Integer id) {
        Country updatedCountry = countryRepository.findById(id).get();
        updatedCountry.setName(country.getName());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!countryRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        countryRepository.deleteById(id);
    }
}
