package org.vuelosGlobales.salesAgent.flightRes.infrastructure;

import org.vuelosGlobales.salesAgent.flightRes.domain.FlightRes;

import java.util.List;
import java.util.Optional;

public interface FlightResRepository {
    int save(FlightRes flightRes);
    void update(FlightRes flightRes);
    Optional<FlightRes> findById(int id);
    List<FlightRes> findAll();
    void delete(int id);

    int findPlaneSeats(int idTrip);
}
