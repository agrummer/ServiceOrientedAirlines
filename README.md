# Service Oriented Airlines

You're working for a hip new airline start-up that's rethinking the way we travel. Instead of big crowded planes and 
rigid schedules, Service Oriented Airlines has a fleet of small planes, with a plane taking off every 30 minutes.
Passengers are added to a queue upon arrival, and an aircraft is selected based on the capacity required to transport
the queued passengers and their luggage. The aircraft's flight plan is determined just before departure, and can 
include multiple destinations based on the passengers on board.

Ticket pricing varies based on the number of passengers, destinations, and fuel costs. You've been asked to build a
program that can estimate the fuel required for a aircraft's full round-trip flight plan.

## The Flight Plan

1. Starting at an aircraft's home city, a flight plan is determined by the unique list of destinations for all the 
passengers on board.
2. The aircraft flies to the closest destination in the flight plan first.
3. From there, the aircraft flies to the next closest destination in the flight plan.
4. The aircraft continues this pattern until it has visited all of the remaining destinations in the flight plan.
5. Once all destinations in the flight plan have been reached, the aircraft flies back to its original home airport.

#### Example Flight Plan

If the home airport is in **Seattle** and the passengers are going to **San Francisco**, **Portland** 
and **Los Angeles**, the flight plan would be:
```
Seattle -> Portland -> San Francisco -> Los Angeles -> Seattle
```

Note: The aircraft can be refueled at each airport on the flight plan, but it will need to fly home from the last 
airport on the flight plan without stopping.

## Program Specification

* Upon initialization, the gate agent will enter the home airport code (where the flight plan will start and end).
* Passengers, checked bags, and destinations will be added to the system as passengers show up at the gate.
    * Throw an exception if the passenger's destination airport is not within the airline's service network.
    * Throw an exception if the number of passengers in the system has already reached the maximum seating capacity 
    of the largest available aircraft.
* When it's time to board, the program should select an appropriate aircraft from the fleet, with the capacity to
seat all the passengers, stow all their checked bags, and reach all their destinations. 
* Based on the aircraft selected, calculate the amount of fuel required (in kg) for the full flight plan.
    * Throw an exception if there are no available aircraft with enough range to make the trip.

Note: The impact of passenger weight on the fuel burn rate is negligible, so don't worry about it for this exercise 
(and don't worry about head or tail winds, time spent taxiing, or any other complexities outside the scope of these 
instructions).

## Getting Started

1. Install [Maven](http://maven.apache.org/install.html "Install Maven") if you don't already have it
2. From the root directory of this project (same directory as this README.md file), compile the code and run the 
test suite:
```shell script
$ mvn test
```
Open `src/main/java/com/agrummer/FuelBurnEstimator.java` and start writing code to get the tests passing!