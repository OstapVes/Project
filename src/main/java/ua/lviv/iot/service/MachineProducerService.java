package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.MachineProducerRepository;
import ua.lviv.iot.models.converter.domain.MachineProducer;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MachineProducerService {
    @Autowired
    MachineProducerRepository machineProducerRepository;

    public List<MachineProducer> getAll() {
        return machineProducerRepository.findAll();
    }

    public MachineProducer getById(Integer id) {
        return machineProducerRepository.findById(id).get();
    }

    @Transactional
    public MachineProducer create(MachineProducer machineProducer) {
        return machineProducerRepository.save(machineProducer);
    }

    @Transactional
    public void updateById(MachineProducer machineProducer, Integer id) {
        MachineProducer updatedMachineProducer = machineProducerRepository.findById(id).get();
        updatedMachineProducer.setName(machineProducer.getName());
        updatedMachineProducer.setMobilePhone(machineProducer.getMobilePhone());
        updatedMachineProducer.setEmail(machineProducer.getEmail());
        updatedMachineProducer.setFullAddress(machineProducer.getFullAddress());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!machineProducerRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        machineProducerRepository.deleteById(id);
    }
}
