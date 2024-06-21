package org.vuelosGlobales.systemAdministrator.airport.infrastructure;

import org.vuelosGlobales.systemAdministrator.airport.domain.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {
    void save(Airport airport);
    void update(Airport airport);
    Optional<Airport> findById(int id);
    List<Airport> findAll();
    void delete(int id);
}
