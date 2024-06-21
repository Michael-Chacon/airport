package org.vuelosGlobales.generals.city.infrastructure;

import org.vuelosGlobales.generals.city.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    void save(City city);
    void update(City city);
    Optional<City> findById(String id);
    List<City> findAll();
    void delete(String id);
}
