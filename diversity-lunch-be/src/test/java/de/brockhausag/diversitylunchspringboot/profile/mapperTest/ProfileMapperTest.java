package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.BasicDimensionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.MultiselectDimensionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.WeightedDimensionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.*;
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

    private BasicDimensionTestDataFactory basicDimensionTestDataFactory;
    private ProfileDto profileDto;
    private ProfileEntity profileEntity;
    private WeightedDimensionTestDataFactory weightedDimensionTestDataFactory;
    private MultiselectDimensionTestDataFactory multiselectDimensionTestDataFactory;

    @BeforeEach
    void setup() {
        this.factory = new ProfileTestdataFactory();
        this.basicDimensionTestDataFactory = new BasicDimensionTestDataFactory();
        this.weightedDimensionTestDataFactory = new WeightedDimensionTestDataFactory();
        this.multiselectDimensionTestDataFactory = new MultiselectDimensionTestDataFactory();
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

        when(basicDimensionService.getSelectableOptionById(profileDto.getProject().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(1)));
        when(basicDimensionService.getSelectableOptionById(profileDto.getGender().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(2)));
        when(basicDimensionService.getSelectableOptionById(profileDto.getOriginCountry().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(3)));
        when(basicDimensionService.getSelectableOptionById(profileDto.getReligion().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(4)));
        when(basicDimensionService.getSelectableOptionById(profileDto.getMotherTongue().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(5)));

        when(basicDimensionService.getSelectableOptionById(profileDto.getEducation().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(6)));

        when(basicDimensionService.getSelectableOptionById(profileDto.getDiet().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(7)));

        when(basicDimensionService.getSelectableOptionById(profileDto.getSocialBackground().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(8)));

        when(basicDimensionService.getSelectableOptionById(profileDto.getSocialBackgroundDiscrimination().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(9)));

        when(basicDimensionService.getSelectableOptionById(profileDto.getSexualOrientation().getId()))
                .thenReturn(profileEntity.getSelectedBasicValues().get(basicDimensionTestDataFactory.buildEntity(10)));

        when(weightedDimService.getSelectableOptionById(profileDto.getWorkExperience().getId()))
                .thenReturn(profileEntity.getSelectedWeightedValues().get(weightedDimensionTestDataFactory.buildEntity(1)));

        when(multiselectDimService.getSelectableOptions(profileDto.getHobby().stream().map(HobbyDto::getId).collect(Collectors.toList())))
                .thenReturn(profileEntity.getSelectedMultiselectValues().get(multiselectDimensionTestDataFactory.buildEntity(1)).getSelectedOptions().stream().toList());


        //Act
        Optional<ProfileEntity> profileEntityOptional = profileMapper.dtoToEntity(profileDto);

        //Assert

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
