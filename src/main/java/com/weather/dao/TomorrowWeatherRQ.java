package com.weather.dao;

public class TomorrowWeatherRQ {
    private String zipCode;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "TomorrowWeatherRQ{" +
                "zipCode='" + zipCode + '\'' +
                '}';
    }
}
