package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.LoaderRepository;
import ua.lviv.iot.models.converter.domain.Loader;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoaderService {
    @Autowired
    LoaderRepository loaderRepository;

    public List<Loader> getAll() {
        return loaderRepository.findAll();
    }

    public Loader getById(Integer id) {
        return loaderRepository.findById(id).get();
    }

    @Transactional
    public Loader create(Loader loader) {
        return loaderRepository.save(loader);
    }

    @Transactional
    public void updateById(Loader loader, Integer id) {
        Loader updatedLoader = loaderRepository.findById(id).get();
        updatedLoader.setName(loader.getName());
        updatedLoader.setSurname(loader.getSurname());
        updatedLoader.setCompany(loader.getCompany());
        updatedLoader.setEmail(loader.getEmail());
        updatedLoader.setMobilePhone(loader.getMobilePhone());
        updatedLoader.setFullAddress(loader.getFullAddress());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!loaderRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        loaderRepository.deleteById(id);
    }
}
