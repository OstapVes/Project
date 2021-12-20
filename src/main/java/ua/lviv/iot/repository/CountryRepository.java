package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.converter.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByName(String name);
}

