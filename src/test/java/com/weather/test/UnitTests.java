package com.weather.test;

import com.weather.config.ExternalAPI;
import com.weather.config.WeatherConfig;
import com.weather.dao.Weather;
import com.weather.service.GetTomorrowWeatherService;
import com.weather.service.impl.GetTomorrowWeatherImpl;
import com.weather.utils.WeatherUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static com.weather.constant.Constant.UTC_PATTERN;

@SpringBootTest
@Import(WeatherConfig.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UnitTests {
    private static final String EXTERNAL_TEST_URL = "https://weather.cit.api.here.com/weather/1.0/report.json";

    @Autowired
    private GetTomorrowWeatherService getTomorrowWeatherService;

    @Test
    public void testZipCodeValidationFunction() {
        Assert.assertFalse(WeatherUtils.isZipCodeValid("483246341"));
        Assert.assertFalse(WeatherUtils.isZipCodeValid("-8453"));
        Assert.assertFalse(WeatherUtils.isZipCodeValid("dsgfddg"));
    }

    @Test
    public void testCallToExternalResource() {
        ExternalAPI externalAPI = new ExternalAPI();
        externalAPI.setUrl(EXTERNAL_TEST_URL);
        // invalid zip code, external API will return 400.
        List<Weather> weathers = getTomorrowWeatherService.get("88888", externalAPI);
        Assert.assertNull(weathers);
    }
}
