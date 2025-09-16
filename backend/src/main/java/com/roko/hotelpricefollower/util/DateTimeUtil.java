package com.roko.hotelpricefollower.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private DateTimeUtil() {}

    public static LocalDate toLocalDate(Instant instant) {
        return LocalDate.ofInstant(instant, zoneIdHelsinki());
    }

    public static ZoneId zoneIdHelsinki() {
        return ZoneId.of("Europe/Helsinki");
    }

    public static Instant instantNow() {
        return Instant.now();
    }

    public static Instant instant30DaysAgo() {
        return Instant.now().minus(30, ChronoUnit.DAYS);
    }
}
