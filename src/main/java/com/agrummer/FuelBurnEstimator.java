package com.agrummer;

import com.agrummer.entity.Aircraft;
import com.agrummer.entity.Airport;
import com.agrummer.entity.FlightPlan;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import com.agrummer.service.AircraftService;
import com.agrummer.service.AirportService;
import com.agrummer.util.DistanceUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private FlightPlan flightPlan;

    private void initServices() throws IOException {
        this.aircraftService = AircraftService.init();
        this.airportService = AirportService.init();
    }

    /**
     * 1. Constructor - Initialize this class
     *
     */
    public FuelBurnEstimator() throws Exception {
        initServices();
    }

    /**
     * 2. Set flight plan
     *
     * @param flightPlan information about the flight (destinations, passengers, checked bags)
     */
    public void setFlightPlan(FlightPlan flightPlan) {
        this.flightPlan = flightPlan;
    }

    /**
     * 3. Get your aircraft and calculate the estimated fuel required to deliver all passengers to their destinations
     *
     * @throws NoAircraftForFlightLoadException if there are no available aircraft for the current flight load requirements
     * @return kilograms of fuel required
     */
    public double calcFuelRequired() throws NoAircraftForFlightLoadException {
        
        List<String> airportCodes = flightPlan.getAirportCodes();
        List<Airport> airports = airportCodes.stream().map((airportCode) -> airportService.getAirport(airportCode).get()).collect(
            Collectors.toList());
        
        double distance = 0.0d;
        
        for(int i = 0; i < airports.size() - 1; i++){
            Airport startAirport = airports.get(i);
            Airport endAirport = airports.get(i + 1);
            
            distance += DistanceUtil.calcKilometersBetween(startAirport.getCoordinates(), endAirport.getCoordinates());
        }
        Aircraft aircraft = aircraftService.getAircraftForLoad(flightPlan.getPassengers(), flightPlan.getCheckedBags(), distance).orElseThrow(
            NoAircraftForFlightLoadException::new);
        
        return aircraft.getFuelBurnRateKgKm() * distance;
    }
    

}
