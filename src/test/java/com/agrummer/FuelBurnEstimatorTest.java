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
public class FuelBurnEstimatorTest {

    private static final String HOME_AIRPORT_CODE = "KSEA";
    private static final String ESTIMATE_MSG = "Fuel burn estimate is incorrect";
    private static final double DELTA_TOLERANCE = 0.01;

    @Test
    public void testSinglePassengerToSingleDestination() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        planner.addPassenger("KPDX", 1);

        FuelBurnEstimator estimator = new FuelBurnEstimator();
        estimator.setFlightPlan(planner.buildFlightPlan());

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testMultiplePassengersToSingleDestination() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        for (int i = 0; i < 3; i++) {
            planner.addPassenger("KPDX", 1);
        }

        FuelBurnEstimator estimator = new FuelBurnEstimator();
        estimator.setFlightPlan(planner.buildFlightPlan());

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
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

        FuelBurnEstimator estimator = new FuelBurnEstimator();
        estimator.setFlightPlan(planner.buildFlightPlan());

        double expected = 3524.22;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testNoAircraftWithLongEnoughRangeThrowsException() throws Exception {
        FlightPlanner planner = new FlightPlanner(HOME_AIRPORT_CODE);

        // Destination: Moscow, Russia
        planner.addPassenger("UUDD", 0);

        FuelBurnEstimator estimator = new FuelBurnEstimator();
        estimator.setFlightPlan(planner.buildFlightPlan());

        try {
            estimator.calcFuelRequired();
            Assert.fail("Should have thrown NoAircraftForFlightLoadException");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    @Test
    public void testEstimateStillAccurateAfterHandlingExceptions() throws Exception {
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

        FuelBurnEstimator estimator = new FuelBurnEstimator();
        estimator.setFlightPlan(planner.buildFlightPlan());

        double expected = 801.96;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals(ESTIMATE_MSG, expected, actual, DELTA_TOLERANCE);
    }

}
