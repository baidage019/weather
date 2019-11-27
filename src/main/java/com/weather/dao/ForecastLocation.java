package com.weather.dao;

import java.util.List;

public class ForecastLocation {
    private List<Weather> forecast;

    public List<Weather> getForecast() {
        return forecast;
    }

    public void setForecast(List<Weather> forecast) {
        this.forecast = forecast;
    }
}
