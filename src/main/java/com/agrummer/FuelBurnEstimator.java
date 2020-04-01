package com.agrummer;

import com.agrummer.exception.InsufficientSeatingCapacityException;
import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.service.AirportService;

import java.io.IOException;

/**
 * Fuel Burn Estimator Coding Exercise
 * Written by Alex Grummer on March 31st, 2020
 *
 * Complete the three public methods below to get all the unit tests passing.
 */
public class FuelBurnEstimator {

    private AirportService airportService;

    private void initAirportService() throws IOException {
        this.airportService = AirportService.init();
    }

    /**
     * 1. Constructor - Initialize this class
     *
     * @param homeAirportCode           airport code of home base airport where trips will originate and terminate
     * @param aircraftFuelBurnRateKgKm  rate the aircraft burns fuel, in kilograms per kilometer
     * @param aircraftSeatingCapacity   maximum passenger seating capacity of the aircraft
     */
    public FuelBurnEstimator(String homeAirportCode, double aircraftFuelBurnRateKgKm, int aircraftSeatingCapacity) throws Exception {
        // TODO
    }

    /**
     * 2. Add passengers to the trip
     *
     * @param destinationAirportCode                    airport code of the passenger's destination airport
     * @throws InsufficientSeatingCapacityException     if the aircraft is already at full passenger seating capacity
     * @throws InvalidAirportCodeException              if the airport code provided does not resolve to a valid airport
     */
    void addPassenger(String destinationAirportCode) throws InsufficientSeatingCapacityException, InvalidAirportCodeException {
        // TODO
    }

    /**
     * 3. Calculate the estimated fuel required to deliver all passengers to their destinations
     *
     * @return kilograms of fuel required
     */
    double calcFuelRequired() {
        // TODO
        return 0.0;
    }

}
