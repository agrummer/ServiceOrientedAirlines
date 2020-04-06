package com.agrummer;

import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Level 2: Fuel Burn Estimator Test
 * All of the tests in FlightPlannerTest must be passing before attempting to pass these tests.
 * Finish the methods in the FuelBurnEstimator class to get these tests passing.
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class Level2Tests {

    private static final String HOME_AIRPORT_CODE = "KSEA";
    private static final String AIRCRAFT_MSG = "Wrong aircraft was selected";
    private static final String ESTIMATE_MSG = "Fuel burn estimate is incorrect";
    private static final double DELTA_TOLERANCE = 0.01;

    private static FuelBurnEstimator setupPassengersToSingleDestination(int passengers, String airportCode) throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < passengers; i++) {
            planner.addPassenger(airportCode, 1);
        }

        return new FuelBurnEstimator(planner.buildFlightPlan());
    }

    @Test
    public void testSinglePassengerToSingleDestinationAircraft() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(1, "KPDX");

        String expected = "Cessna Citation M2";
        String actual = estimator.selectAircraft().getName();
        Assert.assertEquals(AIRCRAFT_MSG, expected, actual);
    }

    @Test
    public void testSinglePassengerToSingleDestinationFuelBurn() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(1, "KPDX");
        estimator.selectAircraft();

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testCalcFuelRequiredCalledBeforeAircraftSelected() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(1, "KPDX");

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testMultiplePassengersToSingleDestinationAircraft() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(3, "KPDX");

        String expected = "Cessna Citation M2";
        String actual = estimator.selectAircraft().getName();
        Assert.assertEquals(AIRCRAFT_MSG, expected, actual);
    }

    @Test
    public void testMultiplePassengersToSingleDestinationFuelBurn() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(3, "KPDX");
        estimator.selectAircraft();

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testLotsOfPassengersToSingleDestinationAircraft() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(36, "KPDX");

        String expected = "Bombardier CRJ900";
        String actual = estimator.selectAircraft().getName();
        Assert.assertEquals(AIRCRAFT_MSG, expected, actual);
    }

    @Test
    public void testLotsOfPassengersToSingleDestinationFuelBurn() throws Exception {
        FuelBurnEstimator estimator = setupPassengersToSingleDestination(36, "KPDX");
        estimator.selectAircraft();

        double expected = 801.96;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    private static FuelBurnEstimator setupMultiplePassengersToMultipleDestinations() throws Exception {
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

        return new FuelBurnEstimator(planner.buildFlightPlan());
    }

    @Test
    public void testMultiplePassengersToMultipleDestinationsAircraft() throws Exception {
        FuelBurnEstimator estimator = setupMultiplePassengersToMultipleDestinations();

        String expected = "Beechcraft 1900D";
        String actual = estimator.selectAircraft().getName();
        Assert.assertEquals(AIRCRAFT_MSG, expected, actual);
    }

    @Test
    public void testMultiplePassengersToMultipleDestinationsFuelBurn() throws Exception {
        FuelBurnEstimator estimator = setupMultiplePassengersToMultipleDestinations();
        estimator.selectAircraft();

        double expected = 3524.22;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testNoAircraftWithLongEnoughRangeThrowsException() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        // Destination: Moscow, Russia
        planner.addPassenger("UUDD", 0);

        FuelBurnEstimator estimator = new FuelBurnEstimator(planner.buildFlightPlan());

        try {
            estimator.selectAircraft();
            Assert.fail("Should have thrown NoAircraftForFlightLoadException");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    private static FuelBurnEstimator setupEstimateStillAccurateAfterHandlingExceptions() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 10; i++) {
            planner.addPassenger("KPDX", 2);
        }
        try {
            planner.addPassenger("ABC123", 1);
            Assert.fail("Should have thrown InvalidAirportCodeException");
        } catch (InvalidAirportCodeException e) {
            // Do nothing
        }
        for (int i = 0; i < 26; i++) {
            planner.addPassenger("KPDX", 2);
        }
        try {
            planner.addPassenger("KPDX", 1);
            Assert.fail("Should have thrown NoAircraftForFlightLoadException");
        } catch (NoAircraftForFlightLoadException e) {
            // Do nothing
        }
        return new FuelBurnEstimator(planner.buildFlightPlan());
    }

    @Test
    public void testAircraftStillCorrectAfterHandlingExceptions() throws Exception {
        FuelBurnEstimator estimator = setupEstimateStillAccurateAfterHandlingExceptions();

        String expected = "Bombardier CRJ900";
        String actual = estimator.selectAircraft().getName();
        Assert.assertEquals(AIRCRAFT_MSG, expected, actual);
    }

    @Test
    public void testEstimateStillAccurateAfterHandlingExceptions() throws Exception {
        FuelBurnEstimator estimator = setupEstimateStillAccurateAfterHandlingExceptions();
        estimator.selectAircraft();

        double expected = 801.96;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

}
