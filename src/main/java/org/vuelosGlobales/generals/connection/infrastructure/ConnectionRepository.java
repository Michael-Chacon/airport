package org.vuelosGlobales.generals.connection.infrastructure;

import org.vuelosGlobales.generals.connection.domain.Connections;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    void save(Connections connections);
    void update(Connections connections);
    Optional<Connections> findById(int id);
    List<Connections> findAll();
    void delete(int id);
}
