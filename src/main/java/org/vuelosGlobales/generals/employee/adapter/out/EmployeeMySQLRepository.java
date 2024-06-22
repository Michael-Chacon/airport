package org.vuelosGlobales.generals.employee.adapter.out;

import org.vuelosGlobales.generals.employee.domain.Employee;
import org.vuelosGlobales.generals.employee.infrastructure.EmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeMySQLRepository implements EmployeeRepository {

    private final String url;
    private final String user;
    private final String password;


    public EmployeeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Employee employee) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO employee (name, ingressDate, idRol, idAirline, idAirport) VALUES (?,?,?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, employee.getNombre());
                stm.setString(2, employee.getIngressDate());
                stm.setInt(3, employee.getIdRol());
                stm.setInt(4, employee.getIdAirline());
                stm.setInt(5, employee.getIdAirport());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE employee SET name = ?, ingressDate = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, employee.getNombre());
                stm.setString(2, employee.getIngressDate());
                stm.setString(3, employee.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT name, ingressDate, idRol, idAirline, idAirport FROM employee WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Employee obj = new Employee(resultSet.getString("id"), resultSet.getString("name"),
                                resultSet.getString("ingressDAte"), resultSet.getInt("idRol"),
                                resultSet.getInt("idAirline"), resultSet.getInt("idAirport"));
                        return Optional.of(obj);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT name, ingressDate, idRol, idAirline, idAirport FROM employee";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Employee employee = new Employee(resultSet.getString("id"), resultSet.getString("name"),
                            resultSet.getString("ingressDAte"), resultSet.getInt("idRol"),
                            resultSet.getInt("idAirline"), resultSet.getInt("idAirport"));
                    objects.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public void delete(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM employee WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
