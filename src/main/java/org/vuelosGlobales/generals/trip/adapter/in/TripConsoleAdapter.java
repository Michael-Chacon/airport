package org.vuelosGlobales.generals.trip.adapter.in;

import org.vuelosGlobales.generals.city.domain.City;
import org.vuelosGlobales.generals.trip.application.TripService;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.systemAdministrator.airport.domain.AirportCityDTO;

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
            System.out.println("3. Buscar Viaje por ID");
            System.out.println("4. Eliminar Viaje");
            System.out.println("5. Listar todos los Viajes");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar viaje", "*");
                    String tripDate = console.stringNotNull("Fecha del viaje, formado (dd-mm-yyyy): ");
//                    showAirports();
                    AirportCityDTO objAirportOrigin = Helpers.transformAndValidateObj(
                            () -> tripService.getAirportCityById(console.stringNotNull("Selección el aeropuerto de origen: ").toUpperCase())
                    );
                    AirportCityDTO objAirportDestination = Helpers.transformAndValidateObj(
                            () -> tripService.getAirportCityById(console.stringNotNull("Selección el aeropuerto de destino: ").toUpperCase())
                    );
                    String idOrigen = objAirportOrigin.getId();
                    String idDestination = objAirportDestination.getId();
                    Trip st = new Trip();
//                    st.setId(getTrip);
//                    st.setName(name);
//                    st.setIdCity(objCity.getId());
                    tripService.createTrip(st);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

//                case 2:
//                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información del viaje", "*");
//                    showTripes();
//                    Trip airportSelect = Helpers.transformAndValidateObj(
//                            () -> tripService.getTripById(console.stringNotNull("Seleccione el viaje por el id: ").toUpperCase())
//                    );
//                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + airportSelect.getName(), "*");
//                    String newName = console.stringNotNull("Nuevo nombre del viaje: ");
//                    String idCity;
//                    String updateCity = console.stringNotNull("Quiere cambiar la ubicación de este viaje? (y/n): ");
//                    if (updateCity.equals("y")){
//                        showAirports();
//                        City selectCity = Helpers.transformAndValidateObj(
//                                () -> tripService.getCityById(console.stringNotNull("Selección el id del nuevo fabricante: ").toUpperCase())
//                        );
//                        idCity = selectCity.getId();
//                    }else {
//                        idCity = airportSelect.getIdCity();
//                    }
//                    airportSelect.setName(newName);
//                    airportSelect.setIdCity(idCity);
//                    tripService.updateTrip(airportSelect);
//                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
//                    break;
//
//                case 3:
//                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un viaje", "*");
//                    System.out.println();
//                    showTripes();
//                    TripCityDTO showTrip = Helpers.transformAndValidateObj(
//                            () -> tripService.getTripCityById(console.stringNotNull("Seleccione el viaje por el id: ").toUpperCase())
//                    );
//                    System.out.println(showTrip);
//                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
//                    break;
//
//                case 4:
//                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un viaje", "*");
//                    showTripes();
//                    Trip showTripF = Helpers.transformAndValidateObj(
//                            () -> tripService.getTripById(console.stringNotNull("Seleccione el viaje por el id: ").toUpperCase())
//                    );
//                    String airportDelete = showTripF.getId();
//                    tripService.deleteTrip(airportDelete);
//                    CuadroDeTexto.dibujarCuadroDeTexto("Viaje eliminado con éxito", null);
//                    break;
//
//                case 5:
//                    CuadroDeTexto.dibujarCuadroDeTexto("Viajes registrados", "*");
//                    showTripes();
//                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
//                    break;
//
//                case 6:
//                    break menuTrip;
            }
        }
    }

//    public void showTripes(){
//        List<TripCityDTO> airports = tripService.getAllTripCity();
//        System.out.println("Listado de estados:");
//        CuadroDeTexto.drawHorizontal(57, "-");
//        System.out.println(String.format("\n| %-5s | %-22s | %-20s |", "ID", "NOMBRE", "CIUDAD"));
//        airports.forEach(airport -> {
//            CuadroDeTexto.drawHorizontal(57, "-");
//            System.out.println(String.format("\n| %-5s | %-22s | %-20s |", airport.getId(), airport.getNameTrip(), airport.getNameCity()));
//        });
//        CuadroDeTexto.drawHorizontal(57, "-");
//        System.out.println();
//    }
//
//    public void showAirports(){
//        List<City> cities = tripService.getAllCities();
//        System.out.println("Listado de fabricantes:");
//        CuadroDeTexto.drawHorizontal(27, "-");
//        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
//        cities.forEach(city -> {
//            CuadroDeTexto.drawHorizontal(27, "-");
//            System.out.println(String.format("\n| %-4s | %-16s |", city.getId(), city.getName()));
//        });
//        System.out.println();
//    }

}
