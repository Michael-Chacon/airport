package org.vuelosGlobales.generals.employee.application;

import org.vuelosGlobales.generals.employee.domain.Employee;
import org.vuelosGlobales.generals.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.generals.employee.infrastructure.EmployeeRepository;
import org.vuelosGlobales.generals.role.domain.Role;
import org.vuelosGlobales.generals.role.infrastrustura.RoleRepository;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.systemAdministrator.airline.infrastructure.AirlineRepository;
import org.vuelosGlobales.systemAdministrator.airport.domain.Airport;
import org.vuelosGlobales.systemAdministrator.airport.domain.AirportCityDTO;
import org.vuelosGlobales.systemAdministrator.airport.infrastructure.AirportRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, AirlineRepository airlineRepository, AirportRepository airportRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
    }

    public void createEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee){
        this.employeeRepository.update(employee);
    }

    public Optional<Employee> getEmployeeById(String id){
        return this.employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public void deleteEmployee(String id){
        this.employeeRepository.delete(id);
    }

    public List<Role> getAllRoles(){
        return this.roleRepository.findAll();
    }

    public Optional<Role> getRoleById(int id){
        return this.roleRepository.findById(id);
    }

    public List<Airline> getAllAirlines(){
        return this.airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(int id){
        return this.airlineRepository.findById(id);
    }

    public List<AirportCityDTO> getAllAirport(){
        return this.airportRepository.findAllAirportCity();
    }

    public Optional<AirportCityDTO> getAirportById(String id){
        return this.airportRepository.findAirportCityById(id);
    }

    public List<EmployeeRelationshipDTO> getAllEmployeesInfo(){
        return this.employeeRepository.findAllEmployeesInfo();
    }

    public Optional<EmployeeRelationshipDTO> getEmployeeInfoById(String id){
        return this.employeeRepository.findEmployeeInfoById(id);
    }
}
