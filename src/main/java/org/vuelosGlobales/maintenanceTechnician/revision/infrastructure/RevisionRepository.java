package org.vuelosGlobales.maintenanceTechnician.revision.infrastructure;

import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;
import org.vuelosGlobales.maintenanceTechnician.revision.domain.RevisionInfoDTO;

import java.util.List;
import java.util.Optional;

public interface RevisionRepository {
    int save(Revision revision);
    void update(Revision revision);
    Optional<Revision> findById(int id);
    List<Revision> findAll();
    void delete(int id);
    List<Revision> findByIdPlane(int id);
    void saveReviEmployee(int idRevision, String idEmployee);
    List<RevisionInfoDTO> findRevisionByPlane(int id);
}
