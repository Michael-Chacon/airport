package org.vuelosGlobales.generals.manufacturer.adapter.out;

import org.vuelosGlobales.generals.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.generals.manufacturer.infrastructure.ManufacturerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManufacturerMySQLRepository implements ManufacturerRepository {
    private final String url;
    private final String user;
    private final String password;

    public ManufacturerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Manufacturer manufacturer) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO manufacturer (name) VALUES (?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, manufacturer.getName());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE manufacturer SET name = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, manufacturer.getName());
                stm.setInt(2, manufacturer.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Manufacturer> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM manufacturer WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Manufacturer obj = new Manufacturer(resultSet.getInt("id"), resultSet.getString("name"));
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
    public List<Manufacturer> findAll() {
        List<Manufacturer> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM manufacturer";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Manufacturer manufacturer = new Manufacturer(resultSet.getInt("id"), resultSet.getString("name"));
                    objects.add(manufacturer);
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
            String query = "DELETE FROM manufacturer WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
