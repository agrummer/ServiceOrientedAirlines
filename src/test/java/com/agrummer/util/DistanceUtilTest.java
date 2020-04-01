package com.agrummer.util;

import com.agrummer.entity.Coordinates;
import org.junit.Assert;
import org.junit.Test;

/**
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class DistanceUtilTest {

    @Test
    public void testCalcKilometersBetween() {
        Object[][] testCases = new Object[][] {
                new Object[] { new Coordinates(0.0, 0.0), new Coordinates(0.0, 0.0), 0.0 },
                new Object[] { new Coordinates(52.5, 64.8), new Coordinates(52.5, 64.8), 0.0 },
                new Object[] { new Coordinates(58.99079895019531, 22.830699920654297), new Coordinates(-22.16830062866211, 16.301700592041016), 9044.425628056275 },
                new Object[] { new Coordinates(53.32055555555556, -1.7297222222222221), new Coordinates(53.31861111111111, -1.6997222222222223), 2.004367838271627 }
        };

        for (Object[] testCase : testCases) {
            Coordinates pointA = (Coordinates) testCase[0];
            Coordinates pointB = (Coordinates) testCase[1];
            double expected = (double) testCase[2];
            double actual = DistanceUtil.calcKilometersBetween(pointA, pointB);
            Assert.assertEquals(String.format("Unexpected distance for coordinates: %s, %s", pointA, pointB), expected, actual, 0.0);
        }
    }

}
