package com.weather;

import com.weather.config.ExternalAPI;
import com.weather.dao.Weather;
import com.weather.service.GetTomorrowWeatherService;
import com.weather.utils.WeatherUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.*;

import static com.weather.constant.Constant.Q;

@SpringBootApplication
@Profile("!test")
public class WeatherApp implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(WeatherApp.class);

    @Autowired
    private ExternalAPI externalAPI;

    @Autowired
    private GetTomorrowWeatherService getTomorrowWeatherService;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            logger.info("Pls enter the zip code:");
            Scanner in = new Scanner(System.in);

            // when the input string is "exit", quit the loop.
            String str = in.nextLine();
            if (Q.equals(str)) {
                logger.info("Bye-Bye");
                System.exit(0);
            }

            // validate the zip code format
            if (!WeatherUtils.isZipCodeValid(str)) {
                logger.error("Zip code: {} is invalid!", str);
                continue;
            }

            logger.info("Retrieve the tomorrow weather condition for zip code: {}", str);

            logger.info("External url: {}", externalAPI.getUrl());

            List<Weather> nextDayWeather = getTomorrowWeatherService.get(str, externalAPI);
            if (nextDayWeather != null) {
                logger.info("===================== Next Day Weather Report =====================");
                nextDayWeather.forEach(weather -> {
                    logger.info("Time: {}, Day Segment: {}, Temperature: {}", weather.getLocalDate(),
                            weather.getDaySegment(), weather.getTemperature());
                });
            } else {
                continue;
            }
        }
    }
}

