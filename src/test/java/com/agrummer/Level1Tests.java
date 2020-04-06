package com.agrummer;

import com.agrummer.entity.FlightPlan;
import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Level 1: Flight Planner Test
 * Finish the methods in the FlightPlanner class to get these tests passing.
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class Level1Tests {

    private static final String HOME_AIRPORT_CODE = "KSEA";
    private static final String AIRPORTS_MSG = "List of airports is incorrect";
    private static final String PASSENGERS_MSG = "Number of passengers is incorrect";
    private static final String CHECKED_BAGS_MSG = "Number of checked bags is incorrect";

    @Test
    public void testSinglePassengerToSingleDestination() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        planner.addPassenger("KPDX", 1);

        FlightPlan flightPlan = planner.buildFlightPlan();
        List<String> expectedAirportCodes = Arrays.asList(HOME_AIRPORT_CODE, "KPDX", HOME_AIRPORT_CODE);
        Assert.assertEquals(AIRPORTS_MSG, expectedAirportCodes, flightPlan.getAirportCodes());
        Assert.assertEquals(PASSENGERS_MSG, 1, flightPlan.getPassengers());
        Assert.assertEquals(CHECKED_BAGS_MSG, 1, flightPlan.getCheckedBags());
    }

    @Test
    public void testMultiplePassengersToSingleDestination() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 3; i++) {
            planner.addPassenger("KPDX", 1);
        }

        FlightPlan flightPlan = planner.buildFlightPlan();
        List<String> expectedAirportCodes = Arrays.asList(HOME_AIRPORT_CODE, "KPDX", HOME_AIRPORT_CODE);
        Assert.assertEquals(AIRPORTS_MSG, expectedAirportCodes, flightPlan.getAirportCodes());
        Assert.assertEquals(PASSENGERS_MSG, 3, flightPlan.getPassengers());
        Assert.assertEquals(CHECKED_BAGS_MSG, 3, flightPlan.getCheckedBags());
    }

    @Test
    public void testMultiplePassengersToMultipleDestinations() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        planner.addPassenger("KLAX", 1);
        planner.addPassenger("KLAX", 0);
        planner.addPassenger("KPDX", 2);
        planner.addPassenger("KPDX", 1);
        planner.addPassenger("KSFO", 3);
        planner.addPassenger("KSLC", 1);
        planner.addPassenger("KSLC", 0);
        planner.addPassenger("KLAX", 2);
        planner.addPassenger("KSFO", 3);
        planner.addPassenger("KSFO", 1);
        planner.addPassenger("CYVR", 0);
        planner.addPassenger("KOAK", 0);
        planner.addPassenger("KSLC", 0);
        planner.addPassenger("KOAK", 1);
        planner.addPassenger("KLAX", 0);

        FlightPlan flightPlan = planner.buildFlightPlan();
        List<String> expectedAirportCodes = Arrays.asList(
                HOME_AIRPORT_CODE,
                "KPDX",
                "CYVR",
                "KOAK",
                "KSFO",
                "KLAX",
                "KSLC",
                HOME_AIRPORT_CODE);
        Assert.assertEquals(AIRPORTS_MSG, expectedAirportCodes, flightPlan.getAirportCodes());
        Assert.assertEquals(PASSENGERS_MSG, 15, flightPlan.getPassengers());
        Assert.assertEquals(CHECKED_BAGS_MSG, 15, flightPlan.getCheckedBags());
    }

    @Test
    public void testSeatsAtFullCapacity() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 36; i++) {
            planner.addPassenger("KPDX", 1);
        }

        FlightPlan flightPlan = planner.buildFlightPlan();
        List<String> expectedAirportCodes = Arrays.asList(HOME_AIRPORT_CODE, "KPDX", HOME_AIRPORT_CODE);
        Assert.assertEquals(AIRPORTS_MSG, expectedAirportCodes, flightPlan.getAirportCodes());
        Assert.assertEquals(PASSENGERS_MSG, 36, flightPlan.getPassengers());
        Assert.assertEquals(CHECKED_BAGS_MSG, 36, flightPlan.getCheckedBags());
    }

    @Test
    public void testCheckedBagsAtFullCapacity() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 24; i++) {
            planner.addPassenger("KPDX", 3);
        }

        FlightPlan flightPlan = planner.buildFlightPlan();
        List<String> expectedAirportCodes = Arrays.asList(HOME_AIRPORT_CODE, "KPDX", HOME_AIRPORT_CODE);
        Assert.assertEquals(AIRPORTS_MSG, expectedAirportCodes, flightPlan.getAirportCodes());
        Assert.assertEquals(PASSENGERS_MSG, 24, flightPlan.getPassengers());
        Assert.assertEquals(CHECKED_BAGS_MSG, 72, flightPlan.getCheckedBags());
    }

    @Test
    public void testOverSeatingCapacityThrowsException() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 36; i++) {
            try {
                planner.addPassenger("CYVR", 0);
            } catch (NoAircraftForFlightLoadException e) {
                Assert.fail("NoAircraftForFlightLoadException was thrown before seating capacity was exceeded");
            }
        }

        try {
            planner.addPassenger("CYVR", 0);
            Assert.fail("Should have thrown NoAircraftForFlightLoadException because no available airplane has enough " +
                    "seats for the additional passenger");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    @Test
    public void testOverCheckedBagCapacityThrowsException() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 24; i++) {
            try {
                planner.addPassenger("KPDX", 3);
            } catch (NoAircraftForFlightLoadException e) {
                Assert.fail("NoAircraftForFlightLoadException was thrown before checked bag capacity was exceeded");
            }
        }

        try {
            planner.addPassenger("KPDX", 1);
            Assert.fail("Should have thrown NoAircraftForFlightLoadException because no available airplane has enough " +
                    "checked baggage capacity for the additional bag");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    @Test
    public void testInvalidAirportCodeThrowsException() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        try {
            planner.addPassenger("ABC123", 0);
            Assert.fail("Should have thrown InvalidAirportCodeException");
        } catch (InvalidAirportCodeException e) {
            // Success
        }
    }

}
