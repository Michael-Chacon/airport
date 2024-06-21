package org.vuelosGlobales.systemAdministrator.airport.application;

import org.vuelosGlobales.systemAdministrator.airport.domain.Airport;
import org.vuelosGlobales.systemAdministrator.airport.infrastructure.AirportRepository;

import java.util.List;
import java.util.Optional;

public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void createAirport(Airport airport){
        this.airportRepository.save(airport);
    }

    public void updateAirport(Airport airport){
        this.airportRepository.update(airport);
    }

    public Optional<Airport> getAirportById(int id){
        return this.airportRepository.findById(id);
    }

    public List<Airport> getAllAirports(){
        return this.airportRepository.findAll();
    }

    public void deleteAirport(int id){
        this.airportRepository.delete(id);
    }
}
