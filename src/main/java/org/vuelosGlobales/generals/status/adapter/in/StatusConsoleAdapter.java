package org.vuelosGlobales.generals.status.adapter.in;

import org.vuelosGlobales.generals.status.domain.Status;
import org.vuelosGlobales.generals.status.application.StatusService;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;

import java.util.List;

public class StatusConsoleAdapter {
    private final StatusService statusService;
    Console console = new Console();

    public StatusConsoleAdapter(StatusService statusService) {
        this.statusService = statusService;
    }

    public void crudStatus(){
        menuStatus: while (true){
            System.out.println("======================================");
            System.out.println("         MENÚ DE GESTIÓN DE ESTADOS     ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Estado");
            System.out.println("\t2. Actualizar Estado");
            System.out.println("\t3. Buscar Estado por ID");
            System.out.println("\t4. Eliminar Estado");
            System.out.println("\t5. Listar todos los Estados");
            System.out.println("\t6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar el estado de los  avión", "*");
                    String name = console.stringNotNull("Nombre del estado: ");
                    Status st = new Status();
                    st.setName(name);
                    statusService.createStatus(st);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de un estado del avión", "*");
                    showStatuses();
                    Status statusSelect = Helpers.transformAndValidateObj(
                            () -> statusService.getStatusById(console.readInt("Seleccione el estado por el id: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + statusSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del estado: ");
                    statusSelect.setName(newName);
                    statusService.updateStatus(statusSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un estado", "*");
                    System.out.println();
                    showStatuses();
                    Status showStatus = Helpers.transformAndValidateObj(
                            () -> statusService.getStatusById(console.readInt("Seleccione el estado por el id: "))
                    );
                    System.out.println(showStatus);
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un estado", "*");
                    showStatuses();
                    Status showStatusF = Helpers.transformAndValidateObj(
                            () -> statusService.getStatusById(console.readInt("Seleccione el estado por el id: "))
                    );
                    int statusDelete = showStatusF.getId();
                    statusService.deleteStatus(statusDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Estado eliminado con éxito", null);
                    break;

                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Estados registrados", "*");
                    showStatuses();
                    System.out.println();
                    break;

                case 6:
                    break menuStatus;
            }
        }
    }

    public void showStatuses(){
        List<Status> statuses = statusService.getAllStatuss();
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "ESTADOS"));
        statuses.forEach(country -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }

}

