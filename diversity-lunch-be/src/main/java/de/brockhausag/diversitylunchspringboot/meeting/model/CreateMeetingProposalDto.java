package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.meeting.customValidators.ProposedDateTimeValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMeetingProposalDto {
    @ProposedDateTimeValidator
    private LocalDateTime fromDateTime;
}
