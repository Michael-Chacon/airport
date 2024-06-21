package org.vuelosGlobales.generals.manufacturer.application;

import org.vuelosGlobales.generals.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.generals.manufacturer.infrastructure.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public void createManufacturer(Manufacturer manufacturer){
        this.manufacturerRepository.save(manufacturer);
    }

    public void updateManufacturer(Manufacturer manufacturer){
        this.manufacturerRepository.update(manufacturer);
    }

    public Optional<Manufacturer> getManufacturerById(int id){
        return this.manufacturerRepository.findById(id);
    }

    public List<Manufacturer> getAllManufacturers(){
        return this.manufacturerRepository.findAll();
    }

    public void deleteManufacturer(int id){
        this.manufacturerRepository.delete(id);
    }
}
