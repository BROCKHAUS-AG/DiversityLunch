package de.brockhausag.diversitylunchspringboot.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DiversityLunchTimeAndDateFormatter {

    private DiversityLunchTimeAndDateFormatter() {
    }

    public static String convertFromUTCToTimeZoneAndFormatWithPattern(LocalDateTime time, String pattern, String timeZone) {
        ZonedDateTime zonedDateTime = time.atZone(ZoneId.of("UTC"));

        ZonedDateTime zonedDateTimeFormatted = zonedDateTime.withZoneSameInstant(ZoneId.of(timeZone));

        return zonedDateTimeFormatted.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String convertFromUTCToTimeZoneAndFormat(LocalDateTime time, String timeZone) {
        ZonedDateTime zonedDateTime = time.atZone(ZoneId.of("UTC"));

        ZonedDateTime zonedDateTimeFormatted = zonedDateTime.withZoneSameInstant(ZoneId.of(timeZone));

        return zonedDateTimeFormatted.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public static String formatDate(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
