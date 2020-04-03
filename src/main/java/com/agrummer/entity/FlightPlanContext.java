package com.agrummer.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jeffk on 4/3/20
 */
public class FlightPlanContext {
    
    private String homeAirport;
    private final Set<String> airportCodes = new HashSet<>();
    private int passengers = 0;
    private int checkedBags = 0;
    
    public FlightPlanContext(String homeAirport) {
        this.homeAirport = homeAirport;
    }
    
    public String getHomeAirport() {
        return homeAirport;
    }
    
    public int getPassengers() {
        return passengers;
    }
    
    public int getCheckedBags() {
        return checkedBags;
    }
    
    public void addPassenger() {
        ++this.passengers;
    }
    
    public void addCheckedBags(int bags) {
        this.checkedBags += bags;
    }
    
    public Set<String> getAirportCodes() {
        return airportCodes;
    }
}
