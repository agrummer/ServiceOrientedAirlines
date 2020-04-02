package com.agrummer.exception;

/**
 * Throw this exception when there are no aircraft available that meet the flight load requirements
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class NoAircraftForFlightLoadException extends Exception {

    public NoAircraftForFlightLoadException() {
        super("No aircraft available for the flight load requirements");
    }
}
