package org.vuelosGlobales.maintenanceTechnician.revision.application;

import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;
import org.vuelosGlobales.maintenanceTechnician.revision.infrastructure.RevisionRepository;

import java.util.List;
import java.util.Optional;

public class RevisionService {
    private final RevisionRepository revisionRepository;

    public RevisionService(RevisionRepository revisionRepository) {
        this.revisionRepository = revisionRepository;
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
}
