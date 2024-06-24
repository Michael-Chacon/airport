package org.vuelosGlobales.maintenanceTechnician.revision.adapter.in;

import org.vuelosGlobales.maintenanceTechnician.revision.application.RevisionService;
import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
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
            System.out.println("1. Crear Revisión");
            System.out.println("2. Actualizar Revisión");
            System.out.println("3. Buscar Revisión por ID");
            System.out.println("4. Eliminar Revisión");
            System.out.println("5. Listar todos Revisiones");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar revisión de avión", "*");
                    showPlanes();

                    PlaneStMdDTO getPlane = Helpers.transformAndValidateObj(
                            () -> revisionService.getPlaneById(console.readInt("Seleccione el avión al que le va a hacer la revisión: "))
                    );
                    int idPlane = getPlane.getId();
                    String revisionDate = console.stringWithLeght("En que fecha se hizo la revisión, formato valido de fecha(YYYY-MM-DD): ", 10);
                    String description = console.stringNotNull("Escriba una descripción detallada de la revisión:\n ");
                    Revision revision = new Revision();
                    revision.setRevisionDate(revisionDate);
                    revision.setIdPlane(idPlane);
                    revision.setDescription(description);
                    revisionService.createRevision(revision);
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
                        showPlanes();
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
                    System.out.println();
                    showPlanes();
                    PlaneStMdDTO showPlanes = Helpers.transformAndValidateObj(
                            () -> revisionService.getPlaneById(console.readInt("Seleccionar el avión por su id: "))
                    );

                    showRevisionByIdPlane(showPlanes.getId());
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un revisión", "*");
                    showRevisions();
                    Revision showRevisionD = Helpers.transformAndValidateObj(
                            () -> revisionService.getRevisionById(console.readInt("Seleccione la ciudad por el id: "))
                    );
                    int revisionDelete = showRevisionD.getId();
                    revisionService.deleteRevision(revisionDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Revisión eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Revisiones registradas", "*");
                    showRevisions();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;
                case 6:
                    break menuRevision;
            }
        }
    }

    public void showRevisions(){
        List<Revision> revisionList = revisionService.getAllRevisions();
        System.out.println("Listado de revisiones:");
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", "ID", "FECHA", "AVIÓN", "DESCRIPCIÓN"));
        revisionList.forEach(revision -> {
            CuadroDeTexto.drawHorizontal(50, "-");
            System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", revision.getId(), revision.getRevisionDate(), revision.getIdPlane(), revision.getDescription()));
        });
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.println();
    }

    public void showPlanes(){
        List<PlaneStMdDTO> planeList = revisionService.getAllPlanes();
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
        List<Revision> revisionList = revisionService.getRevisionsByIdPlane(id);
        System.out.println("Historial de revisiones:");
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", "ID", "FECHA", "AVIÓN", "DESCRIPCIÓN"));
        revisionList.forEach(revision -> {
            CuadroDeTexto.drawHorizontal(50, "-");
            System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", revision.getId(), revision.getRevisionDate(), revision.getIdPlane(), revision.getDescription()));
        });
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.println();
    }
}
