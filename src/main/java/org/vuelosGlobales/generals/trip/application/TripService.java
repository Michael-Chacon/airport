package org.vuelosGlobales.generals.trip.application;

import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.infrastructure.TripRepository;

import java.util.List;
import java.util.Optional;

public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void createTrip(Trip trip){
        this.tripRepository.save(trip);
    }

    public void updateTrip(Trip trip){
        this.tripRepository.update(trip);
    }

    public Optional<Trip> getTripById(int id){
        return this.tripRepository.findById(id);
    }

    public List<Trip> getAllTrips(){
        return this.tripRepository.findAll();
    }

    public void deleteTrip(int id){
        this.tripRepository.delete(id);
    }
}
