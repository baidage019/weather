package com.weather.service;

import com.weather.config.ExternalAPI;
import com.weather.dao.Weather;

import java.util.List;

/**
 * This interface is a generic interface which helps to retrieve the weather list based on the product.
 */
public interface WeatherService {
    /**
     * get a list of weather based on the passed in parameters.
     * @param zipCode - zip code
     * @param externalAPI - contains the external URL which is configurable.
     * @param product - external API provides a list of product
     * @param oneObservation - external function
     * @param appId - app ID
     * @param appCode - App Code
     * @return - a list of weather which meet the condition.
     */
    List<Weather> get(String zipCode, ExternalAPI externalAPI,
                      String product, boolean oneObservation, String appId, String appCode);
}
