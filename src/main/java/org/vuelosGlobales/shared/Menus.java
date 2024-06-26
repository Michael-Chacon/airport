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
import org.vuelosGlobales.systemAdministrator.airline.adapter.in.AirlineConsoleAdap;
import org.vuelosGlobales.systemAdministrator.airline.adapter.out.AirlineMySQLRepository;
import org.vuelosGlobales.systemAdministrator.airline.application.AirlineService;
import org.vuelosGlobales.systemAdministrator.airport.adapter.in.AirportConsoleAdapter;
import org.vuelosGlobales.systemAdministrator.airport.adapter.out.AirportMySQLRepository;
import org.vuelosGlobales.systemAdministrator.airport.application.AirportService;
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
        CuadroDeTexto.dibujarCuadroDeTexto("Actores del sistema", "*");
        menuAdmin: while(true){
            System.out.println("\t1. Administrador del sistema");
            System.out.println("\t2. Agente de ventas");
            System.out.println("\t3. Técnico en mantenimiento");
            System.out.println("\t4. Cliente");
            System.out.println("\t5. Salir");
            int choise = console.readInt("Seleccione un apción: ");

            if (choise == 1){
                menuSystemAdmin();
            }else if (choise == 2){
                System.out.println("nada");
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
            System.out.println("\t1. Administrar los aviones");
            System.out.println("\t2. Administrar los aeropuertos");
            System.out.println("\t3. Gestionar los trayectos y sus escalas");
            System.out.println("\t4. Gestionar las tarifas");
            System.out.println("\t13 Gestionar de empleados");
            System.out.println("\t5. Tipo de documento");
            System.out.println("\t6. Gestionar info de paises");
            System.out.println("\t7. Gestionar info de las ciudades");
            System.out.println("\t8. Gestionar info de los fabricantes de aviones");
            System.out.println("\t9. Gestionar info de los modelos de avión");
            System.out.println("\t10. Gestionar info de los estados de un avión");
            System.out.println("\t11. Gestionar info de los roles de la tripulación");
            System.out.println("\t12. Regresar la menu principal");
            int choise = console.readInt("Seleccione un apción: ");

            if (choise == 1){
                planeIn.crudPlane();
            }else if (choise == 2){
                airportIn.crudAirport();
            } else if (choise == 3) {
                tripConsoleAdapter.crudTrip();
            } else if (choise == 6) {
                countryIn.crudCountry();
            } else if (choise == 7) {
                cityIn.crudCity();
            } else if (choise == 8) {
                manufacturerIn.crudManufacturer();
            } else if (choise == 9) {
                modelIn.crudModel();
            } else if (choise == 10) {
                statusIn.crudStatus();
            } else if (choise == 11) {
                roleIn.crudRole();
            } else if (choise == 12) {
                break menuAdmin;
            } else if (choise == 13) {
                employeeIn.crudEmployee();
            } else {
                System.out.println("Opción incorrecta");
            }
        }
    }

}
