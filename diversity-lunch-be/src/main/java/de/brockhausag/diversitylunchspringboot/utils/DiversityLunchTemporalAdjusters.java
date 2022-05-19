package de.brockhausag.diversitylunchspringboot.utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.*;
import java.util.List;

public class DiversityLunchTemporalAdjusters {

    private DiversityLunchTemporalAdjusters() {

    }

    public static TemporalAdjuster roundMinutesDownToHalfAndFull() {
        return temporal -> {
            LocalDateTime time = LocalDateTime.from(temporal);
            int minutes = time.getMinute();
            int rounds  = Math.floorDiv(minutes, 30);
            return time.withMinute(rounds == 0 ? 0 : 30);
        };
    }

    public static TemporalAdjuster addTimezoneOffset() {
        return temporal -> {
            int offset = 0;
            if (temporal instanceof ZonedDateTime zonedDateTime) {
                 offset = zonedDateTime.getOffset().getTotalSeconds();
            }
            return temporal.plus(offset, ChronoUnit.SECONDS);
        };
    }
}
