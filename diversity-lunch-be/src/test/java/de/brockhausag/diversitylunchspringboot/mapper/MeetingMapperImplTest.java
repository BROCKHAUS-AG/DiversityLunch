package de.brockhausag.diversitylunchspringboot.mapper;

import de.brockhausag.diversitylunchspringboot.data.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapperImpl;
import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.ProfileNotFoundException;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeetingMapperImplTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private MeetingMapperImpl mapper;

    private final MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();

    @Test
    void testMapCreateDtoToEntity_ExistingProfileIdProvided_validEntity() {
        MeetingProposalEntity meetingProposalEntity = meetingTestdataFactory.createEntity();
        ProfileEntity profileEntity = meetingProposalEntity.getProposerProfile();
        CreateMeetingProposalDto createMeetingProposalDto = meetingTestdataFactory.createDto();
        final LocalDateTime createdAt = LocalDateTime.of(2022, 2, 14, 16, 1, 0, 0);


        when(profileService.getProfile(profileEntity.getId())).thenReturn(Optional.of(profileEntity));
        try(var mock = Mockito.mockStatic(LocalDateTime.class)) {
            mock.when(LocalDateTime::now).thenReturn(createdAt);
            Optional<MeetingProposalEntity> actualEntity = mapper.mapCreateDtoToEntity(createMeetingProposalDto, profileEntity.getId());
            assertTrue(actualEntity.isPresent());
            assertEquals(meetingProposalEntity, actualEntity.get());
        }
    }

    @Test
    void testMapCreateDtoToEntity_NonExistingProfileIdProvided_OptionalEmpty() {
        CreateMeetingProposalDto createDto = meetingTestdataFactory.createDto();
        when(profileService.getProfile(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class, () -> mapper.mapCreateDtoToEntity(createDto, -1));
    }

    @Test
    void testMapEntityToDto() {
        MeetingProposalEntity entity = meetingTestdataFactory.unmatchedEntity();
        MeetingDto dto = meetingTestdataFactory.unmatchedDto();

        MeetingDto actualDto = mapper.mapEntityToDto(entity);

        assertEquals(dto, actualDto);
    }
}
