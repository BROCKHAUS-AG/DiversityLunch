package de.brockhausag.diversitylunchspringboot.meeting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateMeetingProposalDtoTest {

    @Test
    void shouldCreateMeetingProposalDto_NoArgsConstructor() {
        CreateMeetingProposalDto createMeetingProposalDto = new CreateMeetingProposalDto();
        CreateMeetingProposalDto expectedCreateMeetingProposalDto = new CreateMeetingProposalDto();

        assertEquals(expectedCreateMeetingProposalDto, createMeetingProposalDto);
    }

}
