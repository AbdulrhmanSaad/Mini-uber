package com.example.uber;

import java.io.Serializable;

public class Trip implements Serializable {
    private String pickUpPoint;
    private String destination;
   private String time;
   private static int tripId=0;

    public static int getTripId() {
        return tripId;
    }

    private Driver driver=new Driver();
private Rider rider=new Rider();

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public float getCarFare() {
        return carFare;
    }

    public void setCarFare(float carFare) {
        this.carFare = carFare;
    }

    private float carFare;
    public Trip(String pickUpPoint, String destination, String time, String driverPhoneNo, float carFare, String riderPhoneNo) {
        this.pickUpPoint = pickUpPoint;
        this.destination = destination;
        this.time = time;
        this.driver.setPhoneNumber(driverPhoneNo);
        this.carFare = carFare;
        this.rider.setPhoneNumber(riderPhoneNo);
        tripId++;
    }

    public Trip(String pickUpPoint, String destination, String time, String riderPhoneNo) {
        this.pickUpPoint = pickUpPoint;
        this.destination = destination;
        this.time = time;
        this.rider.setPhoneNumber(riderPhoneNo);
        tripId++;
    }

    public Trip() {
    }
    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
