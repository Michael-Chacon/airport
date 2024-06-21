package org.vuelosGlobales.salesAgent.customer.adapter.out;

import org.vuelosGlobales.salesAgent.customer.domain.Customer;
import org.vuelosGlobales.salesAgent.customer.infrastructure.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerMySQLRepository implements CustomerRepository {
    private final String url;
    private final String user;
    private final String password;

    public CustomerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Customer customer) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO customer (name, age, idDocument) VALUES (?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, customer.getName());
                stm.setInt(2, customer.getAge());
                stm.setInt(3, customer.getIdDocument());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE customer SET name = ?, age = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, customer.getName());
                stm.setInt(2, customer.getAge());
                stm.setInt(3, customer.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, age, idDocument FROM customer WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Customer obj = new Customer(resultSet.getInt("id"), resultSet.getString("name"),
                                resultSet.getInt("age"), resultSet.getInt("idDocument"));
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
    public List<Customer> findAll() {
        List<Customer> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, age, idDocument FROM customer";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getInt("age"), resultSet.getInt("idDocument"));
                    objects.add(customer);
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
            String query = "DELETE FROM customer WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
