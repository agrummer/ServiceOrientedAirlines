package com.agrummer.exception;

/**
 * Throw this exception when the user attempts to add a passenger to an
 * aircraft that is already at its maximum seating capacity.
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class InsufficientSeatingCapacityException extends Exception {

    public InsufficientSeatingCapacityException() {
        super("Aircraft seating is already at full capacity");
    }
}
