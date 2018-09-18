package com.example.zo0okadev.wunderdemo.Model;

public class Car {

    private String carName;
    private String carAddress;
    private double carLng;
    private double carLat;
    private String carExterior;
    private String carInterior;

    public Car(String carName, String carAddress, double carLng, double carLat, String carExterior, String carInterior) {
        this.carName = carName;
        this.carAddress = carAddress;
        this.carLng = carLng;
        this.carLat = carLat;
        this.carExterior = carExterior;
        this.carInterior = carInterior;
    }

    public Car(String carName, double carLng, double carLat) {
        this.carName = carName;
        this.carLng = carLng;
        this.carLat = carLat;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }

    public double getCarLng() {
        return carLng;
    }

    public void setCarLng(double carLng) {
        this.carLng = carLng;
    }

    public double getCarLat() {
        return carLat;
    }

    public void setCarLat(double carLat) {
        this.carLat = carLat;
    }

    public String getCarExterior() {
        return carExterior;
    }

    public void setCarExterior(String carExterior) {
        this.carExterior = carExterior;
    }

    public String getCarInterior() {
        return carInterior;
    }

    public void setCarInterior(String carInterior) {
        this.carInterior = carInterior;
    }
}
