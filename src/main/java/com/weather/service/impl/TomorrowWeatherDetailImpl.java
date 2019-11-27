package com.weather.service.impl;

import com.weather.config.ExternalAPI;
import com.weather.dao.HourlyReport;
import com.weather.dao.Weather;
import com.weather.service.TomorrowWeatherDetailService;
import com.weather.service.WeatherService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.weather.constant.Constant.*;

public class TomorrowWeatherDetailImpl implements TomorrowWeatherDetailService {
    private static final Logger logger = LoggerFactory.getLogger(TomorrowWeatherDetailImpl.class);

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private DateFormat dateFormat;

    @Override
    public List<Weather> get(String zipCode, ExternalAPI externalAPI) {
        List<Weather> nextSevernDays = weatherService.get(zipCode, externalAPI,
                PRODUCT.NEXT_SEVEN_DAYS_HOURLY.getValue(), true, APP_ID, APP_CODE);

        if (nextSevernDays == null) {
            return null;
        }

        long nextDateLong = new Date().getTime() + DAY_TO_MILLS;
        Date nextDate = new Date(nextDateLong);

        long nextDayStart = DateUtils.truncate(nextDate, Calendar.DATE).getTime();
        long nextDayEnd = nextDayStart + DAY_TO_MILLS;

        final List<Weather> collect = nextSevernDays.stream().filter(weather -> {
            try {
                final long time = dateFormat.parse(weather.getUtcTime()).getTime();
                if (time >= nextDayStart && time < nextDayEnd) {
                    weather.setLocalDate(new Date(time));
                    return true;
                }
            } catch (ParseException e) {
                logger.error(e.getMessage());
                return false;
            }
            return false;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<HourlyReport> getHourlyReports(String zipCode, ExternalAPI externalAPI) {
        List<Weather> nextDayWeather = get(zipCode, externalAPI);
        if (nextDayWeather == null) {
            return null;
        }
        List<HourlyReport> reports = new LinkedList<>();
        nextDayWeather.forEach(weather -> {
            HourlyReport hourlyReport = new HourlyReport();
            hourlyReport.setTemperature(Float.parseFloat(weather.getTemperature()));
            hourlyReport.setDate(weather.getLocalDate().toString());
            reports.add(hourlyReport);
        });
        return reports;
    }
}
