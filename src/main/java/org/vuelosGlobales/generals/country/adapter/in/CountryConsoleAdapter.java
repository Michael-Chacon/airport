package org.vuelosGlobales.generals.country.adapter.in;

import org.vuelosGlobales.generals.country.application.CountryService;
import org.vuelosGlobales.generals.country.domain.Country;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;

import java.util.List;
import java.util.Optional;

public class CountryConsoleAdapter {
    private final CountryService countryService;
    Console console = new Console();
    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    public  void crudCountry(){
        menuCountry: while (true){
            System.out.println("======================================");
            System.out.println("             MENÚ DE PAÍSES           ");
            System.out.println("======================================");
            System.out.println("\t1. Crear País");
            System.out.println("\t2. Actualizar País");
            System.out.println("\t3. Buscar País por ID");
            System.out.println("\t4. Eliminar País");
            System.out.println("\t5. Listar todos los Países");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar pais", "*");
                    String name = console.stringNotNull("Nombre del pais: ");
                    String idCountry = Helpers.validateExist(
                        "Ingrese el ID del país (alfanumérico, máximo 5 caracteres): ",
                        id -> countryService.getCountryById(id)
                    );
                    countryService.createCountry(new Country(idCountry.toUpperCase(),name));
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de un país", "*");
                    showCountries();
                    Country countrySelect = Helpers.transformAndValidateObj(
                            () -> countryService.getCountryById(console.stringNotNull("Seleccione el pais por el id: ").toUpperCase())
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + countrySelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del pais: ");
                    countrySelect.setName(newName);
                    countryService.updateCountry(countrySelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, "-");
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un país", "*");
                    System.out.println();
                    showCountries();
                    Country showCountry = Helpers.transformAndValidateObj(
                            () -> countryService.getCountryById(console.stringNotNull("Seleccione el pais por el id: ").toUpperCase())
                    );
                    System.out.println(showCountry);
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un pais", "*");
                    showCountries();
                    Country showCountryF = Helpers.transformAndValidateObj(
                            () -> countryService.getCountryById(console.stringNotNull("Seleccione el pais por el id: ").toUpperCase())
                    );
                    String countryDelete = showCountryF.getId();
                    countryService.deleteCountry(countryDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Pais eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Paises registrados", "*");
                    showCountries();
                    System.out.println();
                    break;
                case 6:
                    break menuCountry;
            }
        }
    }

    public void showCountries(){
        List<Country> countryList = countryService.getAllCoutries();
        System.out.println("Listado de paises:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "PAIS"));
        countryList.forEach(country -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }

//    public Country transformAndValidateObj(){
//        Country countrySelect = null;
//        while (true){
//            String countryx = console.stringNotNull("Seleccione el país por el id: ");
//            Optional<Country> getCountry = countryService.getCountryById(countryx.toUpperCase());
//            if(!getCountry.isEmpty()){
//                System.out.println();
//                return countrySelect = getCountry.get();
//            }else {
//                System.out.println("El id no existe");
//            }
//        }
//    }
}
