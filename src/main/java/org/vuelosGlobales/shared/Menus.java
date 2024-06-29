package org.vuelosGlobales.shared;

import org.vuelosGlobales.generals.city.adapter.in.CityConsoleAdapter;
import org.vuelosGlobales.generals.city.adapter.out.CityMySQLRepository;
import org.vuelosGlobales.generals.city.application.CityService;
import org.vuelosGlobales.generals.connection.adapter.out.ConnectionMySQLRepository;
import org.vuelosGlobales.generals.connection.application.ConnectionService;
import org.vuelosGlobales.generals.country.adapter.in.CountryConsoleAdapter;
import org.vuelosGlobales.generals.country.adapter.out.CountryMySQLRepository;
import org.vuelosGlobales.generals.country.application.CountryService;
import org.vuelosGlobales.generals.employee.adapter.in.EmployeeConsoleAdap;
import org.vuelosGlobales.generals.employee.adapter.out.EmployeeMySQLRepository;
import org.vuelosGlobales.generals.employee.application.EmployeeService;
import org.vuelosGlobales.generals.manufacturer.adapter.in.ManufacturerConsoleAdap;
import org.vuelosGlobales.generals.manufacturer.adapter.out.ManufacturerMySQLRepository;
import org.vuelosGlobales.generals.manufacturer.application.ManufacturerService;
import org.vuelosGlobales.generals.model.adapter.in.ModelConsoleAdap;
import org.vuelosGlobales.generals.model.adapter.out.ModelMySQLRepository;
import org.vuelosGlobales.generals.model.application.ModelService;
import org.vuelosGlobales.generals.passenger.adapter.out.PassengerMySQLRepo;
import org.vuelosGlobales.generals.passenger.domain.Passenger;
import org.vuelosGlobales.generals.role.adapter.in.RoleConsoleAdap;
import org.vuelosGlobales.generals.role.adapter.out.RoleMySQLRepository;
import org.vuelosGlobales.generals.role.application.RoleService;
import org.vuelosGlobales.generals.status.adapter.in.StatusConsoleAdapter;
import org.vuelosGlobales.generals.status.adapter.out.StatusMySQLRepository;
import org.vuelosGlobales.generals.status.application.StatusService;
import org.vuelosGlobales.generals.trip.adapter.in.TripConsoleAdapter;
import org.vuelosGlobales.generals.trip.adapter.out.TripMySQLRepository;
import org.vuelosGlobales.generals.trip.application.TripService;
import org.vuelosGlobales.generals.tripcrew.adapter.in.TripCrewConsoleAdap;
import org.vuelosGlobales.generals.tripcrew.adapter.out.TripCrewMySQLRepository;
import org.vuelosGlobales.generals.tripcrew.application.TripCrewService;
import org.vuelosGlobales.maintenanceTechnician.revision.adapter.in.RevisionConsoleAdapter;
import org.vuelosGlobales.maintenanceTechnician.revision.adapter.out.RevisionMySQLRepository;
import org.vuelosGlobales.maintenanceTechnician.revision.application.RevisionService;
import org.vuelosGlobales.salesAgent.customer.adapter.in.CustomerConsoleAdapter;
import org.vuelosGlobales.salesAgent.customer.adapter.out.CustomerMySQLRepository;
import org.vuelosGlobales.salesAgent.customer.application.CustomerService;
import org.vuelosGlobales.salesAgent.customer.domain.Customer;
import org.vuelosGlobales.salesAgent.flightRes.adapter.in.FlightResConsoleAdapter;
import org.vuelosGlobales.salesAgent.flightRes.adapter.out.FlightResMySQLRepository;
import org.vuelosGlobales.salesAgent.flightRes.application.FlightResService;
import org.vuelosGlobales.systemAdministrator.airline.adapter.in.AirlineConsoleAdap;
import org.vuelosGlobales.systemAdministrator.airline.adapter.out.AirlineMySQLRepository;
import org.vuelosGlobales.systemAdministrator.airline.application.AirlineService;
import org.vuelosGlobales.systemAdministrator.airport.adapter.in.AirportConsoleAdapter;
import org.vuelosGlobales.systemAdministrator.airport.adapter.out.AirportMySQLRepository;
import org.vuelosGlobales.systemAdministrator.airport.application.AirportService;
import org.vuelosGlobales.systemAdministrator.document.adapter.in.DocumentConsoleAdapter;
import org.vuelosGlobales.systemAdministrator.document.adapter.out.DocumentMySQLRepository;
import org.vuelosGlobales.systemAdministrator.document.application.DocumentService;
import org.vuelosGlobales.systemAdministrator.fare.adapter.in.FareConsoleAdapter;
import org.vuelosGlobales.systemAdministrator.fare.adapter.out.FareMySQLRepository;
import org.vuelosGlobales.systemAdministrator.fare.application.FareService;
import org.vuelosGlobales.systemAdministrator.plane.adapter.in.PlaneConsoleAdapter;
import org.vuelosGlobales.systemAdministrator.plane.adapter.out.PlaneMySQLRepository;
import org.vuelosGlobales.systemAdministrator.plane.application.PlaneService;

