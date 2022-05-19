package de.brockhausag.diversitylunchspringboot.meeting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingProposalDtoTest {

    @Test
    void shouldTestNoArgsConstructor() {

        MeetingDto meetingProposalDto = new MeetingDto();
        MeetingDto expectedMeetingProposalDto = new MeetingDto();

        assertEquals(expectedMeetingProposalDto, meetingProposalDto);

    }

    @Test
    void shouldTestPartnerNameIsNull() {
        MeetingDto meetingProposalDto = new MeetingDto();
        assertEquals(meetingProposalDto.getPartnerName(), (null));
    }

}
