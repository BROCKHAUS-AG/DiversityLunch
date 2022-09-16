package de.brockhausag.diversitylunchspringboot.meeting.controller;

import de.brockhausag.diversitylunchspringboot.data.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeetingControllerTest {

    @Mock
    private MeetingService meetingService;

    @Mock
    private MeetingMapper meetingMapper;

    @InjectMocks
    MeetingController meetingController;

    final MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();
    final ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();


    @Test
    void testGetMeeting_withValidId_expectedGetMeetingDtoWithStatusOK() {
        //ASSEMBLE
        MeetingDto meetingDto = meetingTestdataFactory.unmatchedDto();

        when(this.meetingService.getAllMeetingsInFutureForUser(15)).thenReturn(List.of(meetingDto));

        //ACT
        ResponseEntity<List<MeetingDto>> meetingProposalResponse = meetingController.getMeetingProposalsByUser(15);

        //ASSERT
        assertEquals(HttpStatus.OK, meetingProposalResponse.getStatusCode());
        assertNotNull(meetingProposalResponse.getBody());
        assertEquals(1, meetingProposalResponse.getBody().size());
        assertEquals(meetingDto, meetingProposalResponse.getBody().get(0));
    }

    @Test
    void testPostMeeting_serviceCreateEntity_returnsOkWithMeetingDto() {
        CreateMeetingProposalDto createMeetingProposalDto = this.meetingTestdataFactory.createDto();
        ProfileEntity profileEntity = this.profileTestdataFactory.entity();
        MeetingProposalEntity meetingProposalEntity = this.meetingTestdataFactory.entity();
        MeetingDto dto = this.meetingTestdataFactory.dto();
        MeetingProposalEntity createMeetingProposalEntity = this.meetingTestdataFactory.createEntity();

        when(this.meetingMapper.mapCreateDtoToEntity(createMeetingProposalDto, profileEntity.getId())).thenReturn(Optional.of(createMeetingProposalEntity));
        when(this.meetingMapper.mapEntityToDto(meetingProposalEntity)).thenReturn(dto);
        when(this.meetingService.createMeetingProposal(createMeetingProposalEntity)).thenReturn(meetingProposalEntity);

        ResponseEntity<MeetingDto> response = this.meetingController.createMeetingProposal(42, createMeetingProposalDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testPostMeeting_serviceProfileNotFound_returnsBadRequest(){
        CreateMeetingProposalDto createMeetingProposalDto = this.meetingTestdataFactory.createDto();
        long invalidId = 119L;

        when(this.meetingMapper.mapCreateDtoToEntity(createMeetingProposalDto, invalidId)).thenReturn(Optional.empty());

        ResponseEntity<MeetingDto> meetingDtoResponseEntity = this.meetingController.createMeetingProposal(invalidId, createMeetingProposalDto);

        assertEquals(HttpStatus.BAD_REQUEST, meetingDtoResponseEntity.getStatusCode());
    }

    @Test
    void testDeleteMeeting_serviceDeleteMeeting() {
        long id = 1L;
        ResponseEntity<String> response = this.meetingController.deleteMeetingProposal(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateMeetingProposal_AlreadyBookedMeetingProposal_shouldReturnConflictStatus() {
        CreateMeetingProposalDto createMeetingProposalDto = meetingTestdataFactory.createDto();
        ProfileEntity profileEntity = profileTestdataFactory.entity();
        when(meetingService.meetingExists(profileEntity.getId(), createMeetingProposalDto.getFromDateTime())).thenReturn(true);

        ResponseEntity<MeetingDto> response = meetingController.createMeetingProposal(profileEntity.getId(), createMeetingProposalDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
