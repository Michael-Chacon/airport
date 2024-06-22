package org.vuelosGlobales.generals.model.adapter.out;

import org.vuelosGlobales.generals.model.domain.Model;
import org.vuelosGlobales.generals.model.infrastructure.ModelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModelMySQLRepository implements ModelRepository {
    private final String url;
    private final String user;
    private final String password;

    public ModelMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Model model) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO model (name) VALUES (?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, model.getName());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Model model) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE model SET name = ?, manufacturerId = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, model.getName());
                stm.setInt(2, model.getManufacturerId());
                stm.setInt(3, model.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Model> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, manufacturerId FROM model WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Model obj = new Model(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("manufacturerId"));
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
    public List<Model> findAll() {
        List<Model> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, manufacturerId FROM model";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Model model = new Model(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("manufacturerId"));
                    objects.add(model);
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
            String query = "DELETE FROM model WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
