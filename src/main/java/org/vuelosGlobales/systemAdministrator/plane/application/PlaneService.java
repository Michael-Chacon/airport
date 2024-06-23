package org.vuelosGlobales.systemAdministrator.plane.application;

import org.vuelosGlobales.generals.model.domain.Model;
import org.vuelosGlobales.generals.model.infrastructure.ModelRepository;
import org.vuelosGlobales.generals.status.domain.Status;
import org.vuelosGlobales.generals.status.infrastructure.StatusRepository;
import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.systemAdministrator.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class PlaneService {
    private final PlaneRepository planeRepository;
    private final StatusRepository statusRepository;
    private final ModelRepository modelRepository;

    public PlaneService(PlaneRepository planeRepository, StatusRepository statusRepository, ModelRepository modelRepository) {
        this.planeRepository = planeRepository;
        this.statusRepository = statusRepository;
        this.modelRepository = modelRepository;
    }

    public void createPlane(Plane plane){
        this.planeRepository.save(plane);
    }

    public void updatePlane(Plane plane){
        this.planeRepository.update(plane);
    }

    public Optional<Plane> getPlaneById(int id){
        return this.planeRepository.findById(id);
    }

    public List<Plane> getAllPlanes(){
        return this.planeRepository.findAll();
    }

    public void deletePlane(int id){
        this.planeRepository.delete(id);
    }

    public List<Status> getAllStatus(){
        return this.statusRepository.findAll();
    }
    public Optional<Status> getStatusById(int id){
        return this.statusRepository.findById(id);
    }

    public List<Model> getAllModels(){
        return this.modelRepository.findAll();
    }

    public Optional<Model> getModelById(int id){
        return this.modelRepository.findById(id);
    }

    public List<PlaneStMdDTO> getAllPlaneStMd(){
        return this.planeRepository.findAllPlaneStMd();
    }
    public Optional<PlaneStMdDTO> getPlaneStMdById(int id){
        return this.planeRepository.findPlaneStMdById(id);
    }

}
