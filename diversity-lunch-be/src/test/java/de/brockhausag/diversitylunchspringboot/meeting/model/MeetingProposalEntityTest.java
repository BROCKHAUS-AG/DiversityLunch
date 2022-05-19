package de.brockhausag.diversitylunchspringboot.meeting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingProposalEntityTest {

    @Test
    void shouldTestNoArgsConstructor() {

        MeetingProposalEntity meetingProposalEntity = new MeetingProposalEntity();
        MeetingProposalEntity expectedMeetingProposalEntity = new MeetingProposalEntity();

        assertEquals(expectedMeetingProposalEntity, meetingProposalEntity);
    }

}
