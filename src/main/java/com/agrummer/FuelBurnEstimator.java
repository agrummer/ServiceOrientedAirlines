package com.agrummer;

import com.agrummer.entity.Aircraft;
import com.agrummer.entity.Airport;
import com.agrummer.entity.FlightPlan;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import com.agrummer.service.AircraftService;
import com.agrummer.service.AirportService;
import com.agrummer.util.DistanceUtil;

import java.io.IOException;
import java.util.Optional;

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
     */
    public FuelBurnEstimator() throws Exception {
        // TODO
    }

    /**
     * 2. Set flight plan
     *
     * @param flightPlan information about the flight (destinations, passengers, checked bags)
     */
    public void setFlightPlan(FlightPlan flightPlan) {
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
