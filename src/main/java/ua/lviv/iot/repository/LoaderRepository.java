package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.converter.domain.Loader;

@Repository
public interface LoaderRepository extends JpaRepository<Loader, Integer> {
}
