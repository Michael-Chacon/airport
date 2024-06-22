package org.vuelosGlobales.generals.employee.application;

import org.vuelosGlobales.generals.employee.domain.Employee;
import org.vuelosGlobales.generals.employee.infrastructure.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee){
        this.employeeRepository.update(employee);
    }

    public Optional<Employee> getEmployeeById(int id){
        return this.employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public void deleteEmployee(int id){
        this.employeeRepository.delete(id);
    }
}
