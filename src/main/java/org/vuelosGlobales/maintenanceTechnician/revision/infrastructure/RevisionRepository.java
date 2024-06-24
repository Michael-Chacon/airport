package org.vuelosGlobales.maintenanceTechnician.revision.infrastructure;

import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;

import java.util.List;
import java.util.Optional;

public interface RevisionRepository {
    void save(Revision revision);
    void update(Revision revision);
    Optional<Revision> findById(int id);
    List<Revision> findAll();
    void delete(int id);
    List<Revision> findByIdPlane(int id);
}
