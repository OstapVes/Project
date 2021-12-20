package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.repository.MachineServiceRepository;
import ua.lviv.iot.models.converter.domain.MachineService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MachineServiceService {
    @Autowired
    MachineServiceRepository machineServiceRepository;

    public List<MachineService> getAll() {
        return machineServiceRepository.findAll();
    }

    public MachineService getById(Integer id) {
        return machineServiceRepository.findById(id).get();
    }

    @Transactional
    public MachineService create(MachineService machineService) {
        return machineServiceRepository.save(machineService);
    }

    @Transactional
    public void updateById(MachineService machineService, Integer id) {
        MachineService updatedMachineService = machineServiceRepository.findById(id).get();
        updatedMachineService.setLastCashGathering(machineService.getLastCashGathering());
        updatedMachineService.setGatheredCash(machineService.getGatheredCash());

        updatedMachineService.setLastLoad(machineService.getLastLoad());

        updatedMachineService.setLastCoinLoad(machineService.getLastCoinLoad());
        updatedMachineService.setLoadedCoins(machineService.getLoadedCoins());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!machineServiceRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        machineServiceRepository.deleteById(id);
    }
}
