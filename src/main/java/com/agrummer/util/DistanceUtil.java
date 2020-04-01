package com.agrummer.util;

import com.agrummer.entity.Coordinates;

/**
 * A helper class for all your distance-related needs.
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class DistanceUtil {

    private static final double EARTH_RADIUS_KM = 6371;

    /**
     * Returns the distance in kilometers (as the crow flies) between two points on planet earth
     *
     * @param   a   latitude/longitude coordinates of first point
     * @param   b   latitude/longitude coordinates of second point
     * @return      distance in kilometers
     */
    public static double calcKilometersBetween(Coordinates a, Coordinates b) {
        double aLat = Math.toRadians(a.getLatitude());
        double aLon = Math.toRadians(a.getLongitude());
        double bLat = Math.toRadians(b.getLatitude());
        double bLon = Math.toRadians(b.getLongitude());

        double distLat = bLat - aLat;
        double distLon = bLon - aLon;

        return EARTH_RADIUS_KM * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(distLat / 2), 2)
                + Math.cos(aLat) * Math.cos(bLat)
                * Math.pow(Math.sin(distLon / 2),2)));
    }

}
