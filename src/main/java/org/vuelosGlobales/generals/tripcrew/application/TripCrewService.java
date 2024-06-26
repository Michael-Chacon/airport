package org.vuelosGlobales.generals.tripcrew.application;

import org.vuelosGlobales.generals.connection.application.ConnectionService;
import org.vuelosGlobales.generals.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.generals.connection.domain.Connections;
import org.vuelosGlobales.generals.connection.infrastructure.ConnectionRepository;
import org.vuelosGlobales.generals.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.generals.employee.infrastructure.EmployeeRepository;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.generals.trip.infrastructure.TripRepository;
import org.vuelosGlobales.generals.tripcrew.domain.TripCrew;
import org.vuelosGlobales.generals.tripcrew.domain.TripCrewInfoDTO;
import org.vuelosGlobales.generals.tripcrew.infrastructure.TripCrewRepository;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.systemAdministrator.airline.infrastructure.AirlineRepository;

import java.util.List;
import java.util.Optional;

public class TripCrewService {
    private final TripCrewRepository tripCrewRepository;
    private final AirlineRepository airlineRepository;
    private final EmployeeRepository employeeRepository;
    private final ConnectionRepository connectionRepository;
    private final TripRepository tripRepository;

    public TripCrewService(TripCrewRepository tripCrewRepository, AirlineRepository airlineRepository, EmployeeRepository employeeRepository, ConnectionRepository connectionRepository, TripRepository tripRepository) {
        this.tripCrewRepository = tripCrewRepository;
        this.airlineRepository = airlineRepository;
        this.employeeRepository = employeeRepository;
        this.connectionRepository = connectionRepository;
        this.tripRepository = tripRepository;
    }

    public void saveTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }

    public List<TripCrewInfoDTO> getDataTripCrewByConn(int idConn){
        return tripCrewRepository.showCrewByConn(idConn);
    }

    public List<Airline> getAllAirlines(){
        return airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(int id){
        return airlineRepository.findById(id);
    }

    public List<EmployeeRelationshipDTO> getAllEmployees(boolean filter, int id){
        return employeeRepository.findAllEmployeesInfo(filter, id);
    }

    public Optional<EmployeeRelationshipDTO> getEmployecById(String id){
        return employeeRepository.findEmployeeInfoById(id);
    }

    public List<TripAirportDTO> getAllTripAirp(){
        return tripRepository.findAllTripAirport();
    }
    public Optional<TripAirportDTO> getTripAirpById(int id){
        return tripRepository.findTripAirportById(id);
    }

    public List<ConnInfoDTO> getAllConectionByTrip(int idTrip){
        return connectionRepository.findAllConnByTrip(idTrip);
    }

    public Optional<Connections> getConnectionById(int id){
        return connectionRepository.findById(id);
    }
}
