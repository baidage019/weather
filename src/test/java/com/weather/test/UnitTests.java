package com.weather.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.config.ExternalAPI;
import com.weather.config.WeatherConfig;
import com.weather.constant.Constant;
import com.weather.controller.WeatherController;
import com.weather.dao.TomorrowWeatherRS;
import com.weather.dao.Weather;
import com.weather.service.TomorrowWeatherDetailService;
import com.weather.service.WeatherService;
import com.weather.utils.WeatherUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static com.weather.constant.Constant.APP_CODE;
import static com.weather.constant.Constant.APP_ID;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(WeatherConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UnitTests {
    @Autowired
    private ExternalAPI externalAPI;

    @Autowired
    private WeatherController weatherController;

    @Autowired
    private TomorrowWeatherDetailService tomorrowWeatherDetailService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void TomorrowWeatherAPITestSuccess() throws Exception {
        String uri = "/get_tomorrow_weather_detail?zipCode=85054";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        TomorrowWeatherRS tomorrowWeatherRS = objectMapper.readValue(content, TomorrowWeatherRS.class);
        assertEquals(Constant.StatusCode.SUCCESS.getValue(), tomorrowWeatherRS.getStatusCode());
        assertTrue(tomorrowWeatherRS.getHourlyReports().size() > 0);
        assertNotNull(tomorrowWeatherRS.getCoolestHour());
    }

    @Test
    public void TomorrowWeatherAPITestBadZipCode1() throws Exception {
        String uri = "/get_tomorrow_weather_detail?zipCode=88888";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        TomorrowWeatherRS tomorrowWeatherRS = objectMapper.readValue(content, TomorrowWeatherRS.class);
        assertEquals(Constant.StatusCode.BAD_RQ.getValue(), tomorrowWeatherRS.getStatusCode());
    }

    @Test(expected = NestedServletException.class)
    public void TomorrowWeatherAPITestBadZipCode2() throws Exception {
        String uri = "/get_tomorrow_weather_detail?zipCode=6524";
        mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isInternalServerError());
    }

    /**
     * This unit test does the end to end testing for the controller.
     */
    @Test
    public void endToEndTestForController() {
        TomorrowWeatherRS tomorrowWeather = weatherController.getTomorrowWeather("85054");
        assertEquals(Constant.StatusCode.SUCCESS.getValue(), tomorrowWeather.getStatusCode());
        Assert.assertNotNull(tomorrowWeather.getHourlyReports());
        Assert.assertNotNull(tomorrowWeather.getCoolestHour());

        tomorrowWeather = weatherController.getTomorrowWeather("88888");
        assertEquals(Constant.StatusCode.BAD_RQ.getValue(), tomorrowWeather.getStatusCode());
    }

    @Test
    public void testTomorrowDetailService(){
        Assert.assertNotNull(tomorrowWeatherDetailService.get("85054", externalAPI));
        Assert.assertNull(tomorrowWeatherDetailService.get("88888", externalAPI));

        Assert.assertNotNull(tomorrowWeatherDetailService.getHourlyReports("85054", externalAPI));
        Assert.assertNull(tomorrowWeatherDetailService.getHourlyReports("88888", externalAPI));
    }

    @Test
    public void testWeatherService(){
        List<Weather> weathers = weatherService.get("85054", externalAPI, Constant.PRODUCT.NEXT_SEVEN_DAYS_HOURLY.getValue(), true, APP_ID, APP_CODE);
        Assert.assertNotNull(weathers);

        weathers = weatherService.get("88888", externalAPI, Constant.PRODUCT.NEXT_SEVEN_DAYS_HOURLY.getValue(), true, APP_ID, APP_CODE);
        Assert.assertNull(weathers);
    }

    @Test
    public void testURLBuilder() {
        final String url = WeatherUtils.buildURL("85054", externalAPI,
                Constant.PRODUCT.NEXT_SEVEN_DAYS_HOURLY.getValue(), true, APP_ID, APP_CODE);
        assertEquals("https://weather.cit.api.here.com/weather/1.0/report.json?product=forecast_hourly&zipcode=85054&oneobservation=true&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg", url);
    }
}
