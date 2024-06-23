package org.vuelosGlobales.generals.trip.infrastructure;

import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;

import java.util.List;
import java.util.Optional;

public interface TripRepository {

    void save(Trip trip);
    void update(Trip trip);
    Optional<Trip> findById(int id);
    List<Trip> findAll();
    void delete(int id);
    List<TripAirportDTO> findAllTripAirport();
    Optional<TripAirportDTO> findTripAirportById(int id);
}
