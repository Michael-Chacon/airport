package org.vuelosGlobales.generals.city.adapter.in;

import org.vuelosGlobales.generals.city.application.CityService;
import org.vuelosGlobales.generals.city.domain.City;
import org.vuelosGlobales.generals.country.domain.Country;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.w3c.dom.ls.LSOutput;

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
            System.out.println("1. Crear Ciudad");
            System.out.println("2. Actualizar Ciudad");
            System.out.println("3. Buscar Ciudad por ID");
            System.out.println("4. Eliminar Ciudad");
            System.out.println("5. Listar todos Ciudades");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar ciudad", "*");
                    showCountries();
                    String idCountry = transformAndValidateObj().getId();
                    String name = console.stringNotNull("Nombre del ciudad: ");
                    String idCity = console.stringWithLeght("Ingrese el id del ciudad, debe ser alfanumérico de máximo 5 caracteres: ", 5);
                    cityService.createCity(new City(idCity.toUpperCase(), name, idCountry));
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una ciudad", "*");
                    showCities();
                    City citySelect = transformAndValidateCity();
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + citySelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del ciudad: ");
                    String idCountryCity;
                    String validate = console.stringNotNull("Quiere cambiar el país al que pertenece la ciudad? (y/n)");
                    if (validate.equals("y")){
                        showCountries();
                        idCountryCity = transformAndValidateObj().getId();
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
                    City showCity = transformAndValidateCity();
                    System.out.println(showCity);
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un ciudad", "*");
                    showCities();
                    String cityDelete = transformAndValidateCity().getId();
                    cityService.deleteCity(cityDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Ciudad eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Ciudades registradas", "*");
                    showCities();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;
                case 6:
                    break menuCity;
            }
            String option = console.stringNull("Algo: ");
        }
    }

    public void showCities(){
        List<City> cityList = cityService.getAllCities();
        System.out.println("Listado de ciudades:");
        CuadroDeTexto.drawHorizontal(28, "-");
        System.out.println(String.format("\n| %-6s | %-17s |", "ID", "CIUDAD"));
        cityList.forEach(city -> {
            CuadroDeTexto.drawHorizontal(28, "-");
            System.out.println(String.format("\n| %-6s | %-17s |", city.getId(), city.getName()));
        });
        System.out.println();
    }

    public void showCountries(){
        List<Country> countryList = cityService.getAllCoutries();
        System.out.println("Listado de paises:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "PAIS"));
        countryList.forEach(country -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        System.out.println();
    }

    public City transformAndValidateCity(){
        City citySelect = null;
        while (true){
            String idCity = console.stringNotNull("Seleccione la ciudad por el id: ");
            Optional<City> getCity = cityService.getCityById(idCity.toUpperCase());
            if(!getCity.isEmpty()){
                System.out.println();
                return citySelect = getCity.get();
            }else {
                System.out.println("El id no existe");
            }
        }
    }

    public Country transformAndValidateObj(){
        Country countrySelect = null;
        while (true){
            String countryx = console.stringNotNull("A que país pertenece la ciudad, seleccione por el por el id: ");
            Optional<Country> getCountry = cityService.getCountryById(countryx.toUpperCase());
            if(!getCountry.isEmpty()){
                System.out.println();
                return countrySelect = getCountry.get();
            }else {
                System.out.println("El id no existe");
            }
        }
    }
}
