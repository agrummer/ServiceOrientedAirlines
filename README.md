# Service Oriented Airlines

You're working for a hip new airline start-up that's rethinking the way we travel. Instead of big crowded planes and 
rigid schedules, Service Oriented Airlines has a fleet of small planes, with a plane taking off every 30 minutes.
Passengers board planes based on when they show up at the airport, so a plane's flight plan is determined just before 
departure, and can include multiple destinations based on the passengers on board.

Ticket pricing varies based on the number of passengers, destinations, and fuel costs. You've been asked to build a
component that estimates the fuel required for a aircraft's full round-trip flight plan.

## The Flight Plan

1. Starting at an aircraft's home city, a flight plan is determined by the unique list of destinations for all the 
passengers on board.
2. The aircraft flies to the closest destination in the flight plan first.
3. From there, the aircraft flies to the next closest destination in the flight plan.
4. The aircraft continues this pattern until it has visited all of the remaining destinations in the flight plan.
5. Once all destinations in the flight plan have been reached, the aircraft flies back to its original home airport.

#### Example

If the home airport is in **Seattle** and the passengers are going to **San Francisco**, **Portland** 
and **Los Angeles**, the flight plan would be:
```
Seattle -> Portland -> San Francisco -> Los Angeles -> Seattle
```

Note: The aircraft can be refueled at each airport on the flight plan, but it will need to fly home from the last 
airport on the flight plan without stopping.

## Program Specification

* When a plane is ready for boarding, the gate agent will input the following information:
    * Home airport code (where the flight plan will start and end)
    * Aircraft's fuel burn rate in kg/km
    * Aircraft's maximum fuel capacity in kg
    * Aircraft seating capacity
* Passengers and their destinations will be added to the system as they show up to board the plane.
    * If there are not enough available seats remaining on the aircraft, the program should alert the gate agent.
    * If the passenger's destination airport is not within the airline's service network, do not allow the passenger 
    to board.
    * If the distance between the home airport and the passenger's destination airport is more than half the max range 
    of the aircraft, do not allow the passenger to board (this ensures no two destinations will be farther apart than 
    the plane can fly). 
*  When boarding is complete, calculate the amount of fuel required (in kg) for the full flight plan.

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