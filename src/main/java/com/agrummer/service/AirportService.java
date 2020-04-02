package com.agrummer.service;

import com.agrummer.entity.Airport;
import com.agrummer.entity.Coordinates;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple service to provide information about major airports around the world.
 *
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class AirportService {

    private static final String AIRPORTS_CSV_PATH = "src/main/resources/airports.csv";

    private Map<String, Airport> airportsByCode;

    private AirportService(Map<String, Airport> airportsByCode) {
        this.airportsByCode = airportsByCode;
    }

    /**
     * Parses airport information from a local CSV file
     */
    public static AirportService init() throws IOException {
        Map<String, Airport> airportsByCode = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(new File(AIRPORTS_CSV_PATH)))) {
            try {
                String[] row = reader.readNext();
                if (row == null) {
                    throw new IllegalStateException("CSV file appears to be empty: " + AIRPORTS_CSV_PATH);
                }
                // Skip first row with column names
                row = reader.readNext();
                while (row != null && row.length > 0) {
                    if (row.length == 9) {
                        String code = row[0];
                        String name = row[1];
                        String continent = row[2];
                        String country = row[3];
                        String region = row[4];
                        String gpsCode = row[5];
                        String iataCode = row[6];
                        double latitude = Double.parseDouble(row[7]);
                        double longitude = Double.parseDouble(row[8]);
                        Airport airport = new Airport(code, name, continent, country, region, gpsCode, iataCode, new Coordinates(latitude, longitude));
                        airportsByCode.put(code, airport);
                    } else {
                        System.out.println("Skipping CSV row " + reader.getLinesRead() + " with unexpected length: " + row.length);
                    }
                    row = reader.readNext();
                }
            } catch (CsvValidationException e) {
                long lineNumber = reader.getLinesRead() + 1;
                System.out.println("Error reading CSV line number " + lineNumber + ": " + e.getMessage());
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file from path '" + AIRPORTS_CSV_PATH + "': " + e.getMessage());
            throw e;
        }

        return new AirportService(airportsByCode);
    }

    /**
     * Optionally returns an instance of Airport if the airport code argument provided maps to
     * an airport that this service knows about
     *
     * @param code airport code
     * @return instance of Optional, with or without an instance of Airport
     * @see Airport
     */
    public Optional<Airport> getAirport(String code) {
        if (airportsByCode.containsKey(code)) {
            return Optional.of(airportsByCode.get(code));
        } else {
            return Optional.empty();
        }
    }
}
