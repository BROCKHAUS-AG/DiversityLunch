package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.ProfileMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

    @Mock
    private BasicDimensionService basicDimensionService;

    @Mock
    private MultiselectDimensionService multiselectDimService;

    @Mock
    private WeightedDimensionService weightedDimService;

//    @Mock
//    private CountryMapper countryMapper;
//    @Mock
//    private DietMapper dietMapper;
//    @Mock
//    private EducationMapper educationMapper;
//    @Mock
//    private GenderMapper genderMapper;
//    @Mock
//    private HobbyMapper hobbyMapper;
//    @Mock
//    private LanguageMapper languageMapper;
//    @Mock
//    private ProjectMapper projectMapper;
//    @Mock
//    private ReligionMapper religionMapper;
//    @Mock
//    private SexualOrientationMapper sexualOrientationMapper;
//    @Mock
//    private WorkExperienceMapper workExperienceMapper;
//    @Mock
//    private SocialBackgroundMapper socialBackgroundMapper;
//    @Mock
//    private SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;

    @InjectMocks
    private ProfileMapper profileMapper;
    private ProfileTestdataFactory factory;
    private ProfileDto profileDto;
    private ProfileEntity profileEntity;

    @BeforeEach
    void setup() {
         this.profileDto = factory.buildDto(1);
         this.profileEntity = factory.buildEntity(1);
    }

    @Test
    void workToDo(){
        assertTrue(false);
    }
    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        //Arrange

        BasicDimensionSelectableOption educationSelectable = new BasicDimensionSelectableOption();
        educationSelectable.setId(profileDto.getEducation().getId());
        educationSelectable.setValue(profileDto.getEducation().getDescriptor());

        BasicDimensionSelectableOption countrySelectable = new BasicDimensionSelectableOption();
        countrySelectable.setId(profileDto.getOriginCountry().getId());
        countrySelectable.setValue(profileDto.getOriginCountry().getDescriptor());

        BasicDimensionSelectableOption religionSelectable = new BasicDimensionSelectableOption();
        religionSelectable.setId(profileDto.getReligion().getId());
        religionSelectable.setValue(profileDto.getReligion().getDescriptor());

        BasicDimensionSelectableOption projectSelectable = new BasicDimensionSelectableOption();
        projectSelectable.setId(profileDto.getOriginCountry().getId());
        projectSelectable.setValue(profileDto.getOriginCountry().getDescriptor());

        BasicDimensionSelectableOption languageSelectable = new BasicDimensionSelectableOption();
        languageSelectable.setId(profileDto.getMotherTongue().getId());
        languageSelectable.setValue(profileDto.getMotherTongue().getDescriptor());

        BasicDimensionSelectableOption genderSelectables = new BasicDimensionSelectableOption();
        genderSelectables.setId(profileDto.getGender().getId());
        genderSelectables.setValue(profileDto.getGender().getDescriptor());

        BasicDimensionSelectableOption dietSelectable = new BasicDimensionSelectableOption();
        dietSelectable.setId(profileDto.getEducation().getId());
        dietSelectable.setValue(profileDto.getEducation().getDescriptor());

        WeightedDimensionSelectableOption workExperienceSelectable = new WeightedDimensionSelectableOption();
        educationSelectable.setId(profileDto.getEducation().getId());
        educationSelectable.setValue(profileDto.getEducation().getDescriptor());


       MultiselectDimensionSelectableOption m1 =  new MultiselectDimensionSelectableOption();
       m1.setId(1L);
       m1.setValue("Option 1");

        MultiselectDimensionSelectableOption m2 =  new MultiselectDimensionSelectableOption();
        m2.setId(2L);
        m2.setValue("Option 2");

        MultiselectDimensionSelectableOption m3 =  new MultiselectDimensionSelectableOption();
        m3.setId(3L);
        m3.setValue("Option 3");

        List<MultiselectDimensionSelectableOption> hobbySelectables = List.of(m1, m2,m3);


        when(basicDimensionService.getSelectableOptionById(profileDto.getOriginCountry().getId()).thenReturn(Optional.of(profileEntity.getSelectedBasicValues().));
        when(sexualOrientationService.getEntityById(profileDto.getSexualOrientation().getId())).thenReturn(Optional.of(profileEntity.getSexualOrientation()));
        when(socialBackgroundService.getEntityById(profileDto.getSocialBackground().getId())).thenReturn(Optional.of(profileEntity.getSocialBackground()));
        when(socialBackgroundDiscriminationService.getEntityById(profileDto.getSocialBackgroundDiscrimination().getId())).thenReturn(Optional.of(profileEntity.getSocialBackgroundDiscrimination()));

        when(basicDimensionService.getSelectableOptionById(profileDto.getEducation().getId())).thenReturn(educationSelectable);


        //Act
        Optional<ProfileEntity> profileEntityOptional = profileMapper.dtoToEntity(profileDto);

        //Assert
        assertTrue(profileEntityOptional.isPresent());
        assertEquals(profileEntity, profileEntityOptional.get());
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
    void workToDo2(){
        assertTrue(false);
    }
//    @Test
//    void testEntityToDto_withOneEntity_returnsOneDto() {
//        //Arrange
//        ProfileEntity inputEntity = factory.buildEntity(1);
//        ProfileDto expectedDto = factory.buildDto(1);
//        when(this.countryMapper.entityToDto(any(CountryEntity.class))).thenReturn(expectedDto.getOriginCountry());
//        when(this.dietMapper.entityToDto(any(DietEntity.class))).thenReturn(expectedDto.getDiet());
//        when(this.educationMapper.entityToDto(any(EducationEntity.class))).thenReturn(expectedDto.getEducation());
//        when(this.genderMapper.entityToDto(any(GenderEntity.class))).thenReturn(expectedDto.getGender());
//        when(this.hobbyMapper.entityToDto(anyList())).thenReturn(expectedDto.getHobby());
//        when(this.languageMapper.entityToDto(any(LanguageEntity.class))).thenReturn(expectedDto.getMotherTongue());
//        when(this.projectMapper.entityToDto(any(ProjectEntity.class))).thenReturn(expectedDto.getProject());
//        when(this.religionMapper.entityToDto(any(ReligionEntity.class))).thenReturn(expectedDto.getReligion());
//        when(this.sexualOrientationMapper.entityToDto(any(SexualOrientationEntity.class))).thenReturn(expectedDto.getSexualOrientation());
//        when(this.workExperienceMapper.entityToDto(any(WorkExperienceEntity.class))).thenReturn(expectedDto.getWorkExperience());
//        when(this.socialBackgroundMapper.entityToDto(any(SocialBackgroundEntity.class))).thenReturn(expectedDto.getSocialBackground());
//        when(this.socialBackgroundDiscriminationMapper.entityToDto(any(SocialBackgroundDiscriminationEntity.class))).thenReturn(expectedDto.getSocialBackgroundDiscrimination());
//
//        //Act
//        ProfileDto actualDto = this.profileMapper.entityToDto(inputEntity);
//
//        //Assert
//        assertEquals(expectedDto, actualDto);
//    }
//
//    @Test
//    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
//        //Arrange
//        List<ProfileEntity> inputEntities = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
//        List<ProfileDto> expectedDtos = Stream.of(1, 2, 3).map(this.factory::buildDto).toList();
//        StreamUtils.zip(inputEntities.stream(), expectedDtos.stream(), (entity, dto) -> {
//            when(this.countryMapper.entityToDto(entity.getOriginCountry())).thenReturn(dto.getOriginCountry());
//            when(this.dietMapper.entityToDto(entity.getDiet())).thenReturn(dto.getDiet());
//            when(this.educationMapper.entityToDto(entity.getEducation())).thenReturn(dto.getEducation());
//            when(this.genderMapper.entityToDto(entity.getGender())).thenReturn(dto.getGender());
//            when(this.hobbyMapper.entityToDto(entity.getHobby())).thenReturn(dto.getHobby());
//            when(this.languageMapper.entityToDto(entity.getMotherTongue())).thenReturn(dto.getMotherTongue());
//            when(this.projectMapper.entityToDto(entity.getProject())).thenReturn(dto.getProject());
//            when(this.religionMapper.entityToDto(entity.getReligion())).thenReturn(dto.getReligion());
//            when(this.sexualOrientationMapper.entityToDto(entity.getSexualOrientation())).thenReturn(dto.getSexualOrientation());
//            when(this.workExperienceMapper.entityToDto(entity.getWorkExperience())).thenReturn(dto.getWorkExperience());
//            when(this.socialBackgroundMapper.entityToDto(entity.getSocialBackground())).thenReturn(dto.getSocialBackground());
//            when(this.socialBackgroundDiscriminationMapper.entityToDto(entity.getSocialBackgroundDiscrimination())).thenReturn(dto.getSocialBackgroundDiscrimination());
//            return null;
//        }).forEach(unused -> {
//        });
//
//        //Act
//        List<ProfileDto> actualDtos = this.profileMapper.entityToDto(inputEntities);
//
//        //Assert
//        assertEquals(3, actualDtos.size());
//        assertEquals(expectedDtos, actualDtos);
//    }
}
