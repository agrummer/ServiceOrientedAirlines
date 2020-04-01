package com.agrummer.exception;

public class AirportTooFarFromHomeException extends Exception {

    public AirportTooFarFromHomeException(String airportCode) {
        super("Airport '" + airportCode + "' is too far away from home");
    }
}
