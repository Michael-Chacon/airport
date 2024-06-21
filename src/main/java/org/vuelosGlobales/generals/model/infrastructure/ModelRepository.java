package org.vuelosGlobales.generals.model.infrastructure;

import org.vuelosGlobales.generals.model.domain.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    void save(Model model);
    void update(Model model);
    Optional<Model> findById(int id);
    List<Model> findAll();
    void delete(int id);
}
