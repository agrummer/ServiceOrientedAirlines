package com.agrummer;

import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import com.agrummer.service.AircraftService;
import com.agrummer.service.AirportService;

import java.io.IOException;

/**
 * Fuel Burn Estimator Coding Exercise
 * Written by Alex Grummer on March 31st, 2020
 *
 * Complete the three public methods below to get all the unit tests passing.
 * Feel free to add whatever you need to write high quality, maintainable code!
 */
public class FuelBurnEstimator {

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
    public FuelBurnEstimator(String homeAirportCode) throws Exception {
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
     * 3. Get your aircraft and calculate the estimated fuel required to deliver all passengers to their destinations
     *
     * @throws NoAircraftForFlightLoadException if there are no available aircraft for the current flight load requirements
     * @return kilograms of fuel required
     */
    public double calcFuelRequired() throws NoAircraftForFlightLoadException {
        // TODO
        return 0.0;
    }

}
