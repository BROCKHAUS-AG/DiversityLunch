package de.brockhausag.diversitylunchspringboot.meeting.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {

    @Schema(description = "die eindeutige Kennung eines Meetings", example = "42L")
    private long id;

    @Schema(description = "Tag und Uhrzeit des Meetings", example = "28-02-2022 11:30")
    private LocalDateTime fromDateTime;

    @Schema(description = "Name des Partners des Matches", example = "Gematchete Helene")
    private String partnerName;

    @Schema(description = "Name des anderen/proposenden Partners des Matches", example = "Anfrager Rudi")
    private String proposerName;
}
