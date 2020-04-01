# Service Oriented Airlines

You're working for a hip new airline start-up that's rethinking the way we travel. Instead of big crowded planes and 
rigid schedules, Service Oriented Airlines has a fleet of small planes, with a plane taking off every 30 minutes.
Passengers board planes based on when they show up at the airport, so a plane's flight plan is determined just before 
departure, and can include multiple destinations based on the passengers on board.

Ticket pricing varies based on the number of passengers, destinations, and fuel costs. You've been asked to build a
component that estimates the fuel required for a aircraft's full round-trip flight plan.

### The Flight Plan
1. Starting at an aircraft's home city, a flight plan is determined by the unique list of destinations for all the 
passengers on board.
2. The aircraft flies to the closest destination in the flight plan first.
3. From there, the aircraft picks up any passengers who desire to travel to any of the remaining destinations in the 
flight plan and flies to the next closest destination in the flight plan.
4. The aircraft continues this pattern until it has visited all of the remaining destinations in the flight plan.
5. Once all destinations in the flight plan have been reached, the aircraft flies back to its original home airport.

Note: The aircraft can be refueled at each airport on the flight plan, but it will need to fly home from the last 
airport on the flight plan without stopping.

### Program Specification
* When a plane is ready for boarding, the gate agent will input the following information:
    * Home airport code (where the flight plan will start and end)
    * Aircraft's fuel burn rate in kg/km
    * Aircraft's maximum fuel capacity in kg
    * Aircraft seating capacity
* Passengers and their destinations will be added to the system as they show up to board the plane.
    * If there are not enough available seats remaining on the aircraft, the program should alert the gate agent.
    * If the passenger's destination airport is not within the airline's service network, do not allow the passenger to board.
    * If the passenger's destination airport is too far away for the aircraft to fly home, do not allow the passenger to board. 
*  When boarding is complete, calculate the amount of fuel required (in kg) for the full flight plan.
