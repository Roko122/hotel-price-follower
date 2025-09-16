package com.roko.hotelpricefollower.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateTimeUtil {
    private DateTimeUtil() {}

    public static LocalDate toLocalDate(Instant instant) {
        return LocalDate.ofInstant(instant, zoneIdHelsinki());
    }

    public static ZoneId zoneIdHelsinki() {
        return ZoneId.of("Europe/Helsinki");
    }
}
