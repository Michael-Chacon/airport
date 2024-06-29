package org.vuelosGlobales.generals.passenger.infrastructure;

import org.vuelosGlobales.generals.passenger.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository {
    void save(Passenger passenger);
    void update(Passenger passenger);
    Optional<Passenger> findById(int id);
    List<Passenger> findAll();
    void delete(int id);
}
