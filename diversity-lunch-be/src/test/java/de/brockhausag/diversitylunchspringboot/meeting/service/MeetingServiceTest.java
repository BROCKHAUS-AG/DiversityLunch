package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapperImpl;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.*;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
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

    @InjectMocks
    private MeetingService service;

    @Mock
    private MeetingMapperImpl meetingMapperImpl;

    private MeetingProposalEntity existingMeetingProposalEntity;
    private MeetingProposalEntity newMeetingProposalEntity;


    @BeforeEach
    void tearUp() {
        ProfileEntity profileEntity = ProfileEntity.builder()
                .id(1)
                .name("Test")
                .email("some@brockhaus-ag.de")
                .birthYear(1990)
                .currentProject(Project.ExampleCompany1)
                .gender(Gender.MALE)
                .originCountry(Country.DEUTSCHLAND)
                .motherTongue(Language.DEUTSCH)
                .religion(Religion.NO_FAITH)
                .hobby(Hobby.ARCHERY)
                .education(Education.STUDY)
                .workExperience(WorkExperience.MID_EXPERIENCE)
                .diet(Diet.MEAT)
                .build();

        newMeetingProposalEntity = MeetingProposalEntity.builder()
                .proposerProfile(profileEntity)
                .proposedDateTime(LocalDateTime.now())
                .matched(true)
                .build();

        existingMeetingProposalEntity = MeetingProposalEntity.builder()
                .id(1L)
                .proposerProfile(profileEntity)
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
                .proposer(ProfileEntity.builder().name("TestSuccess").build())
                .build();
        MeetingEntity meetingEntity2 = MeetingEntity.builder()
                .fromDateTime(LocalDateTime.now().plusDays(6))
                .partner(ProfileEntity.builder().name("TestSuccess").build())
                .build();
        LocalDateTime date = LocalDateTime.now().plusDays(6);
        existingMeetingProposalEntity.setProposedDateTime(date);

        when(meetingMapperImpl.mapEntityToDto(any())).thenCallRealMethod();
        when(meetingMapperImpl.mapMatchedMeetingToDto(any(), any())).thenCallRealMethod();
        when(meetingRepository.findByPartnerAndFromDateTimeIsAfter(any(), any())).thenReturn(List.of(meetingEntity1));
        when(meetingRepository.findByProposerAndFromDateTimeIsAfter(any(), any())).thenReturn(List.of(meetingEntity2));
        when(meetingProposalRepository.findByProposerProfileAndMatchedFalseAndProposedDateTimeIsAfter(any(), any())).thenReturn(List.of(existingMeetingProposalEntity));
        when(profileService.getProfile(1)).thenReturn(Optional.of(existingMeetingProposalEntity.getProposerProfile()));

        List<MeetingDto> result = service.getAllMeetingsInFutureForUser(1);

        assertEquals(result.get(0).getPartnerName(), meetingEntity1.getProposer().getName());
        assertEquals(result.get(1).getPartnerName(), meetingEntity2.getPartner().getName());
        assertNull(result.get(2).getPartnerName());

    }
}
