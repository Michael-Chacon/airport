package org.vuelosGlobales.generals.city.application;

import org.vuelosGlobales.generals.city.infrastructure.CityRepository;
import org.vuelosGlobales.generals.city.domain.City;

import java.util.List;
import java.util.Optional;

public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity(City city){
        this.cityRepository.save(city);
    }

    public void updateCity(City city){
        this.cityRepository.update(city);
    }

    public Optional<City> getCityById(String id){
        return this.cityRepository.findById(id);
    }

    public List<City> getAllCities(){
        return this.cityRepository.findAll();
    }

    public void deleteCity(String id){
        this.cityRepository.delete(id);
    }
}
