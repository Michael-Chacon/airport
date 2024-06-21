package org.vuelosGlobales.systemAdministrator.airport.adapter.out;

import org.vuelosGlobales.systemAdministrator.airport.domain.Airport;
import org.vuelosGlobales.systemAdministrator.airport.infrastructure.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportMySQLRepository implements AirportRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirportMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airport airport) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO airport (name) VALUES (?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, airport.getName());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Airport airport) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE airport SET name = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, airport.getName());
                stm.setInt(2, airport.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Airport> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, idCity FROM airport WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Airport obj = new Airport(resultSet.getInt("id"), resultSet.getString("name"),
                                resultSet.getInt("idCity"));
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
    public List<Airport> findAll() {
        List<Airport> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, idCity FROM airport";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Airport airport = new Airport(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getInt("idCity"));
                    objects.add(airport);
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
            String query = "DELETE FROM airport WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
