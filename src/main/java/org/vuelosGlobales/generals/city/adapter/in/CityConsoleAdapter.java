package org.vuelosGlobales.generals.city.adapter.in;

import org.vuelosGlobales.generals.city.application.CityService;
import org.vuelosGlobales.generals.city.domain.City;
import org.vuelosGlobales.generals.city.domain.CityCountryDTO;
import org.vuelosGlobales.generals.country.domain.Country;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;

import java.util.List;
import java.util.Optional;

public class CityConsoleAdapter {
    private final CityService cityService;
    Console console = new Console();

    public CityConsoleAdapter(CityService cityService) {
        this.cityService = cityService;
    }

    public  void crudCity(){
        menuCity: while (true){
            System.out.println("======================================");
            System.out.println("            MENÚ DE CIUDADES          ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Ciudad");
            System.out.println("\t2. Actualizar Ciudad");
            System.out.println("\t3. Buscar Ciudad por ID");
            System.out.println("\t4. Eliminar Ciudad");
            System.out.println("\t5. Listar todas las Ciudades");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar ciudad", "*");
                    showCountries();

                    Country getObj = Helpers.transformAndValidateObj(
                            () -> cityService.getCountryById(console.stringNotNull("¿A qué país pertenece la ciudad? Seleccione por el ID: ").toUpperCase())
                    );

                    String idCountry = getObj.getId();
                    String name = console.stringNotNull("Nombre del ciudad: ");
                    String idCity = Helpers.validateExist(
                            "Ingrese el ID de la ciudad (alfanumérico, máximo 5 caracteres): ",
                            id -> cityService.getCityById(id)
                    );
                    cityService.createCity(new City(idCity.toUpperCase(), name, idCountry));
                    CuadroDeTexto.dibujarCuadroDeTexto(null, "-");
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una ciudad", "*");
                    showCities();

                    City citySelect = Helpers.transformAndValidateObj(
                            () -> cityService.getCityById(console.stringNotNull("Seleccione la ciudad por el id: ").toUpperCase())
                    );

                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + citySelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del ciudad: ");
                    String idCountryCity;
                    String validate = console.stringNotNull("Quiere cambiar el país al que pertenece la ciudad? (y/n)");
                    if (validate.equals("y")){
                        showCountries();
                        Country getObjP = Helpers.transformAndValidateObj(
                                () -> cityService.getCountryById(console.stringNotNull("A que país pertenece la ciudad, seleccione por el por el id: ").toUpperCase())
                        );
                        idCountryCity = getObjP.getId();
                    }else {
                        idCountryCity = citySelect.getIdCountry();
                    }
                    citySelect.setName(newName);
                    citySelect.setIdCountry(idCountryCity);
                    cityService.updateCity(citySelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de una ciudad", "*");
                    System.out.println();
                    showCities();
                    City showCity = Helpers.transformAndValidateObj(
                        () -> cityService.getCityById(console.stringNotNull("Seleccione la ciudad por el id: ").toUpperCase())
                    );
                    CuadroDeTexto.drawHorizontal(20, "-");
                    System.out.println(showCity);
                    CuadroDeTexto.drawHorizontal(20, "-");

                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un ciudad", "*");
                    showCities();
                    City showCityD = Helpers.transformAndValidateObj(
                            () -> cityService.getCityById(console.stringNotNull("Seleccione la ciudad por el id: ").toUpperCase())
                    );
                    String cityDelete = showCityD.getId();
                    cityService.deleteCity(cityDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Ciudad eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Ciudades registradas", "*");
                    showCities();
                    System.out.println();
                    break;
                case 6:
                    break menuCity;
            }
        }
    }

    public void showCities(){
        List<CityCountryDTO> cityList = cityService.getCityWithCountry();
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.println(String.format("\n| %-6s | %-17s | %-17s |", "ID", "CIUDAD", "PAIS"));
        cityList.forEach(city -> {
            CuadroDeTexto.drawHorizontal(50, "-");
            System.out.println(String.format("\n| %-6s | %-17s | %-17s |", city.getId(), city.getNameCity(), city.getNameCountry()));
        });
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.println();
    }

    public void showCountries(){      List<Country> countryList = cityService.getAllCoutries();
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

}
