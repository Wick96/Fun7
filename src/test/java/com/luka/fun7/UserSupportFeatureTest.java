package com.luka.fun7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

class UserSupportFeatureTest {
    private final UserSupportFeature userSupportFeature = new UserSupportFeature();

    @Test
    void TestToString() {
        DateTime dateTime = new DateTime().withZone(DateTimeZone.forID("Europe/Ljubljana"));

        int day = dateTime.getDayOfWeek();
        int hour = dateTime.getHourOfDay();

        if(day <= 5 && hour >= 9 && hour <= 15){
            assertEquals("enable", userSupportFeature.toString());
        }
        else{
            assertEquals("disable", userSupportFeature.toString());
        }

    }
}