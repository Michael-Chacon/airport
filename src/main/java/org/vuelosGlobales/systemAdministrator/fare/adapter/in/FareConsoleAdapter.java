package org.vuelosGlobales.systemAdministrator.fare.adapter.in;

import org.vuelosGlobales.generals.country.domain.Country;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.fare.application.FareService;
import org.vuelosGlobales.systemAdministrator.fare.domain.Fare;

import java.util.List;

public class FareConsoleAdapter {
    private final FareService fareService;
    Console console = new Console();
    public FareConsoleAdapter(FareService fareService) {
        this.fareService = fareService;
    }

    public  void crudFares(){
        menuCountry: while (true){
            System.out.println("======================================");
            System.out.println("             MENÚ DE TARIFAS           ");
            System.out.println("======================================");
            System.out.println("\t1. Crear taifa de vuelo");
            System.out.println("\t2. Actualizar tarifa de vuelo");
            System.out.println("\t3. Buscar tarifa por ID");
            System.out.println("\t4. Eliminar tarifa");
            System.out.println("\t5. Salir");
            System.out.println("======================================");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar tarifa de vuelo", "*");
                    String description = console.stringNotNull("Descripcion de la tarifa: ");
                    String detail = console.stringNotNull("Detalle de la tarifa: ");
                    Double value = Double.parseDouble(console.stringNotNull("Valor de la tarifa: "));

                    Fare fare = new Fare();
                    fare.setDescription(description);
                    fare.setDetails(detail);
                    fare.setValue(value);
                    fareService.createFare(fare);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una tarifa de vuelo", "*");
                    showFares();
                    Fare fareSelect = Helpers.transformAndValidateObj(
                            () -> fareService.getFareById(console.readInt("Seleccione el pais por el id: "))
                    );
                    String newDescription;
                    String v1 = console.stringNotNull("Quiere actulizar la descripcion de la tarifa?: (s/n): ");
                    if (v1.equals("s")){
                        newDescription = console.stringNotNull("Ingrese la nueva descripcion de la tarifa: ");
                    }else {
                        newDescription = fareSelect.getDescription();
                    }
                    String newDetail;
                    String v2 = console.stringNotNull("Quiere actulizar el detalle de la tarifa?: (s/n): ");
                    if (v2.equals("s")){
                        newDetail = console.stringNotNull("Ingrese el nuevo detalle de la tarifa: ");
                    }else {
                        newDetail = fareSelect.getDetails();
                    }

                    double newValue;
                    String v3 = console.stringNotNull("Quiere actulizar el valor de la tarifa?: (s/n): ");
                    if (v3.equals("s")){
                        newValue = Double.parseDouble(console.stringNotNull("Ingrese el nuevo detalle de la tarifa: "));
                    }else {
                        newValue = fareSelect.getValue();
                    }


                    fareSelect.setDescription(newDescription);
                    fareSelect.setDetails(newDetail);
                    fareSelect.setValue(newValue);
                    fareService.updateFare(fareSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, "-");
                    break;

                case 3:
                    consultarTarifa();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar una tarifa de vuelo", "*");
                    showFares();
                    Fare fareToDelete = Helpers.transformAndValidateObj(
                            () -> fareService.getFareById(console.readInt("Seleccione el pais por el id: "))
                    );
                    int fareDelete = fareToDelete.getId();
                    fareService.deleteFare(fareDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Tarifa eliminado con éxito", null);
                    break;
                case 5:
                    break menuCountry;
            }
        }
    }

    public void showFares(){
        List<Fare> fares = fareService.getAllFares();
        System.out.println("Listado de tarifas:");
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.printf("\n| %-4s | %-20s | %-30s | %-10s | %n", "ID", "DESC", "DETALLES", "VALOR");
        fares.forEach(fare -> {
            CuadroDeTexto.drawHorizontal(80, "-");
            System.out.printf("\n| %-4s | %-20s | %-30s | %-10s |%n", fare.getId(), fare.getDescription(), fare.getDetails(), fare.getValue());
        });
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.println();
    }

    public void consultarTarifa(){
        CuadroDeTexto.dibujarCuadroDeTexto("Mostrar informacion de una tarifa de vuelo", "*");
        System.out.println();
        showFares();
        Fare getFare = Helpers.transformAndValidateObj(
                () -> fareService.getFareById(console.readInt("Seleccione la tarifa de vuelo por el id: "))
        );
        System.out.printf("\n| %-4s | %-20s | %-30s | %-10s | %n", "ID", "DESC", "DETALLES", "VALOR");
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.printf("\n| %-4s | %-20s | %-30s | %-10s |%n", getFare.getId(), getFare.getDescription(), getFare.getDetails(), getFare.getValue());
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.println();
    }
}
