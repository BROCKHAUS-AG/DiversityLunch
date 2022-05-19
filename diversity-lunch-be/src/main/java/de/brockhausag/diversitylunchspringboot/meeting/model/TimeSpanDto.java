package de.brockhausag.diversitylunchspringboot.meeting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSpanDto {

    private LocalDateTime from;

    private LocalDateTime to;

}
