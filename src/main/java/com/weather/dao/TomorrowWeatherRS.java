package com.weather.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(description = "Class holds the information about weather detail information for tomorrow")
public class TomorrowWeatherRS {
    @ApiModelProperty(notes = "0: success<br>400: bad request", required = true, example = "0", position = 1)
    private int statusCode;

    @ApiModelProperty(notes = "A list of hourly weather report for tomorrow", required = false, position = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<HourlyReport> hourlyReports;

    @ApiModelProperty(notes = "This is a field which indicates when is the coolest time for tomorrow. If error status code is returned, then this field will not show up.",
            example = "Thu Nov 28 02:00:00 MST 2019", required = false, position = 3)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String coolestHour;

    @ApiModelProperty(notes = "This is a message can provide additional information if it is necessary to explain the status code", required = false, position = 4)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<HourlyReport> getHourlyReports() {
        return hourlyReports;
    }

    public void setHourlyReports(List<HourlyReport> hourlyReports) {
        this.hourlyReports = hourlyReports;
    }

    public String getCoolestHour() {
        return coolestHour;
    }

    public void setCoolestHour(String coolestHour) {
        this.coolestHour = coolestHour;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
