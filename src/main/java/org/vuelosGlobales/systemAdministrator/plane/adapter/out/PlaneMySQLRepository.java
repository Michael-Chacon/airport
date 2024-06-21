package org.vuelosGlobales.systemAdministrator.plane.adapter.out;

import org.vuelosGlobales.systemAdministrator.plane.domain.Plane;
import org.vuelosGlobales.systemAdministrator.plane.infrastructure.PlaneRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaneMySQLRepository implements PlaneRepository {
    private final String url;
    private final String user;
    private final String password;

    public PlaneMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Plane plane) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO plane (plates, capacity, fabricationDate, idStatus, idModel) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, plane.getPlates());
                stm.setInt(2, plane.getCapacity());
                stm.setString(3, plane.getFabricationDate());
                stm.setInt(4, plane.getIdStatus());
                stm.setInt(5, plane.getIdModel());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Plane plane) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE plane SET plates = ?, capacity = ?, fabricationDate = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, plane.getPlates());
                stm.setInt(2, plane.getCapacity());
                stm.setString(3, plane.getFabricationDate());
                stm.setInt(4, plane.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Plane> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, plates, capacity, fabricationDate, idStatus, idModel FROM plane WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Plane obj = new Plane(resultSet.getInt("id"), resultSet.getString("plates"),
                                resultSet.getInt("capacity"), resultSet.getString("fabricationDate"),
                                resultSet.getInt("idStatus"), resultSet.getInt("idModel"));
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
    public List<Plane> findAll() {
        List<Plane> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, plates, capacity, fabricationDate, idStatus, idModel FROM plane";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Plane plane = new Plane(resultSet.getInt("id"), resultSet.getString("plates"),
                            resultSet.getInt("capacity"), resultSet.getString("fabricationDate"),
                            resultSet.getInt("idStatus"), resultSet.getInt("idModel"));
                    objects.add(plane);
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
            String query = "DELETE FROM plane WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
