package org.vuelosGlobales.generals.city.infrastructure;

import org.vuelosGlobales.generals.city.domain.City;
import org.vuelosGlobales.generals.city.domain.CityCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    void save(City city);
    void update(City city);
    Optional<City> findById(String id);
    List<City> findAll();
    void delete(String id);
    List<CityCountryDTO> cityWithCountry();
}
