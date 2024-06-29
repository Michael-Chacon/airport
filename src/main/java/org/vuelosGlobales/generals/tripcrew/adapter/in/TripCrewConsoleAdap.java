package org.vuelosGlobales.generals.tripcrew.adapter.in;

import org.vuelosGlobales.generals.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.generals.connection.domain.Connections;
import org.vuelosGlobales.generals.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.generals.tripcrew.application.TripCrewService;
import org.vuelosGlobales.generals.tripcrew.domain.TripCrew;
import org.vuelosGlobales.generals.tripcrew.domain.TripCrewInfoDTO;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;

import java.util.List;

public class TripCrewConsoleAdap {
    private final TripCrewService tripCrewService;
    Console console = new Console();
    public TripCrewConsoleAdap(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    public void crew(){
        tripulation: while (true){
            System.out.println("1. Asignar tripulación a conexión");
            System.out.println("2. Ver tripulación de una conexión");
            System.out.println("3. regresar");
            int choice = console.readInt("");
            switch (choice){
                case 1:
                    asignarTripulacion();
                    break;
                case 2:
                    verTripulacion();
                    break;
                case 3:
                    break tripulation;
            }
        }
    }

    public void asignarTripulacion(){

       getConn: while (true){
           CuadroDeTexto.dibujarCuadroDeTexto("Asignar tripulación", "*");
           showAirlines();
           Airline getAirline = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getAirlineById(console.readInt("Seleccione la aerolínea a la que pertenece el vuelo: "))
           );
           int idAirline = getAirline.getId();
           showTripes();
           TripAirportDTO getTrip = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getTripAirpById(console.readInt("Seleccione el viaje al que pertenece la conexión: "))
           );
           int idTrip = getTrip.getId();
           showConnections(idTrip);
           Connections getConn = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getConnectionById(console.readInt("Seleccione la conexión por el Id Conn: "))
           );
           showEmployees(idAirline);
           EmployeeRelationshipDTO piloto = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getEmployecById(console.stringNotNull("Seleccione al piloto por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(piloto.getId(), getConn.getId()));
           EmployeeRelationshipDTO copiloto = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getEmployecById(console.stringNotNull("Seleccione al copiloto por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(copiloto.getId(), getConn.getId()));
           EmployeeRelationshipDTO azafata1 = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getEmployecById(console.stringNotNull("Seleccione la azafata uno por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(azafata1.getId(), getConn.getId()));
           EmployeeRelationshipDTO azafata2 = Helpers.transformAndValidateObj(
                   () -> tripCrewService.getEmployecById(console.stringNotNull("Seleccione la azafata dos por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(azafata2.getId(), getConn.getId()));
           CuadroDeTexto.dibujarCuadroDeTexto(null, null);
           String pregunta = console.stringNotNull("Quiere asignar tripulación a otra conexión? (y/n): ");
           if (pregunta.equals("n")){
               break getConn;
           }
       }

    }

    public void verTripulacion(){
        CuadroDeTexto.dibujarCuadroDeTexto("Ver asignación de la tripulación", null);
        showTripes();
        TripAirportDTO getTrip = Helpers.transformAndValidateObj(
                () -> tripCrewService.getTripAirpById(console.readInt("Seleccione el viaje al que pertenece la conexión: "))
        );
        showConnections(getTrip.getId());
        Connections getConn = Helpers.transformAndValidateObj(
                () -> tripCrewService.getConnectionById(console.readInt("Seleccione la conexión por el  Id Conn: "))
        );
        showTripCrew(getConn.getId());
        System.out.println();
    }

    public void showTripCrew(int idConn){
        List<TripCrewInfoDTO> crew = tripCrewService.getDataTripCrewByConn(idConn);
        if (crew.isEmpty()){
            CuadroDeTexto.dibujarCuadroDeTexto("No hay tripulacion asignada", "-");
        }else{
            CuadroDeTexto.drawHorizontal(130, "-");
            System.out.printf("\n| %-35s | %-20s | %-20s | %-40s |%n", "Nombre", "Rol", "Conexión", "Aeropuerto");
            crew.forEach(data -> {
                CuadroDeTexto.drawHorizontal(130, "-");
                System.out.printf("\n| %-35s | %-20s | %-20s | %-40s |%n", data.getNameEmployee(), data.getRolEmployee(), data.getConnectionNumber(), data.getAirport());
            });
            CuadroDeTexto.drawHorizontal(130, "-");
        }
    }

    public void showAirlines(){
        List<Airline> airlineList = tripCrewService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        airlineList.forEach(airline -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.printf("\n| %-4s | %-16s |%n", airline.getId(), airline.getName());
        });
        System.out.println();
    }

    public void showTripes(){
        List<TripAirportDTO> airports = tripCrewService.getAllTripAirp();
        System.out.println("Listado de viajes:");
        CuadroDeTexto.drawHorizontal(130, "-");
        System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
        airports.forEach(airport -> {
            CuadroDeTexto.drawHorizontal(130, "-");
            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", airport.getId(), airport.getTripDate(), airport.getPriceTrip(), airport.getOrigin(), airport.getDestination());
        });
        CuadroDeTexto.drawHorizontal(130, "-");
        System.out.println();
    }

    public void showConnections(int idTrip){
        List<ConnInfoDTO> connetions = tripCrewService.getAllConectionByTrip(idTrip);
        CuadroDeTexto.dibujarCuadroDeTexto("Listado de conexiones", null);
        CuadroDeTexto.drawHorizontal(100, "-");
        System.out.printf("\n| %-10s | %-12s | %-13s | %-40s |%n", "Id Conn", "Conn Number", "plates plane", "Aeropuerto");
        connetions.forEach(conn -> {
            CuadroDeTexto.drawHorizontal(100, "-");
            System.out.printf("\n| %-10s | %-10s | %-10s | %-40s |%n", conn.getIdConn(), conn.getConnNumber(), conn.getPlates(), conn.getNameCityAirport());
        });
        CuadroDeTexto.drawHorizontal(100, "-");
        System.out.println();
    }

    public void showEmployees(int idAriline){
        List<EmployeeRelationshipDTO> employeeList = tripCrewService.getAllEmployees(true, idAriline);
        if (employeeList.isEmpty()){
            CuadroDeTexto.dibujarCuadroDeTexto("No hay tripulacion asignada", "-");
        }else{
            System.out.println("Listado de empleados:");
            CuadroDeTexto.drawHorizontal(151, "-");
            System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", "ID", "NOMBRE", "FECHA", "ROL", "AEROLINEA", "AIRPORT");
            employeeList.forEach(employee -> {
                CuadroDeTexto.drawHorizontal(151, "-");
                System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", employee.getId(), employee.getName(), employee.getIngressDate(), employee.getRolName(), employee.getAirlineName(), employee.getAirportName());
            });
            CuadroDeTexto.drawHorizontal(151, "-");
        }
        System.out.println();
    }
}
