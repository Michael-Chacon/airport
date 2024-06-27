package org.vuelosGlobales.generals.manufacturer.adapter.in;

import org.vuelosGlobales.generals.manufacturer.application.ManufacturerService;
import org.vuelosGlobales.generals.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;

import java.util.List;

public class ManufacturerConsoleAdap {
    private final ManufacturerService manufacturerService;
    Console console = new Console();
    public ManufacturerConsoleAdap(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }
    
    public void crudManufacturer(){
        menuManufacturer: while (true){
            System.out.println("======================================");
            System.out.println("       MENÚ DE GESTIÓN DE FABRICANTES  ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Fabricante");
            System.out.println("\t2. Actualizar Fabricante");
            System.out.println("\t3. Buscar Fabricante por ID");
            System.out.println("\t4. Eliminar Fabricante");
            System.out.println("\t5. Listar todos los Fabricantes");
            System.out.println("\t6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar Fabricante del avión", "*");
                    String name = console.stringNotNull("Nombre del fabricante: ");
                    Manufacturer st = new Manufacturer();
                    st.setName(name);
                    manufacturerService.createManufacturer(st);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de un fabricante", "*");
                    showManufactureres();
                    Manufacturer manufacturerSelect = Helpers.transformAndValidateObj(
                            () -> manufacturerService.getManufacturerById(console.readInt("Seleccione el fabricante por su ID: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + manufacturerSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del fabricante: ");
                    manufacturerSelect.setName(newName);
                    manufacturerService.updateManufacturer(manufacturerSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un fabricante", "*");
                    System.out.println();
                    showManufactureres();
                    Manufacturer showManufacturer = Helpers.transformAndValidateObj(
                            () -> manufacturerService.getManufacturerById(console.readInt("Seleccione el estado por el id: "))
                    );
                    System.out.println(showManufacturer);
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un fabricante", "*");
                    showManufactureres();
                    Manufacturer showManufacturerF = Helpers.transformAndValidateObj(
                            () -> manufacturerService.getManufacturerById(console.readInt("Seleccione el fabricante por el id: "))
                    );
                    int manufacturerDelete = showManufacturerF.getId();
                    manufacturerService.deleteManufacturer(manufacturerDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Fabricante eliminado con éxito", null);
                    break;

                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Fabricantes registrados", "*");
                    showManufactureres();
                    System.out.println();
                    break;

                case 6:
                    break menuManufacturer;
            }
        }
    }

    public void showManufactureres(){
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "FABRICANTE"));
        manufacturers.forEach(country -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }  

}
