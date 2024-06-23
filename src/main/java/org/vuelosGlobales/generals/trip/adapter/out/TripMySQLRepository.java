package org.vuelosGlobales.generals.trip.adapter.out;

import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
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
            String query = "INSERT INTO trip (tripDate, priceTripe, idOrigin, idDestination) VALUES (?,?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, trip.getTripDate());
                stm.setDouble(2, trip.getPriceTrip());
                stm.setString(3, trip.getIdOrigin());
                stm.setString(4, trip.getIdDestination());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Trip trip) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE trip SET tripDate = ?, priceTrip = ?, idOrigin = ?, idDestination = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, trip.getTripDate());
                stm.setDouble(2, trip.getPriceTrip());
                stm.setString(3, trip.getIdOrigin());
                stm.setString(4, trip.getIdDestination());
                stm.setInt(5, trip.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Trip> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, tripDate, priceTripe, idOrigin, idDestination FROM trip WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Trip obj = new Trip(resultSet.getInt("id"), resultSet.getString("tripDate"),
                                resultSet.getDouble("priceTripe"), resultSet.getString("idOrigin"),
                                resultSet.getString("idDestination"));
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
            String query = "SELECT id, tripDate, priceTripe, idOrigin, idDestination FROM trip";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Trip trip = new Trip(resultSet.getInt("id"), resultSet.getString("tripDate"),
                            resultSet.getDouble("priceTripe"), resultSet.getString("idOrigin"),
                            resultSet.getString("idDestination"));
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

    @Override
    public List<TripAirportDTO> findAllTripAirport() {
        List<TripAirportDTO> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT t.id, t.tripDate, t.priceTrip, CONCAT_WS(' - ', co.name, ao.name) as 'origin', CONCAT_WS(' - ', cd.name, ad.name) as 'destination' FROM trip t" +
                    "INNER JOIN airport ao on ao.id = t.idOrigin" +
                    "INNER JOIN city co on co.id = ao.idCity" +
                    "INNER JOIN airport ad on ad.id = t.idDestination" +
                    "INNER JOIN city cd on cd.id = ad.idCity";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    TripAirportDTO trip = new TripAirportDTO(resultSet.getInt("id"), resultSet.getString("tripDate"),
                            resultSet.getDouble("priceTripe"), resultSet.getString("origin"),
                            resultSet.getString("destination"));
                    objects.add(trip);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public Optional<TripAirportDTO> findTripAirportById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT t.id, t.tripDate, t.priceTrip, CONCAT_WS(' - ', co.name, ao.name) as 'origin', CONCAT_WS(' - ', cd.name, ad.name) as 'destination' FROM trip t" +
                    "INNER JOIN airport ao on ao.id = t.idOrigin" +
                    "INNER JOIN city co on co.id = ao.idCity" +
                    "INNER JOIN airport ad on ad.id = t.idDestination" +
                    "INNER JOIN city cd on cd.id = ad.idCity WHERE t.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()){
                    TripAirportDTO trip = new TripAirportDTO(resultSet.getInt("id"), resultSet.getString("tripDate"),
                            resultSet.getDouble("priceTripe"), resultSet.getString("origin"),
                            resultSet.getString("destination"));
                    return Optional.of(trip);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
