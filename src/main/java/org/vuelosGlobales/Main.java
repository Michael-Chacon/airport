package org.vuelosGlobales;

import org.vuelosGlobales.generals.connection.adapter.out.ConnectionMySQLRepository;
import org.vuelosGlobales.generals.passenger.adapter.out.PassengerMySQLRepo;
import org.vuelosGlobales.generals.trip.adapter.out.TripMySQLRepository;
import org.vuelosGlobales.salesAgent.customer.adapter.out.CustomerMySQLRepository;
import org.vuelosGlobales.salesAgent.flightRes.adapter.in.FlightResConsoleAdapter;
import org.vuelosGlobales.salesAgent.flightRes.adapter.out.FlightResMySQLRepository;
import org.vuelosGlobales.salesAgent.flightRes.application.FlightResService;
import org.vuelosGlobales.shared.Constants;
import org.vuelosGlobales.systemAdministrator.document.adapter.out.DocumentMySQLRepository;
import org.vuelosGlobales.systemAdministrator.fare.adapter.out.FareMySQLRepository;

public class Main {
    public static void main(String[] args) {
        CustomerMySQLRepository customerOut = new CustomerMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        TripMySQLRepository tripOut = new TripMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        ConnectionMySQLRepository connOut = new ConnectionMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        PassengerMySQLRepo passengerMySQLRepo = new PassengerMySQLRepo(Constants.URL, Constants.USER, Constants.PASSWORD);
        FareMySQLRepository fareOut = new FareMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        DocumentMySQLRepository documentOut = new DocumentMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);

        FlightResMySQLRepository flightResMySQLRepository = new FlightResMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        FlightResService flightResService = new FlightResService(flightResMySQLRepository, customerOut, tripOut, connOut, fareOut, passengerMySQLRepo, documentOut);
        FlightResConsoleAdapter fligresIn = new FlightResConsoleAdapter(flightResService);

        fligresIn.crudFlightRes();
    /*    Menus menus = new Menus();
        menus.MenuPrincipal();
        *
     */
    }
}