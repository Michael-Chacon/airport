package org.vuelosGlobales.systemAdministrator.plane.infrastructure;

import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.systemAdministrator.plane.domain.PlaneStMdDTO;

import java.util.List;
import java.util.Optional;

public interface PlaneRepository {
    void save(Plane plane);
    void update(Plane plane);
    Optional<Plane> findById(int id);
    List<Plane> findAll();
    void delete(int id);
    List<PlaneStMdDTO> findAllPlaneStMd(boolean filter, int id);
    Optional<PlaneStMdDTO> findPlaneStMdById(int id);
    Optional<Plane> validatePlate(String plate);
}
