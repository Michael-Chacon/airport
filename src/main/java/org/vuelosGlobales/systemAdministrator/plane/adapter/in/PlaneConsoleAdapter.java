package org.vuelosGlobales.systemAdministrator.plane.adapter.in;

import org.vuelosGlobales.generals.model.domain.Model;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.generals.status.domain.Status;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.plane.application.PlaneService;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;

import java.util.List;

public class PlaneConsoleAdapter {
    private final PlaneService planeService;
    Console console = new Console();
    public PlaneConsoleAdapter(PlaneService planeService) {
        this.planeService = planeService;
    }

    public  void crudPlane(){
        menuPlane: while (true){
            System.out.println("1. Crear Avión");
            System.out.println("2. Actualizar Avión");
            System.out.println("3. Buscar Avión por ID");
            System.out.println("4. Eliminar Avión");
            System.out.println("5. Listar todos Aviones");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar un avión", "*");
                    showAirlines();
                    Airline airlineSelect = Helpers.transformAndValidateObj(
                            () -> planeService.getAirlineById(console.readInt("Seleccione la aerolínea a la que pertenece el avión"))
                    );
                    int airlineId = airlineSelect.getId();
                    String plate = console.stringNotNull("Ingrese la placa del avión: ");
                    int capacity = console.readInt("Cual es la capacidad del avión: ");
                    String fabreicationDate = console.stringWithLeght("En que fecha se fabricó el avión, formato valido de fecha(YYYY-MM-DD): ", 10);

                    showModel();
                    Model getModel = Helpers.transformAndValidateObj(
                            () -> planeService.getModelById(console.readInt("Seleccione el modelo del avión: "))
                    );
                    int idModel = getModel.getId();

                    showStatus();
                    Status getStatus = Helpers.transformAndValidateObj(
                            () -> planeService.getStatusById(console.readInt("Seleccione el estado en que se encuentra el avión: "))
                    );
                    int idStatus = getStatus.getId();
                    Plane objPlane = new Plane();
                    objPlane.setPlates(plate);
                    objPlane.setCapacity(capacity);
                    objPlane.setFabricationDate(fabreicationDate);
                    objPlane.setIdAirline(airlineId);
                    objPlane.setIdStatus(idStatus);
                    objPlane.setIdModel(idModel);
                    planeService.createPlane(objPlane);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una avión", "*");
                    showPlanes();
                    Plane planeSelect = Helpers.transformAndValidateObj(
                            () -> planeService.getPlaneById(console.readInt("Seleccione el avión por el id: "))
                    );

                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos del avión con las placas " + planeSelect.getPlates(), "*");
                    int updateCapacity = console.readInt("Nueva capacidad: ");

                    int idAirlineUp;
                    String validate1 = console.stringNotNull("Quiere cambiar la aerolínea del avión? (y/n): ");
                    if (validate1.equals("y")){
                        showAirlines();
                        Airline getAirlineUp = Helpers.transformAndValidateObj(
                                () -> planeService.getAirlineById(console.readInt("Seleccione el nuevo estado en que se encuentra el avión: "))
                        );
                        idAirlineUp = getAirlineUp.getId();
                    }else {
                        idAirlineUp = planeSelect.getIdStatus();
                    }

                    int idStatusUp;
                    String validate = console.stringNotNull("Quiere cambiar el estado del avión? (y/n): ");
                    if (validate.equals("y")){
                        showStatus();
                        Status getStatusUp = Helpers.transformAndValidateObj(
                                () -> planeService.getStatusById(console.readInt("Seleccione el nuevo estado en que se encuentra el avión: "))
                        );
                        idStatusUp = getStatusUp.getId();
                    }else {
                        idStatusUp = planeSelect.getIdStatus();
                    }
                    int idModelUp;
                    String validate2 = console.stringNotNull("Quiere cambiar el modelo del avión? (y/n): ");
                    if (validate2.equals("y")){
                        showModel();
                        Model getModelUp = Helpers.transformAndValidateObj(
                                () -> planeService.getModelById(console.readInt("Seleccione el nuevo modelo en que se encuentra el avión: "))
                        );
                        idModelUp = getModelUp.getId();
                    }else {
                        idModelUp = planeSelect.getIdModel();
                    }

                    planeSelect.setCapacity(updateCapacity);
                    planeSelect.setIdAirline(idAirlineUp);
                    planeSelect.setIdStatus(idStatusUp);
                    planeSelect.setIdModel(idModelUp);
                    planeService.updatePlane(planeSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de avión", "*");
                    System.out.println();
                    showPlanes();
                    PlaneStMdDTO showPlane = Helpers.transformAndValidateObj(
                            () -> planeService.getPlaneStMdById(console.readInt("Seleccione el avión por el id: "))
                    );
                    System.out.println(showPlane);
                    System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s | %-20s |", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "AEROLÍNEA", "ESTADO", "MODELO"));
                    CuadroDeTexto.drawHorizontal(100, "-");
                    System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s | %-20s |", showPlane.getId(), showPlane.getPlates(), showPlane.getCapacity(), showPlane.getFabricationDate(),showPlane.getNameAirline(), showPlane.getNameStatus(), showPlane.getNameModel()));
                    CuadroDeTexto.drawHorizontal(100, "-");
                    System.out.println();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un avión", "*");
                    showPlanes();
                    Plane showPlaneD = Helpers.transformAndValidateObj(
                            () -> planeService.getPlaneById(console.readInt("Seleccione el avión por el id: "))
                    );
                    int planeDelete = showPlaneD.getId();
                    planeService.deletePlane(planeDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Avión eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Aviones registradas", "*");
                    showPlanes();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;
                case 6:
                    break menuPlane;
            }
        }
    }

    public void showPlanes(){
        List<PlaneStMdDTO> planeList = planeService.getAllPlaneStMd();
        System.out.println("Listado de aviones:");
        CuadroDeTexto.drawHorizontal(110, "-");
        System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "AEROLÍNEA", "ESTADO", "MODELO"));
        planeList.forEach(plane -> {
            CuadroDeTexto.drawHorizontal(105, "-");
            System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |", plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getNameAirline(), plane.getNameStatus(), plane.getNameModel()));
        });
        CuadroDeTexto.drawHorizontal(107, "-");
        System.out.println();
    }

    public void showAirlines(){
        List<Airline> airlineList = planeService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        airlineList.forEach(airline -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", airline.getId(), airline.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }
    public void showModel(){
        List<Model> modelList = planeService.getAllModels();
        System.out.println("Listado de modelos:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        modelList.forEach(model -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", model.getId(), model.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }

    public void showStatus(){
        List<Status> statusList = planeService.getAllStatus();
        System.out.println("Listado de estados:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        statusList.forEach(status -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", status.getId(), status.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }

}
