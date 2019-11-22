package com.weather.service.impl;

import com.weather.config.ExternalAPI;
import com.weather.dao.FutureWeather;
import com.weather.dao.Weather;
import com.weather.service.GetWeather;
import com.weather.utils.WeatherUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class GetWeatherImpl implements GetWeather {
    private static final Logger logger = LoggerFactory.getLogger(GetWeatherImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Weather> get(String zipCode, ExternalAPI externalAPI, String product, boolean oneObservation, String appId, String appCode) {
        final String url = WeatherUtils.buildURL(zipCode, externalAPI, product, oneObservation, appId, appCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        try {
            final FutureWeather body = restTemplate.exchange(url, HttpMethod.GET, entity, FutureWeather.class).getBody();
            return body.getForecasts().getForecastLocation().getForecast();
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
