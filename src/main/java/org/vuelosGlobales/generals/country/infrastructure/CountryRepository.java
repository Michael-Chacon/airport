package org.vuelosGlobales.generals.country.infrastructure;

import org.vuelosGlobales.generals.country.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    void save(Country country);
    void update(Country country);
    Optional<Country> findById(String id);
    List<Country> findAll();
    void delete(String id);

}
