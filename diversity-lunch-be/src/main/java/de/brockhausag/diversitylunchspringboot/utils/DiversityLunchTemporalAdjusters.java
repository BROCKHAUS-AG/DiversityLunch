package de.brockhausag.diversitylunchspringboot.utils;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;

public class DiversityLunchTemporalAdjusters {

    private DiversityLunchTemporalAdjusters() {

    }

    public static TemporalAdjuster roundMinutesDownToHalfAndFull() {
        return temporal -> {
            LocalDateTime time = LocalDateTime.from(temporal);
            int minutes = time.getMinute();
            int rounds = Math.floorDiv(minutes, 30);
            return time.withMinute(rounds == 0 ? 0 : 30);
        };
    }
}
