package org.vuelosGlobales.generals.trip.adapter.out;

import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.infrastructure.TripRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripMySQLRepository implements TripRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Trip trip) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO trip (tripDate, priceTripe) VALUES (?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, trip.getTripDate());
                stm.setDouble(2, trip.getPriceTrip());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Trip trip) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE trip SET tripDate = ?, priceTrip = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, trip.getTripDate());
                stm.setDouble(2, trip.getPriceTrip());
                stm.setInt(3, trip.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Trip> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, tripDate, priceTripe FROM trip WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Trip obj = new Trip(resultSet.getInt("id"), resultSet.getString("tripDate"),
                                resultSet.getDouble("priceTripe"));
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
    public List<Trip> findAll() {
        List<Trip> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, id, tripDate, priceTripe FROM trip";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Trip trip = new Trip(resultSet.getInt("id"), resultSet.getString("tripDate"),
                            resultSet.getDouble("priceTripe"));
                    objects.add(trip);
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
            String query = "DELETE FROM trip WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
