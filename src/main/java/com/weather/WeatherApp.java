package com.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Profile("!test")
public class WeatherApp {
    public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }
}

