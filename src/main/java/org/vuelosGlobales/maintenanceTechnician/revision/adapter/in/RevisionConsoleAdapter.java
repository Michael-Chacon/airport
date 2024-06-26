package org.vuelosGlobales.maintenanceTechnician.revision.adapter.in;

import org.vuelosGlobales.generals.employee.domain.Employee;
import org.vuelosGlobales.generals.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.maintenanceTechnician.revision.application.RevisionService;
import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;
import org.vuelosGlobales.maintenanceTechnician.revision.domain.RevisionInfoDTO;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;

import java.util.List;

public class RevisionConsoleAdapter {
    private final RevisionService revisionService;
    Console console = new Console();

    public RevisionConsoleAdapter(RevisionService revisionService) {
        this.revisionService = revisionService;
    }
    public  void crudRevision(){
        menuRevision: while (true){
            System.out.println("------- Menú de Gestión de Revisiones -------");
            System.out.println("1. Crear Revisión");
            System.out.println("2. Actualizar Revisión");
            System.out.println("3. Buscar Revisión por avión");
            System.out.println("4. Eliminar Revisión");
            System.out.println("5. Salir");
            System.out.println("--------------------------------------------");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar revisión de avión", "*");
                    showAirlines();
                    Airline airline = Helpers.transformAndValidateObj(
                            () -> revisionService.getAirlineById(console.readInt("Seleccione la aerolínea a la que pertenece el avión: "))
                    );
                    int idAirline = airline.getId();

                    showPlanes(true, idAirline);
                    PlaneStMdDTO getPlane = Helpers.transformAndValidateObj(
                            () -> revisionService.getPlaneById(console.readInt("Seleccione el avión al que le va a hacer la revisión: "))
                    );
                    int idPlane = getPlane.getId();

                    System.out.println("Listado de empleados de la aerolínea " + airline.getName());
                    showEmployees(true, airline.getId());
                    EmployeeRelationshipDTO employee = Helpers.transformAndValidateObj(
                            () -> revisionService.getEmployeeById(console.stringNotNull("Seleccione al empleado por el id: ").toUpperCase())
                    );
                    String idEmployee = employee.getId();

                    String revisionDate = console.stringWithLeght("En que fecha se hizo la revisión, formato valido de fecha(YYYY-MM-DD): ", 10);
                    String description = console.stringNotNull("Escriba una descripción detallada de la revisión:\n ");
                    Revision revision = new Revision();
                    revision.setRevisionDate(revisionDate);
                    revision.setIdPlane(idPlane);
                    revision.setDescription(description);
                    int id = revisionService.createRevision(revision);
                    revisionService.saveRevisionEmpl(id, idEmployee);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una revisión", "*");
                    showRevisions();
                    Revision revisionSelect = Helpers.transformAndValidateObj(
                            () -> revisionService.getRevisionById(console.readInt("Seleccione la revisión  por el id: "))
                    );
                    System.out.println(revisionSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de la revisión" + revisionSelect.getId(), "*");
                    String revisionDateUp = console.stringNotNull("Ingrese al fecha actualizada de la revisión: ");
                    String descriptionUp = console.stringNotNull("Escriba la nueva descripción: \n");
                    int idPlaneRevision;
                    String validate = console.stringNotNull("Quiere cambiar el avión al que le hizo la revisión? (y/n)");
                    if (validate.equals("y")){
                        showPlanes(false, 0);
                        PlaneStMdDTO getPlaneSelect = Helpers.transformAndValidateObj(
                                () -> revisionService.getPlaneById(console.readInt("A que país pertenece la ciudad, seleccione por el por el id: "))
                        );
                        idPlaneRevision = getPlaneSelect.getId();
                    }else {
                        idPlaneRevision = revisionSelect.getIdPlane();
                    }
                    revisionSelect.setRevisionDate(revisionDateUp);
                    revisionSelect.setDescription(descriptionUp);
                    revisionSelect.setIdPlane(idPlaneRevision);
                    revisionService.updateRevision(revisionSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Consultar historial de revisiones de avión", "*");
                    showAirlines();
                    Airline airlineSelect = Helpers.transformAndValidateObj(
                            () -> revisionService.getAirlineById(console.readInt("Seleccione la aerolínea a la que pertenece el avión: "))
                    );
                    System.out.println();
                    showPlanes(true, airlineSelect.getId());
                    PlaneStMdDTO showPlanes = Helpers.transformAndValidateObj(
                            () -> revisionService.getPlaneById(console.readInt("Seleccionar el avión por su id: "))
                    );

                    showRevisionByIdPlane(showPlanes.getId());
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un revisión", "*");
                    showAirlines();
                    Airline airlineSel = Helpers.transformAndValidateObj(
                            () -> revisionService.getAirlineById(console.readInt("Seleccione la aerolínea a la que pertenece el avión: "))
                    );
                    System.out.println();
                    showPlanes(true, airlineSel.getId());
                    PlaneStMdDTO planesS = Helpers.transformAndValidateObj(
                            () -> revisionService.getPlaneById(console.readInt("Seleccionar el avión por su id: "))
                    );

                    showRevisionByIdPlane(planesS.getId());
                    Revision rev = Helpers.transformAndValidateObj(
                            () -> revisionService.getRevisionById(console.readInt("Seleccione por el id la revisión a eliminar: "))
                    );
                    int revisionDelete = rev.getId();
                    revisionService.deleteRevision(revisionDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Revisión eliminado con éxito", null);
                    break;
                case 5:
                    break menuRevision;
            }
        }
    }

    public void showRevisions(){
        List<Revision> revisionList = revisionService.getAllRevisions();
        System.out.println("Listado de revisiones:");
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", "ID", "FECHA", "AVIÓN", "DESCRIPCIÓN"));
        revisionList.forEach(revision -> {
            CuadroDeTexto.drawHorizontal(80, "-");
            System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", revision.getId(), revision.getRevisionDate(), revision.getIdPlane(), revision.getDescription()));
        });
        CuadroDeTexto.drawHorizontal(80, "-");
        System.out.println();
    }

    public void showAirlines(){
        List<Airline> airlineList = revisionService.getAllAirlines();
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

    public void showPlanes(boolean filter, int id){
        List<PlaneStMdDTO> planeList = revisionService.getAllPlanes(filter, id);
        System.out.println("Listado de aviones:");
        CuadroDeTexto.drawHorizontal(100, "-");
        System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s |", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "ESTADO", "MODELO"));
        planeList.forEach(plane -> {
            CuadroDeTexto.drawHorizontal(100, "-");
            System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s |", plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getNameStatus(), plane.getNameModel()));
        });
        CuadroDeTexto.drawHorizontal(100, "-");
        System.out.println();
    }

    public void showRevisionByIdPlane(int id){
        List<RevisionInfoDTO> revisionList = revisionService.getRevisionInfo(id);
        System.out.println("Historial de revisiones:");
        CuadroDeTexto.drawHorizontal(110, "-");
        System.out.println(String.format("\n| %-4s | %-16s | %-25s | %-15s | %-15s | %-40s ", "ID", "FECHA", "EMPLEADO", "PLACA", "MODELO", "DESCRIPCIÓN"));
        revisionList.forEach(revision -> {
            CuadroDeTexto.drawHorizontal(110, "-");
            System.out.println(String.format("\n| %-4s | %-16s | %-25s | %-15s | %-15s | %-40s ", revision.getId(), revision.getRevisionDate(), revision.getNameEmployee(), revision.getPlatePlane(), revision.getModelPlane(), revision.getDescription()));
        });
        CuadroDeTexto.drawHorizontal(110, "-");
        System.out.println();
    }

    public void showEmployees(boolean filter, int id){
        List<EmployeeRelationshipDTO> employeeList = revisionService.getAllEployeesInfo(filter, id);
        CuadroDeTexto.drawHorizontal(151, "-");
        System.out.println(String.format("\n| %-11s | %-17s | %-16s | %-45s |", "ID", "NOMBRE", "ROL", "AIRPORT"));
        employeeList.forEach(employee -> {
            CuadroDeTexto.drawHorizontal(151, "-");
            System.out.println(String.format("\n| %-11s | %-17s | %-16s | %-45s |", employee.getId(), employee.getName(), employee.getRolName(), employee.getAirportName()));
        });
        CuadroDeTexto.drawHorizontal(151, "-");
        System.out.println();
    }
}
