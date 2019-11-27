package com.weather.config;

import com.weather.service.TomorrowWeatherDetailService;
import com.weather.service.WeatherService;
import com.weather.service.impl.TomorrowWeatherDetailImpl;
import com.weather.service.impl.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.weather.constant.Constant.UTC_PATTERN;

@Configuration
public class WeatherConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DateFormat dateFormat() {
        return new SimpleDateFormat(UTC_PATTERN, Locale.US);
    }

    @Bean
    public TomorrowWeatherDetailService tomorrowWeatherDetailService() {
        return new TomorrowWeatherDetailImpl();
    }

    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }
}
