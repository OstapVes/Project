package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.SnackRepository;
import ua.lviv.iot.models.converter.domain.Snack;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SnackService {
    @Autowired
    SnackRepository snackRepository;

    public List<Snack> getAll() {
        return snackRepository.findAll();
    }

    public Snack getById(Integer id) {
        return snackRepository.findById(id).get();
    }

    @Transactional
    public Snack create(Snack snack) {
        return snackRepository.save(snack);
    }

    @Transactional
    public void updateById(Snack snack, Integer id) {
        Snack updatedSnack = snackRepository.findById(id).get();
        updatedSnack.setTrademark(snack.getTrademark());
        updatedSnack.setType(snack.getType());
        updatedSnack.setPrice(snack.getPrice());
        updatedSnack.setSnackProducer(snack.getSnackProducer());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!snackRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        snackRepository.deleteById(id);
    }
}
