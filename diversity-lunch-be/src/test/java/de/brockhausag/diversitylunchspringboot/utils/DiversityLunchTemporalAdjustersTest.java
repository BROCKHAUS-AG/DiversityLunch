package de.brockhausag.diversitylunchspringboot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static de.brockhausag.diversitylunchspringboot.utils.DiversityLunchTemporalAdjusters.roundMinutesDownToHalfAndFull;
import static org.junit.jupiter.api.Assertions.*;

class DiversityLunchTemporalAdjustersTest {

    @Test
    void roundMinutesDownToHalfAndFullTest() {
        LocalDateTime time = LocalDateTime.of(2022,3,5, 12, 36, 58);
        LocalDateTime timeExpected = LocalDateTime.of(2022,3,5, 12, 30, 58);
        LocalDateTime result = time.with(roundMinutesDownToHalfAndFull());
        assertEquals(timeExpected, result);
    }
}