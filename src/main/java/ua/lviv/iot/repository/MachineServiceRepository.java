package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.converter.domain.MachineService;

@Repository
public interface MachineServiceRepository extends JpaRepository<MachineService, Integer> {
}
