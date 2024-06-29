package org.vuelosGlobales.generals.trip.adapter.in;

import org.vuelosGlobales.generals.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.generals.connection.domain.Connections;
import org.vuelosGlobales.generals.trip.application.TripService;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.systemAdministrator.airport.domain.AirportCityDTO;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;

import java.sql.Date;
import java.util.List;

public class TripConsoleAdapter {
    private final TripService tripService;
    Console console = new Console();

    public TripConsoleAdapter(TripService tripService) {
        this.tripService = tripService;
    }

    public void crudTrip() {
        menuTrip: while (true){
            System.out.println("1. Crear Viaje");
            System.out.println("2. Actualizar Viaje");
            System.out.println("3. Actualizar una conexión");
            System.out.println("4. Buscar Viaje por ID");
            System.out.println("5. Eliminar Viaje");
            System.out.println("6. Listar todos los Viajes");
            System.out.println("7. Salir");
            int choice = console.readInt("");

            switch (choice) {
                case 1 -> {
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar viaje", "*");
                    Date tripDate = console.validarFecha("Fecha del viaje, formado (yyyy-mm-dd): ");
                    int priceTrip = console.readInt("Ingrese el precio del viaje: ");
                    showAirports();
                    AirportCityDTO objAirportOrigin = Helpers.transformAndValidateObj(
                            () -> tripService.getAirportCityById(console.stringNotNull("Selección el aeropuerto de origen por su ID: ").toUpperCase())
                    );
                    AirportCityDTO objAirportDestination = Helpers.transformAndValidateObj(
                            () -> tripService.getAirportCityById(console.stringNotNull("Selección el aeropuerto de destino por us ID: ").toUpperCase())
                    );
                    String idOrigen = objAirportOrigin.getId();
                    String idDestination = objAirportDestination.getId();
                    Trip st = new Trip();
                    st.setPriceTrip(priceTrip);
                    st.setTripDate(tripDate);
                    st.setIdOrigin(idOrigen);
                    st.setIdDestination(idDestination);
                    int tripId = tripService.createTrip(st);

                    String tipoVuelo = console.stringNotNull("¿El vuelo tiene escalas?(y/n): ");
                    if (tipoVuelo.equals("n")) {
                        String nroConexion = console.stringNotNull("Ingrese el número de la conexión: ");
                        showPlanes();
                        PlaneStMdDTO planeSelect = Helpers.transformAndValidateObj(
                                () -> tripService.getPlaneById(console.readInt("Selección el avión: "))
                        );
                        int idPlane = planeSelect.getId();
                        Connections connections = new Connections();
                        connections.setConnectionNumber(nroConexion);
                        connections.setIdTrip(tripId);
                        connections.setIdPlane(idPlane);
                        connections.setIdAriport(idDestination);
                        tripService.createConnecion(connections);
                    } else {
                        CuadroDeTexto.dibujarCuadroDeTexto("Registrar conexiones", "*");
                        masconexiones: while (true) {
                            String nroConexion = console.stringNotNull("Ingrese el número de la conexión");
                            showPlanes();
                            PlaneStMdDTO planeSelect = Helpers.transformAndValidateObj(
                                    () -> tripService.getPlaneById(console.readInt("Selección el avión: "))
                            );
                            int idPlane = planeSelect.getId();
                            showAirports();
                            AirportCityDTO getAirport = Helpers.transformAndValidateObj(
                                    () -> tripService.getAirportCityById(console.stringNotNull("Seleccione el aeropuerto donde se hace la conexión: "))
                            );
                            String idAirport = getAirport.getId();
                            Connections connections = new Connections();
                            connections.setConnectionNumber(nroConexion);
                            connections.setIdTrip(tripId);
                            connections.setIdPlane(idPlane);
                            connections.setIdAriport(idAirport);
                            tripService.createConnecion(connections);
                            String validar = console.stringNotNull("Quiere registrar otra conexión? (y/n): ");
                            if (validar.equals("n")) {
                                break masconexiones;
                            }
                        }
                    }
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                }
                case 2 -> {
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información del viaje", "*");
                    showTripes();
                    Trip airportSelect = Helpers.transformAndValidateObj(
                            () -> tripService.getTripById(console.readInt("Seleccione el viaje por su ID: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar los datos de un trayecto ", "*");
                    Date newDate = console.validarFecha("Nueva fecha: ");
                    String newPreice = console.stringNotNull("Nuevo precio: ");
                    airportSelect.setTripDate(newDate);
                    airportSelect.setPriceTrip(Double.parseDouble(newPreice));
                    tripService.updateTrip(airportSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                }
                case 3 -> {
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar la información de una conexión", "*");
                    showTripes();
                    TripAirportDTO getTrip = Helpers.transformAndValidateObj(
                            () -> tripService.getTripAripById(console.readInt("Elija el viaje cuya conexión desea actualizar: "))
                    );
                    CuadroDeTexto.drawHorizontal(130, "-");
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
                    CuadroDeTexto.drawHorizontal(130, "-");
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", getTrip.getId(), getTrip.getTripDate(), getTrip.getPriceTrip(), getTrip.getOrigin(), getTrip.getDestination());
                    CuadroDeTexto.drawHorizontal(130, "-");
                    showConnections(getTrip.getId());
                    Connections getConn = Helpers.transformAndValidateObj(
                            () -> tripService.getConnById(console.readInt("Seleccione la conexión a editar por su Id Conn: "))
                    );
                    String nroConxion = console.stringNotNull("Ingrese el nuevo número de la conexión:");
                    int idPlane;
                    String upPlane = console.stringNotNull("¿Desea cambiar el avión de esta conexión? (s/n): ");
                    if (upPlane.equals("s")) {
                        showPlanes();
                        PlaneStMdDTO getPlane = Helpers.transformAndValidateObj(
                                () -> tripService.getPlaneById(console.readInt("Seleccione el id del nuevo avión para esta conexión: "))
                        );
                        idPlane = getPlane.getId();
                    } else {
                        idPlane = getConn.getIdPlane();
                    }
                    String idAirport;
                    String upAirport = console.stringNotNull("¿Desea cambiar el aeropuerto de esta conexión? (s/n): ");
                    if (upAirport.equals("s")) {
                        showAirports();
                        AirportCityDTO getAirport = Helpers.transformAndValidateObj(
                                () -> tripService.getAirportCityById(console.stringNotNull("Seleccione el nuevo aeropuerto por su ID: "))
                        );
                        idAirport = getAirport.getId();
                    } else {
                        idAirport = getConn.getIdAriport();
                    }
                    System.out.println(idAirport);
                    System.out.println(idPlane);
                    getConn.setConnectionNumber(nroConxion);
                    getConn.setIdPlane(idPlane);
                    getConn.setIdAriport(idAirport);
                    tripService.updateConnection(getConn);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                }
                case 4 -> {
                    CuadroDeTexto.dibujarCuadroDeTexto("Buscar viaje por ID", "*");
                    showTripes();
                    TripAirportDTO getTripObj = Helpers.transformAndValidateObj(
                            () -> tripService.getTripAripById(console.readInt("Selecciona el id del viaje: "))
                    );

                    CuadroDeTexto.drawHorizontal(130, "-");
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
                    CuadroDeTexto.drawHorizontal(130, "-");
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", getTripObj.getId(), getTripObj.getTripDate(), getTripObj.getPriceTrip(), getTripObj.getOrigin(), getTripObj.getDestination());
                    CuadroDeTexto.drawHorizontal(130, "-");

                    showConnections(getTripObj.getId());
                    System.out.println();
                    break ;
                }

                case 5 -> {
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un viaje", "*");
                    showTripes();
                    Trip showTripF = Helpers.transformAndValidateObj(
                            () -> tripService.getTripById(console.readInt("Seleccione el viaje por el id: "))
                    );
                    int tripDelete = showTripF.getId();
                    tripService.deleteTrip(tripDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Viaje eliminado con éxito", null);
                    break;
                }
                case 6 -> {
                    CuadroDeTexto.dibujarCuadroDeTexto("Viajes registrados", "*");
                    showTripes();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;
                }
                case 7 -> {
                    break menuTrip;
                }

            }
        }
    }

    public void showTripes(){
        List<TripAirportDTO> airports = tripService.getAllTripAirp();
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

    public void showAirports(){
        List<AirportCityDTO> listadoAeropuertos = tripService.getAllAirportCity();
        System.out.println("Listado Aeropuertos:");
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.printf("\n| %-4s | %-30s | %-30s |%n", "ID", "Aeropuerto", "Ciudad");
        listadoAeropuertos.forEach(aeropuerto -> {
            CuadroDeTexto.drawHorizontal(80, "-");
            System.out.printf("\n| %-4s | %-30s | %-30s |%n", aeropuerto.getId(), aeropuerto.getNameAirport(), aeropuerto.getNameCity());
        });
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.println();
    }

    public void showPlanes(){
        List<PlaneStMdDTO> planeList = tripService.getAllPlanesInfo();
        System.out.println("Listado de aviones:");
        CuadroDeTexto.drawHorizontal(110, "-");
        System.out.printf("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |%n", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "AEROLÍNEA", "ESTADO", "MODELO");
        planeList.forEach(plane -> {
            CuadroDeTexto.drawHorizontal(105, "-");
            System.out.printf("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |%n", plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getNameAirline(), plane.getNameStatus(), plane.getNameModel());
        });
        CuadroDeTexto.drawHorizontal(107, "-");
        System.out.println();
    }

    public void showConnections(int idTrip){
        List<ConnInfoDTO> connetions = tripService.getAllConnByTrip(idTrip);
        System.out.println();
        CuadroDeTexto.dibujarCuadroDeTexto("Listado de conexiones", "*");
        CuadroDeTexto.drawHorizontal(100, "-");
        System.out.printf("\n| %-10s | %-10s | %-12s | %-13s | %-40s |%n", "Id Trip", "Id Conn", "Conn Number", "plates plane", "Aeropuerto");
        connetions.forEach(conn -> {
            CuadroDeTexto.drawHorizontal(100, "-");
            System.out.printf("\n| %-10s | %-10s | %-10s | %-10s | %-40s |%n", conn.getIdTrip(), conn.getIdConn(), conn.getConnNumber(), conn.getPlates(), conn.getNameCityAirport());
        });
        CuadroDeTexto.drawHorizontal(100, "-");
        System.out.println();
    }
}
