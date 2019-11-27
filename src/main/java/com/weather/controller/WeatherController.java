package com.weather.controller;

import com.weather.config.ExternalAPI;
import com.weather.constant.Constant;
import com.weather.dao.HourlyReport;
import com.weather.dao.TomorrowWeatherRS;
import com.weather.service.TomorrowWeatherDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@RestController
@Validated
@Api(value = "Tomorrow Weather Detail API", description = "Retrieve tomorrow weather condition with zip code.", tags = "Tomorrow Weather API")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private ExternalAPI externalAPI;

    @Autowired
    private TomorrowWeatherDetailService tomorrowWeatherDetailService;

    @ApiOperation(value = "Get Tomorrow Weather Detail - this API allows user to retrieve tomorrow weather condition for the provided zip code area.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get tomorrow weather condition for the provided zip code area.")
    })
    @RequestMapping(value = "/get_tomorrow_weather_detail", method = RequestMethod.GET)
    @ResponseBody
    public TomorrowWeatherRS getTomorrowWeather(
            @RequestParam @Pattern(regexp = Constant.ZIPCODE_REGEX) String zipCode
    ) {
        logger.info("Retrieve tomorrow weather for zip code: {}", zipCode);

        logger.info("External Resource Support URL: {}", externalAPI.getUrl());

        List<HourlyReport> hourlyReports = tomorrowWeatherDetailService.getHourlyReports(zipCode, externalAPI);

        TomorrowWeatherRS tomorrowWeatherRS = new TomorrowWeatherRS();
        if (hourlyReports != null) {
            tomorrowWeatherRS.setStatusCode(Constant.StatusCode.SUCCESS.getValue());
            tomorrowWeatherRS.setHourlyReports(hourlyReports);
            tomorrowWeatherRS.setCoolestHour(hourlyReports.stream().min(Comparator.comparing(HourlyReport::getTemperature)).get().getDate());
        } else {
            tomorrowWeatherRS.setStatusCode(Constant.StatusCode.BAD_RQ.getValue());
            tomorrowWeatherRS.setMessage("The rq contains bad data, server can't process it!");
        }

        return tomorrowWeatherRS;
    }

    @ApiIgnore
    @RequestMapping("/doc")
    void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
