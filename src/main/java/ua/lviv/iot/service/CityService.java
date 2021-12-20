package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.models.converter.domain.City;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City getById(Integer id) {
        return cityRepository.findById(id).get();
    }

    @Transactional
    public City create(City city) {
        return cityRepository.save(city);
    }

    @Transactional
    public void updateById(City city, Integer id) {
        City updatedCity = cityRepository.findById(id).get();
        updatedCity.setCountry(city.getCountry());
        updatedCity.setName(city.getName());
        updatedCity.setRegion(city.getRegion());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!cityRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        cityRepository.deleteById(id);
    }
}
