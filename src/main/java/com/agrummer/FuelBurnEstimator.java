package com.agrummer;

import com.agrummer.entity.Airport;
import com.agrummer.exception.AirportTooFarFromHomeException;
import com.agrummer.exception.InsufficientSeatingCapacityException;
import com.agrummer.exception.InvalidAirportCodeException;
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

    private AirportService airportService;

    private void initAirportService() throws IOException {
        this.airportService = AirportService.init();
    }

    /**
     * 1. Constructor - Initialize this class
     *
     * @param homeAirportCode           airport code of home base airport where trips will originate and terminate
     * @param aircraftFuelBurnRateKgKm  rate the aircraft burns fuel, in kilograms per kilometer
     * @param aircraftFuelCapacityKg    the aircraft's maximum fuel capacity in kg
     * @param aircraftSeatingCapacity   maximum passenger seating capacity of the aircraft
     */
    public FuelBurnEstimator(String homeAirportCode, double aircraftFuelBurnRateKgKm, double aircraftFuelCapacityKg, int aircraftSeatingCapacity) throws Exception {
        // TODO
    }

    /**
     * 2. Add passengers to the trip
     *
     * @param   destinationAirportCode                   airport code of the passenger's destination airport
     * @throws  InsufficientSeatingCapacityException     if the aircraft is already at full passenger seating capacity
     * @throws  InvalidAirportCodeException              if the airport code provided does not resolve to a valid airport
     * @throws  AirportTooFarFromHomeException           if the distance between the destination airport and the home
     *                                                   airport is more than half the maximum range of the aircraft
     */
    public void addPassenger(String destinationAirportCode) throws InsufficientSeatingCapacityException, InvalidAirportCodeException, AirportTooFarFromHomeException {
        // TODO
    }

    /**
     * 3. Calculate the estimated fuel required to deliver all passengers to their destinations
     *
     * @return kilograms of fuel required
     */
    public double calcFuelRequired() {
        // TODO
        return 0.0;
    }

}
