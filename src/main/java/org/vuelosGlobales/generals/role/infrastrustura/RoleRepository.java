package org.vuelosGlobales.generals.role.infrastrustura;

import org.vuelosGlobales.generals.role.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    void save(Role role);
    void update(Role role);
    Optional<Role> findById(int id);
    List<Role> findAll();
    void delete(int id);
}
