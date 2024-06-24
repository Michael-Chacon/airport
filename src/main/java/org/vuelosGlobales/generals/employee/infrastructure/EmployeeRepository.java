package org.vuelosGlobales.generals.employee.infrastructure;

import org.vuelosGlobales.generals.employee.domain.Employee;
import org.vuelosGlobales.generals.employee.domain.EmployeeRelationshipDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    Optional<Employee> findById(String id);
    List<Employee> findAll();
    void delete(String id);

    List<EmployeeRelationshipDTO> findAllEmployeesInfo();
    Optional<EmployeeRelationshipDTO> findEmployeeInfoById(String id);

}
