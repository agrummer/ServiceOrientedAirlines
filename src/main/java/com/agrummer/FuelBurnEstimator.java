package com.agrummer;

import com.agrummer.entity.Aircraft;
import com.agrummer.entity.Airport;
import com.agrummer.entity.FlightPlan;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import com.agrummer.service.AircraftService;
import com.agrummer.service.AirportService;
import com.agrummer.util.DistanceUtil;

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
     * @param flightPlan information about the flight (destinations, passengers, checked bags)
     */
    public FuelBurnEstimator(FlightPlan flightPlan) throws Exception {
        // TODO
    }

    /**
     * 2. Return the most appropriate aircraft available for the flight load requirements so that it can be reserved
     *
     * @return Aircraft
     * @throws NoAircraftForFlightLoadException if there are no available aircraft for the current flight load requirements
     */
    public Aircraft selectAircraft() throws NoAircraftForFlightLoadException {
        // TODO
        return null;
    }

    /**
     * 3. Calculate the estimated fuel required to deliver all passengers to their destinations
     *
     * @return kilograms of fuel required
     * @throws NoAircraftForFlightLoadException if an aircraft has not already been selected and there are no available
     *         aircraft for the current flight load requirements
     */
    public double calcFuelRequired() throws NoAircraftForFlightLoadException {
        // TODO
        return 0;
    }

}
