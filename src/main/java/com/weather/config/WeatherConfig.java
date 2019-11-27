package com.weather.config;

import com.weather.service.GetTomorrowWeatherService;
import com.weather.service.GetWeather;
import com.weather.service.impl.GetTomorrowWeatherImpl;
import com.weather.service.impl.GetWeatherImpl;
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
    public GetTomorrowWeatherService getTomorrowWeatherService() {
        return new GetTomorrowWeatherImpl();
    }

    @Bean
    public GetWeather getWeather() {
        return new GetWeatherImpl();
    }
}