public class Menus {
    Console console = new Console();
//    public static void main(String[] args) {

        CountryMySQLRepository countryOut = new CountryMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CountryService countryService = new CountryService(countryOut);
        CountryConsoleAdapter countryIn = new CountryConsoleAdapter(countryService);
//        city
        CityMySQLRepository cityOut = new CityMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CityService cityService = new CityService(cityOut, countryOut);
        CityConsoleAdapter cityIn = new CityConsoleAdapter(cityService);
//        Status
        StatusMySQLRepository statusOut = new StatusMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        StatusService statusService = new StatusService(statusOut);
        StatusConsoleAdapter statusIn = new StatusConsoleAdapter(statusService);
//      manufacturers
        ManufacturerMySQLRepository manufacturerOut = new ManufacturerMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        ManufacturerService manufacturerService = new ManufacturerService(manufacturerOut);
        ManufacturerConsoleAdap manufacturerIn = new ManufacturerConsoleAdap(manufacturerService);
//      Model
        ModelMySQLRepository modelOut = new ModelMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        ModelService modelService = new ModelService(modelOut, manufacturerOut);
        ModelConsoleAdap modelIn = new ModelConsoleAdap(modelService);
//        Airline
        AirlineMySQLRepository airlineOut = new AirlineMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        AirlineService airlineService = new AirlineService(airlineOut);
        AirlineConsoleAdap airlineIn = new AirlineConsoleAdap(airlineService);
//       Role
        RoleMySQLRepository roleOut = new RoleMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        RoleService roleService = new RoleService(roleOut);
        RoleConsoleAdap roleIn = new RoleConsoleAdap(roleService);
//        Airport
        AirportMySQLRepository airportOut = new AirportMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        AirportService airportService = new AirportService(airportOut, cityOut);
        AirportConsoleAdapter airportIn = new AirportConsoleAdapter(airportService);
//        Plane
        PlaneMySQLRepository planeOut = new PlaneMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        PlaneService planeService = new PlaneService(planeOut, statusOut, modelOut, airlineOut);
        PlaneConsoleAdapter planeIn = new PlaneConsoleAdapter(planeService);
        //      Employee
        EmployeeMySQLRepository employeeOut = new EmployeeMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        EmployeeService employeeService = new EmployeeService(employeeOut,roleOut,airlineOut, airportOut);
        EmployeeConsoleAdap employeeIn = new EmployeeConsoleAdap(employeeService);
//        Revision
        RevisionMySQLRepository revisionOut = new RevisionMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        RevisionService revisionService = new RevisionService(revisionOut,planeOut, airlineOut, employeeOut);
        RevisionConsoleAdapter revisionIn = new RevisionConsoleAdapter(revisionService);
//      Connection
        ConnectionMySQLRepository connOut = new ConnectionMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        ConnectionService connectionService = new ConnectionService(connOut);
//      Trip connection
        TripMySQLRepository tripOut = new TripMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        TripService tripService = new TripService(tripOut, airportOut,planeOut, connOut);
        TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
//        tripCrew
        TripCrewMySQLRepository tripCrewOut = new TripCrewMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        TripCrewService tripCrewService = new TripCrewService(tripCrewOut, airlineOut, employeeOut, connOut, tripOut);
        TripCrewConsoleAdap tripCrewIn = new TripCrewConsoleAdap(tripCrewService);
//      Fare
        FareMySQLRepository fareOut = new FareMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        FareService fareService = new FareService(fareOut);
        FareConsoleAdapter fareIn = new FareConsoleAdapter(fareService);

        DocumentMySQLRepository documentOut = new DocumentMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        DocumentService documentService = new DocumentService(documentOut);
        DocumentConsoleAdapter documentIn = new DocumentConsoleAdapter(documentService);
        // Customer
        CustomerMySQLRepository customerOut = new CustomerMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CustomerService customerService = new CustomerService(customerOut, documentOut);
        CustomerConsoleAdapter customerIn = new CustomerConsoleAdapter(customerService);

