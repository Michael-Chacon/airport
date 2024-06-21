package org.vuelosGlobales.systemAdministrator.plane.application;

import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.systemAdministrator.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class PlaneService {
    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
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
}
