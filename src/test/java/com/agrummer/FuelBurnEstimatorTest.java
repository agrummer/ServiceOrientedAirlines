package com.agrummer;

import com.agrummer.exception.InvalidAirportCodeException;
import com.agrummer.exception.NoAircraftForFlightLoadException;
import org.junit.Assert;
import org.junit.Test;

/**
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class FuelBurnEstimatorTest {

    private static final String HOME_AIRPORT_CODE = "KSEA";
    private static final double DELTA_TOLERANCE = 0.01;

    @Test
    public void testSinglePassengerToSingleDestination() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        estimator.addPassenger("KPDX", 1);

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testMultiplePassengersToSingleDestination() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        for (int i = 0; i < 3; i++) {
            estimator.addPassenger("KPDX", 1);
        }

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testMultiplePassengersToMultipleDestinations() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        estimator.addPassenger("KLAX", 1);
        estimator.addPassenger("KLAX", 0);
        estimator.addPassenger("KPDX", 2);
        estimator.addPassenger("KPDX", 1);
        estimator.addPassenger("KSFO", 3);
        estimator.addPassenger("KSLC", 1);
        estimator.addPassenger("KSLC", 0);
        estimator.addPassenger("KLAX", 2);
        estimator.addPassenger("KSFO", 3);
        estimator.addPassenger("KSFO", 1);
        estimator.addPassenger("CYVR", 0);
        estimator.addPassenger("KOAK", 0);
        estimator.addPassenger("KSLC", 0);
        estimator.addPassenger("KOAK", 1);
        estimator.addPassenger("KLAX", 0);

        double expected = 3524.22;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testSeatsAtFullCapacity() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        for (int i = 0; i < 36; i++) {
            estimator.addPassenger("KPDX", 2);
        }

        double expected = 801.96;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testOverSeatingCapacityThrowsException() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        for (int i = 0; i < 36; i++) {
            try {
                estimator.addPassenger("CYVR", 0);
            } catch (NoAircraftForFlightLoadException e) {
                Assert.fail("NoAircraftForFlightLoadException was thrown before seating capacity was exceeded");
            }
        }

        try {
            estimator.addPassenger("CYVR", 0);
            Assert.fail("Should have thrown NoAircraftForFlightLoadException because no available airplane has enough " +
                    "seats for the additional passenger");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    @Test
    public void testCheckedBagsAtFullCapacity() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        for (int i = 0; i < 24; i++) {
            estimator.addPassenger("KPDX", 3);
        }

        double expected = 801.96;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testOverCheckedBagCapacityThrowsException() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        for (int i = 0; i < 24; i++) {
            try {
                estimator.addPassenger("KPDX", 3);
            } catch (NoAircraftForFlightLoadException e) {
                Assert.fail("NoAircraftForFlightLoadException was thrown before checked bag capacity was exceeded");
            }
        }

        try {
            estimator.addPassenger("KPDX", 1);
            Assert.fail("Should have thrown NoAircraftForFlightLoadException because no available airplane has enough " +
                    "checked baggage capacity for the additional bag");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    @Test
    public void testInvalidAirportCodeThrowsException() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        try {
            estimator.addPassenger("ABC123", 0);
            Assert.fail("Should have thrown InvalidAirportCodeException");
        } catch (InvalidAirportCodeException e) {
            // Success
        }
    }

    @Test
    public void testNoAircraftCanReachDestinationThrowsException() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        // Destination: Moscow, Russia
        estimator.addPassenger("UUDD", 0);

        try {
            estimator.calcFuelRequired();
            Assert.fail("Should have thrown NoAircraftForFlightLoadException");
        } catch (NoAircraftForFlightLoadException e) {
            // Success
        }
    }

    @Test
    public void testEstimateStillAccurateAfterHandlingExceptions() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE);

        for (int i = 0; i < 10; i++) {
            estimator.addPassenger("KPDX", 2);
        }
        try {
            estimator.addPassenger("ABC123", 1);
            Assert.fail("Should have thrown InvalidAirportCodeException");
        } catch (InvalidAirportCodeException e) {
            // Do nothing
        }
        for (int i = 0; i < 26; i++) {
            estimator.addPassenger("KPDX", 2);
        }
        try {
            estimator.addPassenger("KPDX", 1);
            Assert.fail("Should have thrown NoAircraftForFlightLoadException");
        } catch (NoAircraftForFlightLoadException e) {
            // Do nothing
        }

        double expected = 801.96;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

}
