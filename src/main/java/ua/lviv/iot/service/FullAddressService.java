package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.FullAddressRepository;
import ua.lviv.iot.models.converter.domain.FullAddress;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FullAddressService {
    @Autowired
    FullAddressRepository fullAddressRepository;

    public List<FullAddress> getAll() {
        return fullAddressRepository.findAll();
    }

    public FullAddress getById(Integer id) {
        return fullAddressRepository.findById(id).get();
    }

    @Transactional
    public FullAddress create(FullAddress fullAddress) {
        return fullAddressRepository.save(fullAddress);
    }

    @Transactional
    public void updateById(FullAddress fullAddress, Integer id) {
        FullAddress updatedFullAddress = fullAddressRepository.findById(id).get();
        updatedFullAddress.setNumber(fullAddress.getNumber());
        updatedFullAddress.setStreet(fullAddress.getStreet());
        updatedFullAddress.setCity(fullAddress.getCity());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!fullAddressRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        fullAddressRepository.deleteById(id);
    }
}
