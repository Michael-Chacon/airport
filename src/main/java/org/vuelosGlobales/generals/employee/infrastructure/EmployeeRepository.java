package org.vuelosGlobales.generals.employee.infrastructure;

import org.vuelosGlobales.generals.employee.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    Optional<Employee> findById(int id);
    List<Employee> findAll();
    void delete(int id);
}
