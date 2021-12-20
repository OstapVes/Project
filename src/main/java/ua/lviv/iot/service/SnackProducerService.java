package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.SnackProducerRepository;
import ua.lviv.iot.models.converter.domain.SnackProducer;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SnackProducerService {
    @Autowired
    SnackProducerRepository snackProducerRepository;

    public List<SnackProducer> getAll() {
        return snackProducerRepository.findAll();
    }

    public SnackProducer getById(Integer id) {
        return snackProducerRepository.findById(id).get();
    }

    public SnackProducer getByName(String name) {
        SnackProducer foundSnackProducer = snackProducerRepository.findByName(name);
        if (foundSnackProducer == null) {
            throw new NoSuchElementException();
        }
        return foundSnackProducer;
    }

    @Transactional
    public SnackProducer create(SnackProducer snackProducer) {
        return snackProducerRepository.save(snackProducer);
    }

    @Transactional
    public void updateById(SnackProducer snackProducer, Integer id) {
        SnackProducer updatedProducer = snackProducerRepository.findById(id).get();
        updatedProducer.setName(snackProducer.getName());
        updatedProducer.setEmail(snackProducer.getEmail());
        updatedProducer.setMobilePhone(snackProducer.getMobilePhone());
        updatedProducer.setFullAddress(snackProducer.getFullAddress());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!snackProducerRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        snackProducerRepository.deleteById(id);
    }
}
