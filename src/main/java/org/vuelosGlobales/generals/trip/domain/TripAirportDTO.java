package org.vuelosGlobales.generals.trip.domain;

public class TripAirportDTO {
    private int id;
    private String tripDate;
    private double priceTrip;
    private String origin;
    private String destination;

    public TripAirportDTO() {
    }

    public TripAirportDTO(int id, String tripDate, double priceTrip, String origin, String destination) {
        this.id = id;
        this.tripDate = tripDate;
        this.priceTrip = priceTrip;
        this.origin = origin;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public double getPriceTrip() {
        return priceTrip;
    }

    public void setPriceTrip(double priceTrip) {
        this.priceTrip = priceTrip;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nFecha: " + tripDate + "\nPrecio: " + priceTrip + "\nOrigen: " + origin + "\nDestino: " + destination;
    }
}
