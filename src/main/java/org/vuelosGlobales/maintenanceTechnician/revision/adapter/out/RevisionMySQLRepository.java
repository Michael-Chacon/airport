package org.vuelosGlobales.maintenanceTechnician.revision.adapter.out;

import org.vuelosGlobales.maintenanceTechnician.revision.domain.Revision;
import org.vuelosGlobales.maintenanceTechnician.revision.infrastructure.RevisionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RevisionMySQLRepository implements RevisionRepository {
    private final String url;
    private final String user;
    private final String password;

    public RevisionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Revision revision) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO revision (revisionDate, idPlane, description) VALUES (?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, revision.getRevisionDate());
                stm.setInt(2, revision.getIdPlane());
                stm.setString(3, revision.getDescription());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Revision revision) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE revision SET revisionDate = ?, idPlane = ?, description = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, revision.getRevisionDate());
                stm.setInt(2, revision.getIdPlane());
                stm.setString(3, revision.getDescription());
                stm.setInt(4, revision.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Revision> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, revisionDate, idPlane FROM revision WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Revision obj = new Revision(resultSet.getInt("id"), resultSet.getString("revisionDate"),
                                resultSet.getInt("idPlane"), resultSet.getString("description"));
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
    public List<Revision> findAll() {
        List<Revision> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, revisionDate, idPlane FROM revision";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Revision revision = new Revision(resultSet.getInt("id"), resultSet.getString("revisionDate"),
                            resultSet.getInt("idPlane"), resultSet.getString("description"));
                    objects.add(revision);
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
            String query = "DELETE FROM revision WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
