package com.agrummer;

import com.agrummer.exception.AirportTooFarFromHomeException;
import com.agrummer.exception.InsufficientSeatingCapacityException;
import com.agrummer.exception.InvalidAirportCodeException;
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
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,0.5, 400,6);

        estimator.addPassenger("KPDX");

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testMultiplePassengersToSingleDestination() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,0.5, 400, 6);

        for (int i = 0; i < 3; i++) {
            estimator.addPassenger("KPDX");
        }

        double expected = 115.55;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testMultiplePassengersToMultipleDestinations() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,0.8, 1000,18);

        estimator.addPassenger("KLAX");
        estimator.addPassenger("KLAX");
        estimator.addPassenger("KPDX");
        estimator.addPassenger("KPDX");
        estimator.addPassenger("KSFO");
        estimator.addPassenger("KSLC");
        estimator.addPassenger("KSLC");
        estimator.addPassenger("KLAX");
        estimator.addPassenger("KSFO");
        estimator.addPassenger("KSFO");
        estimator.addPassenger("CYVR");
        estimator.addPassenger("KOAK");
        estimator.addPassenger("KSLC");
        estimator.addPassenger("KOAK");
        estimator.addPassenger("KLAX");

        double expected = 2819.38;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testSeatsAtFullCapacity() throws Exception {
        final int aircraftSeatingCapacity = 18;
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,1.4, 1900, aircraftSeatingCapacity);

        for (int i = 0; i < aircraftSeatingCapacity; i++) {
            estimator.addPassenger("KPDX");
        }

        double expected = 323.556;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

    @Test
    public void testOverSeatingCapacityThrowsException() throws Exception {
        final int aircraftSeatingCapacity = 18;
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,1.4, 1900, aircraftSeatingCapacity);

        for (int i = 0; i < aircraftSeatingCapacity; i++) {
            try {
                estimator.addPassenger("CYVR");
            } catch (InsufficientSeatingCapacityException e) {
                Assert.fail("InsufficientSeatingCapacityException was thrown before seating capacity was exceeded");
            }
        }

        try {
            estimator.addPassenger("CYVR");
            Assert.fail("Should have thrown InsufficientSeatingCapacityException");
        } catch (InsufficientSeatingCapacityException e) {
            // Success
        }
    }

    @Test
    public void testInvalidAirportCodeThrowsException() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,2.0, 2200, 36);

        try {
            estimator.addPassenger("ABC123");
            Assert.fail("Should have thrown InvalidAirportCodeException");
        } catch (InvalidAirportCodeException e) {
            // Success
        }
    }

    @Test
    public void testAirportTooFarFromHomeThrowsException() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,0.65, 1200, 14);

        try {
            // Destination: Moscow, Russia
            estimator.addPassenger("UUDD");
            Assert.fail("Should have thrown AirportTooFarFromHomeException");
        } catch (AirportTooFarFromHomeException e) {
            // Success
        }
    }

    @Test
    public void testEstimateStillAccurateAfterHandlingExceptions() throws Exception {
        FuelBurnEstimator estimator = new FuelBurnEstimator(HOME_AIRPORT_CODE,0.65, 1200,14);

        try {
            // Destination: Moscow, Russia
            estimator.addPassenger("UUDD");
            Assert.fail("Should have thrown AirportOutOfRangeException");
        } catch (AirportTooFarFromHomeException e) {
            // Do nothing
        }
        for (int i = 0; i < 10; i++) {
            estimator.addPassenger("KSLC");
        }
        try {
            estimator.addPassenger("ABC123");
            Assert.fail("Should have thrown InvalidAirportCodeException");
        } catch (InvalidAirportCodeException e) {
            // Do nothing
        }
        for (int i = 0; i < 4; i++) {
            estimator.addPassenger("KBIL");
        }
        try {
            estimator.addPassenger("KPDX");
            Assert.fail("Should have thrown InsufficientSeatingCapacityException");
        } catch (InsufficientSeatingCapacityException e) {
            // Do nothing
        }

        double expected = 2051.45;
        double actual = estimator.calcFuelRequired();
        Assert.assertEquals("", expected, actual, DELTA_TOLERANCE);
    }

}
