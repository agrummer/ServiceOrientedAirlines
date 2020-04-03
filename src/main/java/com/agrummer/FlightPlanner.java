package com.agrummer;

import com.agrummer.entity.Airport;
import com.agrummer.entity.FlightPlan;
import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import com.agrummer.service.AircraftService;
import com.agrummer.service.AirportService;
import com.agrummer.util.DistanceUtil;

import java.io.IOException;
import java.util.Optional;

/**
 * Flight Planner Coding Exercise
 * Written by Alex Grummer on March 31st, 2020
 *
 * Complete the three public methods below to get all the unit tests passing.
 * Feel free to add whatever you need to write high quality, maintainable code!
 */
public class FlightPlanner {

    private AircraftService aircraftService;
    private AirportService airportService;

    private void initServices() throws IOException {
        this.aircraftService = AircraftService.init();
        this.airportService = AirportService.init();
    }

    /**
     * 1. Constructor - Initialize this class
     *
     * @param homeAirportCode airport code of home base airport where trips will originate and terminate
     */
    public FlightPlanner(String homeAirportCode) throws Exception {
        // TODO
    }

    /**
     * 2. Add passengers to the trip
     *
     * @param destinationAirportCode                airport code of the passenger's destination airport
     * @throws InvalidAirportCodeException          if the airport code provided does not resolve to a valid airport
     * @throws NoAircraftForFlightLoadException     if there are no available aircraft with enough seating capacity to add the passenger
     */
    public void addPassenger(String destinationAirportCode, int checkedBags) throws InvalidAirportCodeException, NoAircraftForFlightLoadException {
        // TODO
    }

    /**
     * 3. Build and return the flight plan with airports in the order in which they should be visited
     *
     * @return FlightPlan instance
     */
    public FlightPlan buildFlightPlan() {
        // TODO
        return null;
    }

}
