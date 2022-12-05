package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapperImpl;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeetingServiceTest {
    @Mock
    private MeetingProposalRepository meetingProposalRepository;

    @Mock
    private ProfileService profileService;

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    MsTeamsService msTeamsService;

    @InjectMocks
    private MeetingService service;

    @Mock
    private MeetingMapperImpl meetingMapperImpl;

    private MeetingProposalEntity existingMeetingProposalEntity;
    private MeetingProposalEntity newMeetingProposalEntity;

    private ProfileTestdataFactory profileFactory;


    @BeforeEach
    void tearUp() {
        this.profileFactory = new ProfileTestdataFactory();
        newMeetingProposalEntity = MeetingProposalEntity.builder()
                .proposerProfile(profileFactory.buildEntity(1))
                .proposedDateTime(LocalDateTime.now())
                .matched(true)
                .build();

        existingMeetingProposalEntity = MeetingProposalEntity.builder()
                .id(1L)
                .proposerProfile(profileFactory.buildEntity(1))
                .proposedDateTime(LocalDateTime.now())
                .matched(true)
                .build();

    }

    @Test
    void testMeetingNonexistent() {
        //Assemble
        when(meetingProposalRepository.findById(-1L)).thenReturn(Optional.empty());

        //Act
        Optional<MeetingProposalEntity> meeting = service.getMeetingProposal(-1L);

        //Assert
        assertTrue(meeting.isEmpty());
    }

    @Test
    void testGetMeetingProposal() {
        //Assemble
        when(meetingProposalRepository.findById(1L)).thenReturn(Optional.of(existingMeetingProposalEntity));

        //Act
        Optional<MeetingProposalEntity> result = service.getMeetingProposal(1L);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(existingMeetingProposalEntity, result.get());
    }

    @Test
    void testCreateMeetingProposal() {
        //Assemble
        when(meetingProposalRepository.save(newMeetingProposalEntity)).thenReturn(existingMeetingProposalEntity);

        //Act
        MeetingProposalEntity result = service.createMeetingProposal(newMeetingProposalEntity);

        //Assert
        assertEquals(existingMeetingProposalEntity, result);
    }

    @Test
    void testGetAllMeetingProposals() {
        //Assemble
        when(meetingProposalRepository.findByProposerProfile(existingMeetingProposalEntity.getProposerProfile())).thenReturn(List.of(existingMeetingProposalEntity));
        when(profileService.getProfile(existingMeetingProposalEntity.getProposerProfile().getId())).thenReturn(Optional.of(existingMeetingProposalEntity.getProposerProfile()));

        //Act
        List<MeetingProposalEntity> result = service.getAllMeetingProposals(existingMeetingProposalEntity.getProposerProfile().getId());

        //Assert
        assertEquals(1, result.size());
        assertEquals(existingMeetingProposalEntity, result.get(0));
    }

    @Test
    void testGetAllMeetingProposals_shouldReturnAnEmptyList(){

        long profileId = 0;

        //Assemble
        when(profileService.getProfile(profileId)).thenReturn(Optional.empty());
        //Act
        List<MeetingProposalEntity> meeting = service.getAllMeetingProposals(profileId);
        //Assert
        assertTrue(meeting.isEmpty());
    }

    @Test
    void testDeleteMeetingProposal_shouldDeleteMeetingProposalById() {

        service.deleteMeetingProposal(existingMeetingProposalEntity.getId());

        verify(meetingProposalRepository, times(1)).deleteById(existingMeetingProposalEntity.getId());
    }

    @Test
    void testMeetingExists_shouldReturnTrueIfGivenMeetingAlreadyExists() {
        //Assemble
        when(meetingProposalRepository.findByProposerProfile(existingMeetingProposalEntity.getProposerProfile())).thenReturn(List.of(existingMeetingProposalEntity));
        when(profileService.getProfile(existingMeetingProposalEntity.getProposerProfile().getId())).thenReturn(Optional.of(existingMeetingProposalEntity.getProposerProfile()));
        //Act
        boolean meetingExists = service.meetingExists(1L,existingMeetingProposalEntity.getProposedDateTime());

        //Assert
        assertTrue(meetingExists);
    }

    @Test
    void testMeetingExists_shouldReturnFalseIfGivenMeetingNotExists() {
        //Assemble
        when(meetingProposalRepository.findByProposerProfile(existingMeetingProposalEntity.getProposerProfile())).thenReturn(List.of());
        when(profileService.getProfile(existingMeetingProposalEntity.getProposerProfile().getId())).thenReturn(Optional.of(existingMeetingProposalEntity.getProposerProfile()));
        //Act
        boolean meetingExists = service.meetingExists(1L,existingMeetingProposalEntity.getProposedDateTime());

        //Assert
        assertFalse(meetingExists);
    }

    @Test
    void testGetAllMeetingsInFutureForUser(){

        MeetingEntity meetingEntity1 = MeetingEntity.builder()
                .fromDateTime(LocalDateTime.now().plusDays(6))
                .proposer(profileFactory.buildEntity(1))
                .build();
        MeetingEntity meetingEntity2 = MeetingEntity.builder()
                .fromDateTime(LocalDateTime.now().plusDays(6))
                .partner(profileFactory.buildEntity(1))
                .build();
        LocalDateTime date = LocalDateTime.now().plusDays(6);
        existingMeetingProposalEntity.setProposedDateTime(date);

        when(meetingMapperImpl.mapEntityToDto(any())).thenCallRealMethod();
        when(meetingMapperImpl.mapMatchedMeetingToDto(any(), any())).thenCallRealMethod();
        when(meetingRepository.findByPartnerAndFromDateTimeIsAfter(any(), any())).thenReturn(List.of(meetingEntity1));
        when(meetingRepository.findByProposerAndFromDateTimeIsAfter(any(), any())).thenReturn(List.of(meetingEntity2));
        when(meetingProposalRepository.findByProposerProfileAndMatchedFalseAndProposedDateTimeIsAfter(any(), any())).thenReturn(List.of(existingMeetingProposalEntity));
        when(profileService.getProfile(1L)).thenReturn(Optional.of(existingMeetingProposalEntity.getProposerProfile()));

        List<MeetingDto> result = service.getAllMeetingsInFutureForUser(1L);

        assertEquals(result.get(0).getPartnerName(), meetingEntity1.getProposer().getName());
        assertEquals(result.get(1).getPartnerName(), meetingEntity2.getPartner().getName());
        assertNull(result.get(2).getPartnerName());

    }

    @Test
    void testCancelMeeting_withMeetingInThePast_returnsFalse()
    {
        Long meetingId = 42L;
        Long userId = 9L;

        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(meetingId)
                .fromDateTime(LocalDateTime.now().minusDays(1))
                .build();
        Optional<MeetingEntity> meetingEntityOptional = Optional.of(meetingEntity);

        when(meetingRepository.findById(meetingId)).thenReturn(meetingEntityOptional);
        boolean canCancel = service.cancelMeeting(meetingId, userId);
        assertFalse(canCancel);
    }

    @Test
    void testCancelMeeting_withNotExistingMeetingId_returnsFalse()
    {
        Long meetingId = 42L;
        Long userId = 9L;

        when(meetingRepository.findById(meetingId)).thenReturn(Optional.empty());
        boolean canCancel = service.cancelMeeting(meetingId, userId);
        assertFalse(canCancel);
    }

    @Test
    void testCancelMeeting_withOneDayUntilMeeting_DeleteMeetingAndPartnerProposalUpdated(){
        //Arrange
        ProfileEntity profileEntityOne = profileFactory.buildEntity(1);
        ProfileEntity profileEntityTwo = profileFactory.buildEntity(2);
        LocalDateTime meetingTime = LocalDateTime.now().plusDays(1);
        String teamsMeetingId = "42";
        MeetingProposalEntity meetingProposalEntityOne = MeetingProposalEntity.builder()
                .id(1L)
                .proposerProfile(profileEntityOne)
                .proposedDateTime(meetingTime)
                .matched(true)
                .build();
        MeetingProposalEntity meetingProposalEntityTwo = MeetingProposalEntity.builder()
                .id(2L)
                .proposerProfile(profileEntityTwo)
                .proposedDateTime(meetingTime)
                .matched(true)
                .build();
        MeetingProposalEntity meetingProposalEntityTwoUpdated = MeetingProposalEntity.builder()
                .id(2L)
                .proposerProfile(profileEntityTwo)
                .proposedDateTime(meetingTime)
                .matched(false)
                .build();
        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(1L)
                .fromDateTime(meetingTime)
                .partner(profileEntityOne)
                .proposer(profileEntityTwo)
                .msTeamsMeetingId(teamsMeetingId)
                .build();


        when(meetingRepository.findById(meetingEntity.getId())).thenReturn(Optional.of(meetingEntity));
        when(meetingProposalRepository.findByProposedDateTimeAndProposerProfileAndMatchedTrue(meetingTime, profileEntityOne))
                .thenReturn(Optional.of(meetingProposalEntityOne));
        when(meetingProposalRepository.findByProposedDateTimeAndProposerProfileAndMatchedTrue(meetingTime, profileEntityTwo))
                .thenReturn(Optional.of(meetingProposalEntityTwo));

        //Act
        boolean actual = service.cancelMeeting(meetingEntity.getId(), profileEntityOne.getId());

        //Assert
        verify(meetingProposalRepository, times(1)).deleteById(meetingProposalEntityOne.getId());
        verify(msTeamsService, times(1)).cancelMsTeamsMeeting(meetingEntity);
        verify(meetingProposalRepository, times(1)).save(meetingProposalEntityTwoUpdated);
        assertTrue(actual);
    }
}
