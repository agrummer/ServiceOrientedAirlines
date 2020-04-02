package com.agrummer.entity;

public class Aircraft {

    private String name;
    private int seatingCapacity;
    private int checkedBagCapacity;
    private double maxFuelCapacityKg;
    private double fuelBurnRateKgKm;

    public Aircraft(String name, int seatingCapacity, int checkedBagCapacity, double maxFuelCapacityKg, double fuelBurnRateKgKm) {
        this.name = name;
        this.seatingCapacity = seatingCapacity;
        this.checkedBagCapacity = checkedBagCapacity;
        this.maxFuelCapacityKg = maxFuelCapacityKg;
        this.fuelBurnRateKgKm = fuelBurnRateKgKm;
    }

    public String getName() {
        return name;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public int getCheckedBagCapacity() {
        return checkedBagCapacity;
    }

    public double getMaxFuelCapacityKg() {
        return maxFuelCapacityKg;
    }

    public double getFuelBurnRateKgKm() {
        return fuelBurnRateKgKm;
    }

}
