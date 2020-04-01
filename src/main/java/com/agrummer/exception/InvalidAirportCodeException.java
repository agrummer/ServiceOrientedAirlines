package com.agrummer.exception;

/**
 * Throw this exception when the user has provided an airport code that does not resolve to an airport.
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class InvalidAirportCodeException extends Exception {

    public InvalidAirportCodeException(String airportCode) {
        super("Invalid airport code: " + airportCode);
    }
}
