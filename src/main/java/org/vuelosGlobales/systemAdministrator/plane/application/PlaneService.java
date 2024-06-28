package org.vuelosGlobales.systemAdministrator.plane.application;

import org.vuelosGlobales.generals.model.domain.Model;
import org.vuelosGlobales.generals.model.infrastructure.ModelRepository;
import org.vuelosGlobales.generals.status.domain.Status;
import org.vuelosGlobales.generals.status.infrastructure.StatusRepository;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.systemAdministrator.airline.infrastructure.AirlineRepository;
import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.systemAdministrator.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class PlaneService {
    private  PlaneRepository planeRepository;
    private  StatusRepository statusRepository;
    private  ModelRepository modelRepository;
    private  AirlineRepository airlineRepository;

    public PlaneService(PlaneRepository planeRepository, StatusRepository statusRepository, ModelRepository modelRepository, AirlineRepository airlineRepository) {
        this.planeRepository = planeRepository;
        this.statusRepository = statusRepository;
        this.modelRepository = modelRepository;
        this.airlineRepository = airlineRepository;
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

    public List<PlaneStMdDTO> getAllPlaneStMd(boolean filter, int id){
        return this.planeRepository.findAllPlaneStMd(filter, id);
    }
    public Optional<PlaneStMdDTO> getPlaneStMdById(int id){
        return this.planeRepository.findPlaneStMdById(id);
    }

    public List<Airline> getAllAirlines(){
        return this.airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(int id){
        return this.airlineRepository.findById(id);
    }
    public Optional<Plane> validatePlate(String plate){
        return this.planeRepository.validatePlate(plate);
    }
}
