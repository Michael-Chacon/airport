package org.vuelosGlobales.maintenanceTechnician.revision.application;

import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;
import org.vuelosGlobales.maintenanceTechnician.revision.infrastructure.RevisionRepository;
import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.systemAdministrator.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class RevisionService {
    private final RevisionRepository revisionRepository;
    private final PlaneRepository planeRepository;

    public RevisionService(RevisionRepository revisionRepository, PlaneRepository planeRepository) {
        this.revisionRepository = revisionRepository;
        this.planeRepository = planeRepository;
    }

    public void createRevision(Revision revision){
        this.revisionRepository.save(revision);
    }

    public void updateRevision(Revision revision){
        this.revisionRepository.update(revision);
    }

    public Optional<Revision> getRevisionById(int id){
        return this.revisionRepository.findById(id);
    }

    public List<Revision> getAllRevisions(){
        return this.revisionRepository.findAll();
    }

    public void deleteRevision(int id){
        this.revisionRepository.delete(id);
    }

    public List<PlaneStMdDTO> getAllPlanes(){
        return this.planeRepository.findAllPlaneStMd();
    }

    public Optional<PlaneStMdDTO> getPlaneById(int id){
        return this.planeRepository.findPlaneStMdById(id);
    }

    public List<Revision> getRevisionsByIdPlane(int id){
        return this.revisionRepository.findByIdPlane(id);
    }
}
