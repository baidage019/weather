package com.weather.service;

import com.weather.config.ExternalAPI;
import com.weather.dao.HourlyReport;
import com.weather.dao.Weather;

import java.util.List;

/**
 * This interface helps to retrieve the weather for next day.
 */
public interface TomorrowWeatherDetailService {
    /**
     *
     * @param zipCode - zip code
     * @param externalAPI - external API which contains url
     * @return - A list of detail weather report for tomorrow weather.
     */
    List<Weather> get(String zipCode, ExternalAPI externalAPI);

    /**
     * This method is a easy way to retrieve the hourly report for the next day.
     * Compared with get method in this interface, get method can be used in more situations, because it has more detail information about the weather.
     *
     * @param zipCode - zip code
     * @param externalAPI - external API which contains url
     * @return - A list of short hourly weather report for tomorrow weather.
     */
    List<HourlyReport> getHourlyReports(String zipCode, ExternalAPI externalAPI);
}
