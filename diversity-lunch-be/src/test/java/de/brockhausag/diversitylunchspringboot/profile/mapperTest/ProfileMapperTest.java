package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.data.CountryRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.*;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    private WorkExperienceService workExperienceService;

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
        ProfileDto expectedDto = factory.buildDto(1);
        ProfileEntity inputEntity = factory.buildEntity(1);

        //Act
        ProfileDto actualDto = this.profileMapper.entityToDto(inputEntity);

        //Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {

    }
}
