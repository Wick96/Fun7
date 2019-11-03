package com.luka.fun7;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class UserSupportFeature {
    private final int OPEN_HOUR = 9;
    private final int CLOSE_HOUR = 15;

    private boolean isEnabled;

    public UserSupportFeature() {
        isEnabled = isSupportAvailable();
    }

    private boolean isSupportAvailable() {
        DateTime dateTime = new DateTime().withZone(DateTimeZone.forID("Europe/Ljubljana"));

        int day = dateTime.getDayOfWeek();
        int hour = dateTime.getHourOfDay();

        return (day <= 5 && hour >= OPEN_HOUR && hour <= CLOSE_HOUR);
    }

    @Override
    public String toString() {
        return isEnabled ? "enable" : "disable";
    }
}
