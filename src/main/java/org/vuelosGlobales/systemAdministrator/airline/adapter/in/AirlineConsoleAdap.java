package org.vuelosGlobales.systemAdministrator.airline.adapter.in;

import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.airline.application.AirlineService;

import java.util.List;

public class AirlineConsoleAdap {
    private final AirlineService airlineService;
    Console console = new Console();

    public AirlineConsoleAdap(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    public  void crudAirline(){
        menuAirline: while (true){
            System.out.println("1. Crear Aerolínea");
            System.out.println("2. Actualizar Aerolínea");
            System.out.println("3. Buscar Aerolínea por ID");
            System.out.println("4. Eliminar Aerolínea");
            System.out.println("5. Listar todos Aerolíneas");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar aerolínea", "*");
                    String name = console.stringNotNull("Nombre del aerolínea: ");
                    Airline airline = new Airline();
                    airline.setName(name);
                    airlineService.createAirline(airline);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una aerolínea", "*");
                    showAirlines();
                    Airline airlineSelect = Helpers.transformAndValidateObj(
                            () -> airlineService.getAirlineById(console.readInt("Seleccione el aerolínea por el id: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + airlineSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre de la aerolínea: ");
                    airlineSelect.setName(newName);
                    airlineService.updateAirline(airlineSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de una aerolínea", "*");
                    System.out.println();
                    showAirlines();
                    Airline showAirline = Helpers.transformAndValidateObj(
                            () -> airlineService.getAirlineById(console.readInt("Seleccione el aerolínea por el id: "))
                    );
                    System.out.println(showAirline);
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar una aerolínea", "*");
                    showAirlines();
                    Airline showAirlineF = Helpers.transformAndValidateObj(
                            () -> airlineService.getAirlineById(console.readInt("Seleccione la aerolínea por el id: "))
                    );
                    int airlineDelete = showAirlineF.getId();
                    airlineService.deleteAirline(airlineDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Aerolínea eliminada con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Aerolíneas registradas", "*");
                    showAirlines();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;
                case 6:
                    break menuAirline;
            }
        }
    }

    public void showAirlines(){
        List<Airline> airlineList = airlineService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        airlineList.forEach(airline -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", airline.getId(), airline.getName()));
        });
        System.out.println();
    }
}
