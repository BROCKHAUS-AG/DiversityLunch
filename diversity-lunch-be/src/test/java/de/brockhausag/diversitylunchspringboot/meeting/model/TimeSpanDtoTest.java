package de.brockhausag.diversitylunchspringboot.meeting.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeSpanDtoTest {

    @Test
    void shouldTestNoArgsConstructor_TimeSpanDto() {
        TimeSpanDto timeSpanDto = new TimeSpanDto();
        TimeSpanDto expectedTimeSpanDto = new TimeSpanDto();
        assertEquals(expectedTimeSpanDto, timeSpanDto);
    }

    @Test
    void shouldTestAllArgsConstructor_TimeSpanDto() {

        LocalDateTime from = LocalDateTime.of(2022, 1, 1, 12, 0, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 1, 12, 30);

        TimeSpanDto timeSpanDto = TimeSpanDto.builder()
                .from(from)
                .to(to).build();

        assertEquals(timeSpanDto.getFrom(), LocalDateTime.of(2022, 1, 1, 12, 0));
        assertEquals(timeSpanDto.getTo(), LocalDateTime.of(2022, 1, 1, 12, 30));
    }
}
