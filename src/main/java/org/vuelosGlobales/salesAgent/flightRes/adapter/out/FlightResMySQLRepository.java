package org.vuelosGlobales.salesAgent.flightRes.adapter.out;

import org.vuelosGlobales.salesAgent.flightRes.domain.FlightRes;
import org.vuelosGlobales.salesAgent.flightRes.infrastructure.FlightResRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightResMySQLRepository implements FlightResRepository {
    private final String url;
    private final String user;
    private final String password;

    public FlightResMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(FlightRes flightRes) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO tripbooking (date) VALUES (?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, flightRes.getDate());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FlightRes flightRes) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE tripbooking SET date = ?, idTrip = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, flightRes.getDate());
                stm.setInt(2, flightRes.getIdTrip());
                stm.setInt(2, flightRes.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FlightRes> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, date, idTrip FROM tripbooking WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        FlightRes obj = new FlightRes(resultSet.getInt("id"), resultSet.getString("date"),
                                resultSet.getInt("idTrip"));
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
    public List<FlightRes> findAll() {
        List<FlightRes> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, date, idTrip FROM tripbooking";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    FlightRes flightRes = new FlightRes(resultSet.getInt("id"), resultSet.getString("date"),
                            resultSet.getInt("idTrip"));
                    objects.add(flightRes);
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
            String query = "DELETE FROM tripbooking WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
