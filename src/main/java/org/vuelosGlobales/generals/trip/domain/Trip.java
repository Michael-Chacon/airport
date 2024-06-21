package org.vuelosGlobales.generals.trip.domain;

public class Trip {
    private int id;
    private String tripDate;
    private double priceTrip;

    public Trip() {
    }

    public Trip(int id, String tripDate, double priceTrip) {
        this.id = id;
        this.tripDate = tripDate;
        this.priceTrip = priceTrip;
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
}
