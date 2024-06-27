package org.vuelosGlobales.systemAdministrator.airport.adapter.in;

import org.vuelosGlobales.generals.city.domain.City;
import org.vuelosGlobales.systemAdministrator.airport.domain.Airport;
import org.vuelosGlobales.systemAdministrator.airport.domain.AirportCityDTO;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.airport.application.AirportService;

import java.util.List;

public class AirportConsoleAdapter {
    private final AirportService airportService;
    Console console = new Console();
    public AirportConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
    }

    public void crudAirport() {
        menuAirport: while (true){
            System.out.println("======================================");
            System.out.println("          MENÚ DE AEROPUERTOS         ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Aeropuerto");
            System.out.println("\t2. Actualizar Aeropuerto");
            System.out.println("\t3. Buscar Aeropuerto por ID");
            System.out.println("\t4. Eliminar Aeropuerto");
            System.out.println("\t5. Listar todos los Aeropuertos");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar aeropuerto", "*");
                    showCityes();
                    City objCity = Helpers.transformAndValidateObj(
                            () -> airportService.getCityById(console.stringNotNull("Selección el id de la ciudad donde está ubicado: ").toUpperCase())
                    );
                    String name = console.stringNotNull("Nombre del aeropuerto: ");
                    String getAirport = Helpers.validateExist(
                            "Ingrese el id del aeropuerto, debe ser alfanumerico de máximo 5 caracteres: ",
                            id -> airportService.getAirportById(id)
                    );
                    Airport st = new Airport();
                    st.setId(getAirport);
                    st.setName(name);
                    st.setIdCity(objCity.getId());
                    airportService.createAirport(st);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información del aeropuerto", "*");
                    showAirportes();
                    Airport airportSelect = Helpers.transformAndValidateObj(
                            () -> airportService.getAirportById(console.stringNotNull("Seleccione el aeropuerto por el id: ").toUpperCase())
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + airportSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del aeropuerto: ");
                    String idCity;
                    String updateCity = console.stringNotNull("Quiere cambiar la ubicación de este aeropuerto? (y/n): ");
                    if (updateCity.equals("y")){
                        showCityes();
                        City selectCity = Helpers.transformAndValidateObj(
                                () -> airportService.getCityById(console.stringNotNull("Selección el id del nuevo fabricante: ").toUpperCase())
                        );
                        idCity = selectCity.getId();
                    }else {
                        idCity = airportSelect.getIdCity();
                    }
                    airportSelect.setName(newName);
                    airportSelect.setIdCity(idCity);
                    airportService.updateAirport(airportSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un aeropuerto", "*");
                    System.out.println();
                    showAirportes();
                    AirportCityDTO showAirport = Helpers.transformAndValidateObj(
                            () -> airportService.getAirportCityById(console.stringNotNull("Seleccione el aeropuerto por el id: ").toUpperCase())
                    );
                    System.out.println(showAirport);
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un aeropuerto", "*");
                    showAirportes();
                    Airport showAirportF = Helpers.transformAndValidateObj(
                            () -> airportService.getAirportById(console.stringNotNull("Seleccione el aeropuerto por el id: ").toUpperCase())
                    );
                    String airportDelete = showAirportF.getId();
                    airportService.deleteAirport(airportDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Aeropuerto eliminado con éxito", null);
                    break;

                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Aeropuertos registrados", "*");
                    showAirportes();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 6:
                    break menuAirport;
            }
        }
    }

    public void showAirportes(){
        List<AirportCityDTO> airports = airportService.getAllAirportCity();
        System.out.println("Listado de estados:");
        CuadroDeTexto.drawHorizontal(57, "-");
        System.out.println(String.format("\n| %-5s | %-22s | %-20s |", "ID", "NOMBRE", "CIUDAD"));
        airports.forEach(airport -> {
            CuadroDeTexto.drawHorizontal(57, "-");
            System.out.println(String.format("\n| %-5s | %-22s | %-20s |", airport.getId(), airport.getNameAirport(), airport.getNameCity()));
        });
        CuadroDeTexto.drawHorizontal(57, "-");
        System.out.println();
    }

    public void showCityes(){
        List<City> cities = airportService.getAllCities();
        System.out.println("Listado de fabricantes:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        cities.forEach(city -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", city.getId(), city.getName()));
        });
        System.out.println();
    }
}
