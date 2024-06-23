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
import org.vuelosGlobales.generals.model.adapter.in.ModelConsoleAdap;
import org.vuelosGlobales.generals.model.adapter.out.ModelMySQLRepository;
import org.vuelosGlobales.generals.model.application.ModelService;
import org.vuelosGlobales.generals.role.adapter.in.RoleConsoleAdap;
import org.vuelosGlobales.generals.role.adapter.out.RoleMySQLRepository;
import org.vuelosGlobales.generals.role.application.RoleService;
import org.vuelosGlobales.generals.status.adapter.in.StatusConsoleAdapter;
import org.vuelosGlobales.generals.status.adapter.out.StatusMySQLRepository;
import org.vuelosGlobales.generals.status.application.StatusService;
import org.vuelosGlobales.systemAdministrator.airline.adapter.in.AirlineConsoleAdap;
import org.vuelosGlobales.systemAdministrator.airline.adapter.out.AirlineMySQLRepository;
import org.vuelosGlobales.systemAdministrator.airline.application.AirlineService;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;

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
//      Model
        ModelMySQLRepository modelOut = new ModelMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        ModelService modelService = new ModelService(modelOut, manufacturerOut);
        ModelConsoleAdap modelIn = new ModelConsoleAdap(modelService);
//        Airline
        AirlineMySQLRepository airlineOut = new AirlineMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        AirlineService airlineService = new AirlineService(airlineOut);
        AirlineConsoleAdap airlineIn = new AirlineConsoleAdap(airlineService);
//       Role
        RoleMySQLRepository roleOut = new RoleMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
        RoleService roleService = new RoleService(roleOut);
        RoleConsoleAdap roleIn = new RoleConsoleAdap(roleService);


        roleIn.crudRole();
//        airlineIn.crudAirline();
//        modelIn.crudModel();
//        manufacturerIn.crudManufacturer();
//        statusIn.crudStatus();
//        cityIn.crudCity();
//        countryIn.crudCountry();
    }

}
