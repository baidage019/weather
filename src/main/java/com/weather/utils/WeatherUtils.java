package com.weather.utils;

import com.weather.config.ExternalAPI;

public class WeatherUtils {
    private static final String URL_PATTERN = "%s?product=%s&zipcode=%s&oneobservation=%b&app_id=%s&app_code=%s";

    public static String buildURL(String zipCode, ExternalAPI externalAPI, String product, boolean oneObservation, String appId, String appCode) {
        return String.format(URL_PATTERN, externalAPI.getUrl(), product, zipCode, oneObservation, appId, appCode);
    }
}
