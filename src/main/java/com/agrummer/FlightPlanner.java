package com.agrummer;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.agrummer.entity.FlightPlan;
import com.agrummer.entity.FlightPlanContext;
import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import com.agrummer.service.AircraftService;
import com.agrummer.service.AirportService;
import com.agrummer.util.DistanceUtil;

/**
 * Flight Planner Coding Exercise Written by Alex Grummer on March 31st, 2020
 * <p>
 * Complete the three public methods below to get all the unit tests passing. Feel free to add
 * whatever you need to write high quality, maintainable code!
 */
public class FlightPlanner {
    
    private AircraftService aircraftService;
    private AirportService airportService;
    private final FlightPlanContext flightPlanContext;
    private final double maxSeatCapacity;
    private final double maxBagCapacity;
    
    private void initServices() throws IOException {
        this.aircraftService = AircraftService.init();
        this.airportService = AirportService.init();
    }
    
    /**
     * 1. Constructor - Initialize this class
     *
     * @param homeAirportCode airport code of home base airport where trips will originate and
     *     terminate
     */
    public FlightPlanner(String homeAirportCode) throws Exception {
        initServices();
        this.flightPlanContext = new FlightPlanContext(homeAirportCode);
        this.maxSeatCapacity = aircraftService.getMaximumSeatingCapacity();
        this.maxBagCapacity = aircraftService.getMaximumCheckedBagCapacity();
    }
    
    /**
     * 2. Add passengers to the trip
     *
     * @param destinationAirportCode airport code of the passenger's destination airport
     * @throws InvalidAirportCodeException if the airport code provided does not resolve to a
     *     valid airport
     * @throws NoAircraftForFlightLoadException if there are no available aircraft with enough
     *     seating capacity to add the passenger
     */
    public void addPassenger(String destinationAirportCode, int checkedBags)
        throws InvalidAirportCodeException, NoAircraftForFlightLoadException {
        airportService.getAirport(destinationAirportCode)
            .orElseThrow(() -> new InvalidAirportCodeException(destinationAirportCode));
        
        int currentBags = this.flightPlanContext.getCheckedBags();
        int currentPassengers = this.flightPlanContext.getPassengers();
        
        if (currentBags + checkedBags > this.maxBagCapacity ||
            currentPassengers + 1 > this.maxSeatCapacity) {
            throw new NoAircraftForFlightLoadException();
        }
        
        this.flightPlanContext.addPassenger();
        this.flightPlanContext.addCheckedBags(checkedBags);
        Set<String> airportCodes = this.flightPlanContext.getAirportCodes();
        airportCodes.add(destinationAirportCode);
    }
    
    /**
     * 3. Build and return the flight plan with airports in the order in which they should be
     * visited
     *
     * @return FlightPlan instance
     */
    public FlightPlan buildFlightPlan() {
        //build a list of flights (yay)
        List<String> flightPlan = new LinkedList<>(
            Collections.singletonList(this.flightPlanContext.getHomeAirport()));
        
        Set<String> airportCodes = this.flightPlanContext.getAirportCodes();
        String currentAirport = this.flightPlanContext.getHomeAirport();
        while (airportCodes.size() > 0) {
            PriorityQueue<String> flightHeap = new PriorityQueue<>(
                buildComparatorForBaseAirport(currentAirport));
            flightHeap.addAll(airportCodes);
            String nearestFlight = flightHeap.poll();
            flightPlan.add(nearestFlight);
            airportCodes.remove(nearestFlight);
        }
        flightPlan.add(this.flightPlanContext.getHomeAirport());
        return new FlightPlan(flightPlan, this.flightPlanContext.getPassengers(),
            this.flightPlanContext.getCheckedBags());
    }
    
    private Comparator<String> buildComparatorForBaseAirport(String startingAirportCode) {
        return (airportCode, secondAirportCode) -> (int) Math.round(
            DistanceUtil.calcKilometersBetween(
                airportService.getAirport(airportCode).get().getCoordinates(),
                airportService.getAirport(startingAirportCode).get().getCoordinates()) -
                DistanceUtil.calcKilometersBetween(
                    airportService.getAirport(secondAirportCode).get().getCoordinates(),
                    airportService.getAirport(startingAirportCode).get().getCoordinates()));
    }
    
}
