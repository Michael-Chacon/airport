package org.vuelosGlobales.generals.model.application;

import org.vuelosGlobales.generals.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.generals.manufacturer.infrastructure.ManufacturerRepository;
import org.vuelosGlobales.generals.model.domain.Model;
import org.vuelosGlobales.generals.model.infrastructure.ModelRepository;

import java.util.List;
import java.util.Optional;

public class ModelService {
    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ModelService(ModelRepository modelRepository, ManufacturerRepository manufacturerRepository) {
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public void createModel(Model model){
        this.modelRepository.save(model);
    }

    public void updateModel(Model model){
        this.modelRepository.update(model);
    }

    public Optional<Model> getModelById(int id){
        return this.modelRepository.findById(id);
    }

    public List<Model> getAllModels(){
        return this.modelRepository.findAll();
    }

    public void deleteModel(int id){
        this.modelRepository.delete(id);
    }
    public List<Manufacturer> getAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> getManufacturerById(int id){
        return manufacturerRepository.findById(id);
    }
}
