package org.vuelosGlobales.generals.trip.domain;

public class Trip {
    private int id;
    private String tripDate;
    private double priceTrip;
    private String idOrigin;
    private String idDestination;

    public Trip() {
    }

    public Trip(int id, String tripDate, double priceTrip, String idOrigin, String idDestination) {
        this.id = id;
        this.tripDate = tripDate;
        this.priceTrip = priceTrip;
        this.idOrigin = idOrigin;
        this.idDestination = idDestination;
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

    public String getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(String idOrigin) {
        this.idOrigin = idOrigin;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }
}
