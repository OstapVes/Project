package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.MachineModelRepository;
import ua.lviv.iot.models.converter.domain.MachineModel;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MachineModelService {
    @Autowired
    MachineModelRepository machineModelRepository;

    public List<MachineModel> getAll() {
        return machineModelRepository.findAll();
    }

    public MachineModel getByModel(String model) {
        return machineModelRepository.findById(model).get();
    }

    @Transactional
    public MachineModel create(MachineModel machineModel) {
        return machineModelRepository.save(machineModel);
    }

    @Transactional
    public void updateByModel(MachineModel machineModel, String model) {
        MachineModel updatedModel = machineModelRepository.findById(model).get();
        updatedModel.setCapacity(machineModel.getCapacity());
        updatedModel.setElectricityConsumption(machineModel.getElectricityConsumption());
    }

    @Transactional
    public void deleteByModel(String model) {
        if (!machineModelRepository.existsById(model)) {
            throw new NoSuchElementException();
        }
        machineModelRepository.deleteById(model);
    }
}
