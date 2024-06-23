package org.vuelosGlobales.generals.model.adapter.in;

import org.vuelosGlobales.generals.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.generals.model.application.ModelService;
import org.vuelosGlobales.generals.model.domain.Model;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;

import java.util.List;

public class ModelConsoleAdap {
    private final ModelService modelService;
    Console console = new Console();

    public ModelConsoleAdap(ModelService modelService) {
        this.modelService = modelService;
    }

    public void crudModel() {
        menuModel: while (true){
            System.out.println("1. Crear Modelo");
            System.out.println("2. Actualizar Modelo");
            System.out.println("3. Buscar Modelo por ID");
            System.out.println("4. Eliminar Modelo");
            System.out.println("5. Listar todos Modelos");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar Modelo del avión", "*");
                    String name = console.stringNotNull("Nombre del estado: ");
                    showManufactureres();
                    Manufacturer objManufacturer = Helpers.transformAndValidateObj(
                            () -> modelService.getManufacturerById(console.readInt("Selección el id del fabricante: "))
                    );
                    Model st = new Model();
                    st.setName(name);
                    st.setManufacturerId(objManufacturer.getId());
                    modelService.createModel(st);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de un modelo de avión", "*");
                    showModeles();
                    Model modelSelect = Helpers.transformAndValidateObj(
                            () -> modelService.getModelById(console.readInt("Seleccione el modelo por el id: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + modelSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del estado: ");
                    int idManufacturer;
                    String updateManufaturer = console.stringNotNull("Quiere cambiar al fabricante de este modelo de avión? (y/n): ");
                    if (updateManufaturer.equals("y")){
                        showManufactureres();
                        Manufacturer selectManufacturer = Helpers.transformAndValidateObj(
                                () -> modelService.getManufacturerById(console.readInt("Selección el id del nuevo fabricante: "))
                        );
                        idManufacturer = selectManufacturer.getId();
                    }else {
                        idManufacturer = modelSelect.getManufacturerId();
                    }
                    modelSelect.setName(newName);
                    modelSelect.setManufacturerId(idManufacturer);
                    modelService.updateModel(modelSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un modelo", "*");
                    System.out.println();
                    showModeles();
                    Model showModel = Helpers.transformAndValidateObj(
                            () -> modelService.getModelById(console.readInt("Seleccione el modelo por el id: "))
                    );
                    System.out.println(showModel);
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un modelo", "*");
                    showModeles();
                    Model showModelF = Helpers.transformAndValidateObj(
                            () -> modelService.getModelById(console.readInt("Seleccione el modelo por el id: "))
                    );
                    int modelDelete = showModelF.getId();
                    modelService.deleteModel(modelDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Modelo eliminado con éxito", null);
                    break;

                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Modelos registrados", "*");
                    showModeles();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 6:
                    break menuModel;
            }
        }
    }

    public void showModeles(){
        List<Model> statuses = modelService.getAllModels();
        System.out.println("Listado de estados:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "ESTADOS"));
        statuses.forEach(country -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        System.out.println();
    }

    public void showManufactureres(){
        List<Manufacturer> manufacturers = modelService.getAllManufacturers();
        System.out.println("Listado de fabricantes:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "FABRICANTE"));
        manufacturers.forEach(country -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        System.out.println();
    }
}
