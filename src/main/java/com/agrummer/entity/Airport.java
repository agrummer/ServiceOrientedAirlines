package com.agrummer.entity;

/**
 * There is no need to modify this class for the purpose of the coding exercise!
 */
public class Airport {

    private String code;
    private String name;
    private String continent;
    private String country;
    private String region;
    private String gpsCode;
    private String iataCode;
    private Coordinates coordinates;

    public Airport(String code, String name, String continent, String country, String region, String gpsCode, String iataCode, Coordinates coordinates) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.region = region;
        this.gpsCode = gpsCode;
        this.iataCode = iataCode;
        this.coordinates = coordinates;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", gpsCode='" + gpsCode + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
