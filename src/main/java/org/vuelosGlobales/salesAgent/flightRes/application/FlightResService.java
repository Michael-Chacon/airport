package org.vuelosGlobales.salesAgent.flightRes.application;

import org.vuelosGlobales.generals.connection.infrastructure.ConnectionRepository;
import org.vuelosGlobales.generals.passenger.domain.Passenger;
import org.vuelosGlobales.generals.passenger.infrastructure.PassengerRepository;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.generals.trip.infrastructure.TripRepository;
import org.vuelosGlobales.salesAgent.customer.domain.Customer;
import org.vuelosGlobales.salesAgent.customer.infrastructure.CustomerRepository;
import org.vuelosGlobales.salesAgent.flightRes.domain.FlightRes;
import org.vuelosGlobales.salesAgent.flightRes.infrastructure.FlightResRepository;
import org.vuelosGlobales.systemAdministrator.fare.domain.Fare;
import org.vuelosGlobales.systemAdministrator.fare.infrastructure.FareRepository;
import java.util.List;
import java.util.Optional;

public class FlightResService {
    private final FlightResRepository flightResRepository;
    private final CustomerRepository customerRepository;
    private final TripRepository tripRepository;
    private final ConnectionRepository connectionRepository;
    private final FareRepository fareRepository;
    private final PassengerRepository passengerRepository;

    public FlightResService(FlightResRepository flightResRepository, CustomerRepository customerRepository, TripRepository tripRepository, ConnectionRepository connectionRepository, FareRepository fareRepository, PassengerRepository passengerRepository) {
        this.flightResRepository = flightResRepository;
        this.customerRepository = customerRepository;
        this.tripRepository = tripRepository;
        this.connectionRepository = connectionRepository;
        this.fareRepository = fareRepository;
        this.passengerRepository = passengerRepository;
    }

    //Mostrar todos los vuelos
    public List<TripAirportDTO> showAllTrips(){
        return this.tripRepository.findAllTripAirport();
    }
    // Obtener un solo vuelo
    public Optional<TripAirportDTO> showOneTrip(int idTrip){
        return this.tripRepository.findTripAirportById(idTrip);
    }

    public int createFlightBooking(FlightRes reservation){
        return this.flightResRepository.save(reservation);
    }

    public Optional<FlightRes> showOneFlightBooking(int idReserva){
        return this.flightResRepository.findById(idReserva);
    }

    public List<Customer> showAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Optional<Customer> showOneCustomer(int idCustomer){
        return this.customerRepository.findById(idCustomer);
    }

    public List<Fare> showAllFares(){
        return this.fareRepository.findAll();
    }

    public Optional<Fare> showOneFare(int idFare){
        return this.fareRepository.findById(idFare);
    }

    public void createPassenger(Passenger passenger){
        this.passengerRepository.save(passenger);
    }

    public int showAmountSeats(int idTrip){
        return flightResRepository.findPlaneSeats(idTrip);
    }
}
