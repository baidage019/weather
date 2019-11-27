package com.weather.dao;

import java.util.Date;

public class Weather {
    private String daySegment;

    private String temperature;

    private String utcTime;

    private String dayOfWeek;

    private Date localDate;

    public String getDaySegment() {
        return daySegment;
    }

    public void setDaySegment(String daySegment) {
        this.daySegment = daySegment;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(String utcTime) {
        this.utcTime = utcTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }
}