        PassengerMySQLRepo passengerMySQLRepo = new PassengerMySQLRepo(Constants.URL, Constants.USER, Constants.PASSWORD);
        // Reservas
        FlightResMySQLRepository flightOut = new FlightResMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        FlightResService flightResService = new FlightResService(flightOut, customerOut,tripOut, fareOut, passengerMySQLRepo,documentOut);
        FlightResConsoleAdapter flightResIn = new FlightResConsoleAdapter(flightResService);
//        tripCrewIn.crew();
//        tripConsoleAdapter.crudTrip();
//        employeeIn.crudEmployee();
//        revisionIn.crudRevision();
//        planeIn.crudPlane();
//        airportIn.crudAirport();
//        roleIn.crudRole();
//        airlineIn.crudAirline();
//        modelIn.crudModel();
//        manufacturerIn.crudManufacturer();
//        statusIn.crudStatus();
//        cityIn.crudCity();
//        countryIn.crudCountry();



    public void MenuPrincipal(){
        menuAdmin: while(true){
            System.out.println("======================================");
            System.out.println("           MENÚ DE ROLES               ");
            System.out.println("======================================");
            System.out.println("\t1. Administrador del sistema");
            System.out.println("\t2. Agente de ventas");
            System.out.println("\t3. Técnico en mantenimiento");
            System.out.println("\t5. Salir");
            System.out.println("======================================");
            int choise = console.readInt("Seleccione un opción: ");

            if (choise == 1){
                menuSystemAdmin();
            }else if (choise == 2){
                optionsSalesAgent();
            } else if (choise == 3) {
                revisionIn.crudRevision();
            } else if (choise == 5) {
                break menuAdmin;
            }
        }
    }

    public void menuSystemAdmin(){
        CuadroDeTexto.dibujarCuadroDeTexto("Opciones de administrador del sistema", "*");
        menuAdmin: while(true){
            System.out.println("======================================");
            System.out.println("            MENÚ PRINCIPAL           ");
            System.out.println("======================================");
            System.out.println("\t1.  Gestionar los aviones");
            System.out.println("\t2.  Gestionar los aeropuertos");
            System.out.println("\t3.  Gestionar aerolíneas");
            System.out.println("\t4.  Gestionar los trayectos y sus escalas");
            System.out.println("\t5.  Gestionar empleados");
            System.out.println("\t6.  Gestionar tripulación");
            System.out.println("\t7.  Gestionar información de roles de la tripulación");
            System.out.println("\t8.  Gestionar las tarifas de vuelo");
            System.out.println("\t9.  Gestionar documentos");
            System.out.println("\t10. Gestionar información de fabricantes de aviones");
            System.out.println("\t11. Gestionar información de modelos de avión");
            System.out.println("\t12. Gestionar información de estados de un avión");
            System.out.println("\t13. Gestionar información de países");
            System.out.println("\t14. Gestionar información de ciudades");
            System.out.println("\t15. Regresar al menú principal");
            System.out.println("======================================");
            System.out.print("Seleccione una opción: ");

            int choise = console.readInt("Seleccione un opción: ");

            if (choise == 1){
                planeIn.crudPlane();
            } else if (choise == 2){
                airportIn.crudAirport();
            } else if (choise == 3){
                airlineIn.crudAirline();
            } else if (choise == 4) {
                tripConsoleAdapter.crudTrip();
            } else if (choise == 13) {
                countryIn.crudCountry();
            } else if (choise == 14) {
                cityIn.crudCity();
            } else if (choise == 10) {
                manufacturerIn.crudManufacturer();
            } else if (choise == 11) {
                modelIn.crudModel();
            } else if (choise == 12) {
                statusIn.crudStatus();
            } else if (choise == 7) {
                roleIn.crudRole();
            } else if (choise == 15) {
                break menuAdmin;
            } else if (choise == 5) {
                employeeIn.crudEmployee();
            } else if (choise == 6) {
                tripCrewIn.crew();
            } else if (choise == 8) {
                fareIn.crudFares();
            } else if (choise == 9) {
                documentIn.crudDocument();
            } else {
                System.out.println("Opción incorrecta");
            }
        }
    }

    public void optionsSalesAgent(){
        salesAgent: while (true){
            System.out.println("+-------------------------------+");
            System.out.println("|     MENU DE AGENTE DE VENTAS  |");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-2d. %-24s |\n", 1, "Gestionar los clientes");
            System.out.printf("| %-2d. %-24s |\n", 2, "Gestionar reservas");
            System.out.printf("| %-2d. %-24s |\n", 3, "Regresar");
            System.out.println("+-----------------------------+");
            int choise = console.readInt("Seleccione una opción: ");
            if (choise == 1){
                customerIn.crudCustomer();
            } else if (choise == 2) {
                flightResIn.crudFlightRes();
            } else if (choise == 3) {
                break salesAgent;
            }else {
                System.out.println("Opción inválida");
            }
        }
    }

}
