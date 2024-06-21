package org.vuelosGlobales.generals.manufacturer.infrastructure;

import org.vuelosGlobales.generals.manufacturer.domain.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository {
    void save(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    Optional<Manufacturer> findById(int id);
    List<Manufacturer> findAll();
    void delete(int id);
}
