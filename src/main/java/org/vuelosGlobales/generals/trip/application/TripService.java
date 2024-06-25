package org.vuelosGlobales.generals.trip.application;

import org.vuelosGlobales.generals.connection.domain.Connections;
import org.vuelosGlobales.generals.connection.infrastructure.ConnectionRepository;
import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.generals.trip.infrastructure.TripRepository;
import org.vuelosGlobales.systemAdministrator.airport.domain.AirportCityDTO;
import org.vuelosGlobales.systemAdministrator.airport.infrastructure.AirportRepository;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.systemAdministrator.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class TripService {
    private final TripRepository tripRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final ConnectionRepository connectionRepository;

    public TripService(TripRepository tripRepository, AirportRepository airportRepository, PlaneRepository planeRepository, ConnectionRepository connectionRepository) {
        this.tripRepository = tripRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.connectionRepository = connectionRepository;
    }

    public int createTrip(Trip trip){
       return this.tripRepository.save(trip);
    }

    public void updateTrip(Trip trip){
        this.tripRepository.update(trip);
    }

    public Optional<Trip> getTripById(int id){
        return this.tripRepository.findById(id);
    }

    public List<Trip> getAllTrips(){
        return this.tripRepository.findAll();
    }

    public void deleteTrip(int id){
        this.tripRepository.delete(id);
    }

    public List<AirportCityDTO> getAllAirportCity(){
        return this.airportRepository.findAllAirportCity();
    }

    public Optional<AirportCityDTO> getAirportCityById(String id){
        return this.airportRepository.findAirportCityById(id);
    }

    public List<TripAirportDTO> getAllTripAirp(){
        return this.tripRepository.findAllTripAirport();
    }

    public Optional<TripAirportDTO> getTripAripById(int id){
        return this.tripRepository.findTripAirportById(id);
    }

    public List<PlaneStMdDTO> getAllPlanesInfo(){
        return this.planeRepository.findAllPlaneStMd(false, 0);
    }

    public Optional<PlaneStMdDTO>getPlaneById(int id){
        return this.planeRepository.findPlaneStMdById(id);
    }

    public void createConnecion(Connections connections){
        this.connectionRepository.save(connections);
    }

}
