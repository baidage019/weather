package com.weather.service;

import com.weather.config.ExternalAPI;
import com.weather.dao.Weather;

import java.util.List;

/**
 * This interface helps to retrieve the weather for next day.
 */
public interface GetTomorrowWeatherService {
    /**
     *
     * @param zipCode - zip code
     * @param externalAPI - external API which contains url
     * @return
     */
    List<Weather> get(String zipCode, ExternalAPI externalAPI);
}
