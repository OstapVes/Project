package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.SnackMachineRepository;
import ua.lviv.iot.models.converter.domain.SnackMachine;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SnackMachineService {
    @Autowired
    SnackMachineRepository snackMachineRepository;

    public List<SnackMachine> getAll() {
        return snackMachineRepository.findAll();
    }

    @Transactional
    public SnackMachine getById(Integer id) {
        return snackMachineRepository.findById(id).get();
    }

    @Transactional
    public SnackMachine create(SnackMachine snackMachine) {
        return snackMachineRepository.save(snackMachine);
    }

    @Transactional
    public void updateById(SnackMachine snackMachine, Integer id) {
        SnackMachine updatedSnackMachine = snackMachineRepository.findById(id).get();
        updatedSnackMachine.setMachineService(snackMachine.getMachineService());
        updatedSnackMachine.setFullAddress(snackMachine.getFullAddress());
        updatedSnackMachine.setMachineModel(snackMachine.getMachineModel());
        updatedSnackMachine.setMachineProducer(snackMachine.getMachineProducer());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!snackMachineRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        snackMachineRepository.deleteById(id);
    }
}
