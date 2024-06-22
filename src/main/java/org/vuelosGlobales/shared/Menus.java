package org.vuelosGlobales.shared;

import org.vuelosGlobales.generals.city.adapter.in.CityConsoleAdapter;
import org.vuelosGlobales.generals.city.adapter.out.CityMySQLRepository;
import org.vuelosGlobales.generals.city.application.CityService;
import org.vuelosGlobales.generals.country.adapter.in.CountryConsoleAdapter;
import org.vuelosGlobales.generals.country.adapter.out.CountryMySQLRepository;
import org.vuelosGlobales.generals.country.application.CountryService;

public class Menus {
    public static void main(String[] args) {
        CountryMySQLRepository countryOut = new CountryMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CountryService countryService = new CountryService(countryOut);
        CountryConsoleAdapter countryIn = new CountryConsoleAdapter(countryService);
//        city
        CityMySQLRepository cityOut = new CityMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CityService cityService = new CityService(cityOut, countryOut);
        CityConsoleAdapter cityIn = new CityConsoleAdapter(cityService);
//        cityIn.crudCity();
        countryIn.crudCountry();
    }

}
