package com.weather.constant;

public class Constant {
    public static final String Q = "q";

    public static final String ZIPCODE_REGEX = "^[0-9]{5}(?:-[0-9]{4})?$";

    public static final String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final long DAY_TO_MILLS = 24 * 60 * 60 * 1000;

    public static final String APP_ID = "DemoAppId01082013GAL";

    public static final String APP_CODE = "AJKnXv84fjrb0KIHawS0Tg";

    public enum PRODUCT{
        NEXT_SEVEN_DAYS("forecast_7days"),
        NEXT_SEVEN_DAYS_HOURLY("forecast_hourly");

        private String value;

        PRODUCT(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
