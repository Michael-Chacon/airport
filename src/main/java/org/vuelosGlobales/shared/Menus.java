package org.vuelosGlobales.shared;

import org.vuelosGlobales.generals.city.adapter.in.CityConsoleAdapter;
import org.vuelosGlobales.generals.city.adapter.out.CityMySQLRepository;
import org.vuelosGlobales.generals.city.application.CityService;
import org.vuelosGlobales.generals.country.adapter.in.CountryConsoleAdapter;
import org.vuelosGlobales.generals.country.adapter.out.CountryMySQLRepository;
import org.vuelosGlobales.generals.country.application.CountryService;
import org.vuelosGlobales.generals.manufacturer.adapter.in.ManufacturerConsoleAdap;
import org.vuelosGlobales.generals.manufacturer.adapter.out.ManufacturerMySQLRepository;
import org.vuelosGlobales.generals.manufacturer.application.ManufacturerService;
import org.vuelosGlobales.generals.status.adapter.in.StatusConsoleAdapter;
import org.vuelosGlobales.generals.status.adapter.out.StatusMySQLRepository;
import org.vuelosGlobales.generals.status.application.StatusService;

public class Menus {
    public static void main(String[] args) {
        CountryMySQLRepository countryOut = new CountryMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CountryService countryService = new CountryService(countryOut);
        CountryConsoleAdapter countryIn = new CountryConsoleAdapter(countryService);
//        city
        CityMySQLRepository cityOut = new CityMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        CityService cityService = new CityService(cityOut, countryOut);
        CityConsoleAdapter cityIn = new CityConsoleAdapter(cityService);
//        Status
        StatusMySQLRepository statusOut = new StatusMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        StatusService statusService = new StatusService(statusOut);
        StatusConsoleAdapter statusIn = new StatusConsoleAdapter(statusService);
//      manufacturers
        ManufacturerMySQLRepository manufacturerOut = new ManufacturerMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        ManufacturerService manufacturerService = new ManufacturerService(manufacturerOut);
        ManufacturerConsoleAdap manufacturerIn = new ManufacturerConsoleAdap(manufacturerService);

        manufacturerIn.crudManufacturer();
//        statusIn.crudStatus();
//        cityIn.crudCity();
//        countryIn.crudCountry();
    }

}
