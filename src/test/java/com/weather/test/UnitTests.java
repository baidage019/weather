package com.weather.test;

import com.weather.config.ExternalAPI;
import com.weather.config.WeatherConfig;
import com.weather.dao.Weather;
import com.weather.service.TomorrowWeatherDetailService;
import com.weather.utils.WeatherUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@Import(WeatherConfig.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UnitTests {
//    private static final String EXTERNAL_TEST_URL = "https://weather.cit.api.here.com/weather/1.0/report.json";
//
//    @Autowired
//    private TomorrowWeatherDetailService getTomorrowWeatherService;
//
//    @Test
//    public void testCallToExternalResource() {
//        ExternalAPI externalAPI = new ExternalAPI();
//        externalAPI.setUrl(EXTERNAL_TEST_URL);
//        // invalid zip code, external API will return 400.
//        List<Weather> weathers = getTomorrowWeatherService.get("88888", externalAPI);
//        Assert.assertNull(weathers);
//    }
}
