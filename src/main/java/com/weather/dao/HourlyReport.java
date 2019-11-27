package com.weather.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HourlyReport")
public class HourlyReport {
    @ApiModelProperty(notes = "Temperature of current time", required = false, example = "15.02", position = 1)
    private float temperature;
    @ApiModelProperty(notes = "Date", required = false, example = "Thu Nov 28 18:00:00 MST 2019", position = 2)
    private String date;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
