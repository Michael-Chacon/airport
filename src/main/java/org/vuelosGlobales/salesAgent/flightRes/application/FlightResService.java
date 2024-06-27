package org.vuelosGlobales.salesAgent.flightRes.application;

import org.vuelosGlobales.generals.connection.domain.Connections;
import org.vuelosGlobales.generals.connection.infrastructure.ConnectionRepository;
import org.vuelosGlobales.generals.trip.infrastructure.TripRepository;
import org.vuelosGlobales.salesAgent.customer.infrastructure.CustomerRepository;
import org.vuelosGlobales.salesAgent.flightRes.infrastructure.FlightResRepository;

public class FlightResService {
    private final FlightResRepository flightResRepository;
    private final CustomerRepository customerRepository;
    private final TripRepository tripRepository;
    private final ConnectionRepository connectionRepository;


    public FlightResService(FlightResRepository flightResRepository, CustomerRepository customerRepository, TripRepository tripRepository, ConnectionRepository connectionRepository) {
        this.flightResRepository = flightResRepository;
        this.customerRepository = customerRepository;
        this.tripRepository = tripRepository;
        this.connectionRepository = connectionRepository;
    }
}
