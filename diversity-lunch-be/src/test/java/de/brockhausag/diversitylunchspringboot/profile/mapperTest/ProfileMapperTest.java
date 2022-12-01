package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.*;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.StreamUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

    @Mock
    private CountryService countryService;
    @Mock
    private DietService dietService;
    @Mock
    private EducationService educationService;
    @Mock
    private GenderService genderService;
    @Mock
    private HobbyService hobbyService;
    @Mock
    private LanguageService languageService;
    @Mock
    private ProjectService projectService;
    @Mock
    private ReligionService religionService;
    @Mock
    private SexualOrientationService sexualOrientationService;
    @Mock
    private WorkExperienceService workExperienceService;
    @Mock
    private SocialBackgroundService socialBackgroundService;
    @Mock
    private SocialBackgroundDiscriminationService socialBackgroundDiscriminationService;

    @Mock
    private CountryMapper countryMapper;
    @Mock
    private DietMapper dietMapper;
    @Mock
    private EducationMapper educationMapper;
    @Mock
    private GenderMapper genderMapper;
    @Mock
    private HobbyMapper hobbyMapper;
    @Mock
    private LanguageMapper languageMapper;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private ReligionMapper religionMapper;
    @Mock
    private SexualOrientationMapper sexualOrientationMapper;
    @Mock
    private WorkExperienceMapper workExperienceMapper;
    @Mock
    private SocialBackgroundMapper socialBackgroundMapper;
    @Mock
    private SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;

    @InjectMocks
    private ProfileMapper profileMapper;
    private ProfileTestdataFactory factory;

    @BeforeEach
    void setup() {
        this.factory = new ProfileTestdataFactory();
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange
        ProfileDto inputDto = factory.buildDto(1);
        ProfileEntity expectedEntity = factory.buildEntity(1);
        when(countryService.getEntityById(inputDto.getOriginCountry().getId())).thenReturn(Optional.of(expectedEntity.getOriginCountry()));
        when(workExperienceService.getEntityById(inputDto.getWorkExperience().getId())).thenReturn(Optional.of(expectedEntity.getWorkExperience()));
        when(religionService.getEntityById(inputDto.getReligion().getId())).thenReturn(Optional.of(expectedEntity.getReligion()));
        when(projectService.getEntityById(inputDto.getProject().getId())).thenReturn(Optional.of(expectedEntity.getProject()));
        when(languageService.getEntityById(inputDto.getMotherTongue().getId())).thenReturn(Optional.of(expectedEntity.getMotherTongue()));
        when(hobbyService.getEntityById(inputDto.getHobby().getId())).thenReturn(Optional.of(expectedEntity.getHobby()));
        when(genderService.getEntityById(inputDto.getGender().getId())).thenReturn(Optional.of(expectedEntity.getGender()));
        when(educationService.getEntityById(inputDto.getEducation().getId())).thenReturn(Optional.of(expectedEntity.getEducation()));
        when(dietService.getEntityById(inputDto.getDiet().getId())).thenReturn(Optional.of(expectedEntity.getDiet()));
        when(sexualOrientationService.getEntityById(inputDto.getSexualOrientation().getId())).thenReturn(Optional.of(expectedEntity.getSexualOrientation()));
        when(socialBackgroundService.getEntityById(inputDto.getSocialBackground().getId())).thenReturn(Optional.of(expectedEntity.getSocialBackground()));
        when(socialBackgroundDiscriminationService.getEntityById(inputDto.getSocialBackgroundDiscrimination().getId())).thenReturn(Optional.of(expectedEntity.getSocialBackgroundDiscrimination()));

        //Act
        Optional<ProfileEntity> profileEntityOptional = profileMapper.dtoToEntity(inputDto);

        //Assert
        assertTrue(profileEntityOptional.isPresent());
        assertEquals(expectedEntity, profileEntityOptional.get());
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        //Arrange
        List<ProfileEntity> inputList = Collections.emptyList();

        //Act
        List<ProfileDto> actualList = this.profileMapper.entityToDto(inputList);

        //Assert
        assertTrue(actualList.isEmpty());
    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        //Arrange
        ProfileEntity inputEntity = factory.buildEntity(1);
        ProfileDto expectedDto = factory.buildDto(1);
        when(this.countryMapper.entityToDto(any(CountryEntity.class))).thenReturn(expectedDto.getOriginCountry());
        when(this.dietMapper.entityToDto(any(DietEntity.class))).thenReturn(expectedDto.getDiet());
        when(this.educationMapper.entityToDto(any(EducationEntity.class))).thenReturn(expectedDto.getEducation());
        when(this.genderMapper.entityToDto(any(GenderEntity.class))).thenReturn(expectedDto.getGender());
        when(this.hobbyMapper.entityToDto(any(HobbyEntity.class))).thenReturn(expectedDto.getHobby());
        when(this.languageMapper.entityToDto(any(LanguageEntity.class))).thenReturn(expectedDto.getMotherTongue());
        when(this.projectMapper.entityToDto(any(ProjectEntity.class))).thenReturn(expectedDto.getProject());
        when(this.religionMapper.entityToDto(any(ReligionEntity.class))).thenReturn(expectedDto.getReligion());
        when(this.sexualOrientationMapper.entityToDto(any(SexualOrientationEntity.class))).thenReturn(expectedDto.getSexualOrientation());
        when(this.workExperienceMapper.entityToDto(any(WorkExperienceEntity.class))).thenReturn(expectedDto.getWorkExperience());
        when(this.socialBackgroundMapper.entityToDto(any(SocialBackgroundEntity.class))).thenReturn(expectedDto.getSocialBackground());
        when(this.socialBackgroundDiscriminationMapper.entityToDto(any(SocialBackgroundDiscriminationEntity.class))).thenReturn(expectedDto.getSocialBackgroundDiscrimination());

        //Act
        ProfileDto actualDto = this.profileMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        //Arrange
        List<ProfileEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<ProfileDto> expectedDtos = Stream.of(1, 2, 3).map(this.factory::buildDto).toList();
        StreamUtils.zip(inputEntities.stream(), expectedDtos.stream(), (entity, dto) -> {
            when(this.countryMapper.entityToDto(entity.getOriginCountry())).thenReturn(dto.getOriginCountry());
            when(this.dietMapper.entityToDto(entity.getDiet())).thenReturn(dto.getDiet());
            when(this.educationMapper.entityToDto(entity.getEducation())).thenReturn(dto.getEducation());
            when(this.genderMapper.entityToDto(entity.getGender())).thenReturn(dto.getGender());
            when(this.hobbyMapper.entityToDto(entity.getHobby())).thenReturn(dto.getHobby());
            when(this.languageMapper.entityToDto(entity.getMotherTongue())).thenReturn(dto.getMotherTongue());
            when(this.projectMapper.entityToDto(entity.getProject())).thenReturn(dto.getProject());
            when(this.religionMapper.entityToDto(entity.getReligion())).thenReturn(dto.getReligion());
            when(this.sexualOrientationMapper.entityToDto(entity.getSexualOrientation())).thenReturn(dto.getSexualOrientation());
            when(this.workExperienceMapper.entityToDto(entity.getWorkExperience())).thenReturn(dto.getWorkExperience());
            when(this.socialBackgroundMapper.entityToDto(entity.getSocialBackground())).thenReturn(dto.getSocialBackground());
            when(this.socialBackgroundDiscriminationMapper.entityToDto(entity.getSocialBackgroundDiscrimination())).thenReturn(dto.getSocialBackgroundDiscrimination());
            return null;
        }).forEach(unused -> {
        });

        //Act
        List<ProfileDto> actualDtos = this.profileMapper.entityToDto(inputEntities);

        //Assert
        assertEquals(3, actualDtos.size());
        assertEquals(expectedDtos, actualDtos);
    }
}
