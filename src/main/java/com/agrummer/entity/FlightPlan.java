package com.agrummer.entity;

import java.util.List;

/**
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class FlightPlan {

    // Airports in the order in which they should be visited
    private List<String> airportCodes;
    private int passengers;
    private int checkedBags;

    public FlightPlan(List<String> airportCodes, int passengers, int checkedBags) {
        this.airportCodes = airportCodes;
        this.passengers = passengers;
        this.checkedBags = checkedBags;
    }

    public List<String> getAirportCodes() {
        return airportCodes;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getCheckedBags() {
        return checkedBags;
    }
}
