package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DimensionMapperTest {

    private DimensionDtoDataFactory dimensionDtoDataFactory;

    //MultiselectDimension
    @Mock
    private MultiselectDimensionService multiselectDimensionService;
    private MultiselectDimensionTestDataFactory multiselectDimensionTestDataFactory;
    @InjectMocks
    private HobbyMapper hobbyMapper;


    //WeightedDimension
    @Mock
    private WeightedDimensionService weightedDimensionService;
    private WeightedDimensionTestDataFactory weightedDimensionTestDataFactory;
    @InjectMocks
    private WorkExperienceMapper workExperienceMapper;


    //BasicDimension
    @Mock
    private BasicDimensionService basicDimensionService;
    private BasicDimensionTestDataFactory basicDimensionTestDataFactory;
    @InjectMocks
    private CountryMapper countryMapper;
    @InjectMocks
    private DietMapper dietMapper;
    @InjectMocks
    private EducationMapper educationMapper;
    @InjectMocks
    private GenderMapper genderMapper;
    @InjectMocks
    private LanguageMapper languageMapper;
    @InjectMocks
    private ProjectMapper projectMapper;
    @InjectMocks
    private ReligionMapper religionMapper;
    @InjectMocks
    private SexualOrientationMapper sexualOrientationMapper;
    @InjectMocks
    private SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;
    @InjectMocks
    private SocialBackgroundMapper socialBackgroundMapper;





    @BeforeEach
    void setup()
    {
        this.basicDimensionTestDataFactory = new BasicDimensionTestDataFactory();
        this.dimensionDtoDataFactory = new DimensionDtoDataFactory();

        this.multiselectDimensionTestDataFactory = new MultiselectDimensionTestDataFactory();
        this.hobbyMapper = new HobbyMapper(multiselectDimensionService);

        this.weightedDimensionTestDataFactory = new WeightedDimensionTestDataFactory();
        this.workExperienceMapper = new WorkExperienceMapper(weightedDimensionService);


        this.countryMapper = new CountryMapper(basicDimensionService);
        this.dietMapper = new DietMapper(basicDimensionService);
        this.educationMapper = new EducationMapper(basicDimensionService);
        this.genderMapper = new GenderMapper(basicDimensionService);
        this.languageMapper = new LanguageMapper(basicDimensionService);
        this.projectMapper = new ProjectMapper(basicDimensionService);
        this.religionMapper = new ReligionMapper(basicDimensionService);
        this.sexualOrientationMapper = new SexualOrientationMapper(basicDimensionService);
        this.socialBackgroundDiscriminationMapper = new SocialBackgroundDiscriminationMapper(basicDimensionService);
        this.socialBackgroundMapper = new SocialBackgroundMapper(basicDimensionService);

    }

    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        // Arrange
        BasicDimensionSelectableOption inputCountryEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(3).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputDietEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(7).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputEducationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(6).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputGenderEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(2).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputLanguageEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(5).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputProjectEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(1).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputReligionEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(4).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputSexualOrientationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(10).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputSocialBackgroundDiscriminationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(9).getSelectableValues()).get(1);
        BasicDimensionSelectableOption inputSocialBackgroundEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(8).getSelectableValues()).get(1);
        MultiselectDimensionSelectableOption inputHobbyEntity = List.copyOf(multiselectDimensionTestDataFactory.buildEntity(1).getSelectableValues()).get(0);
        WeightedDimensionSelectableOption inputWorkExperienceEntity = List.copyOf(weightedDimensionTestDataFactory.buildEntity(1).getSelectableValues()).get(1);

        CountryDto expectedCountryDto = dimensionDtoDataFactory.buildCountryDto(1);
        DietDto expectedDietDto = dimensionDtoDataFactory.buildDietDto(1);
        EducationDto expectedEducationDto = dimensionDtoDataFactory.buildEducationDto(1);
        GenderDto expectedGenderDto = dimensionDtoDataFactory.buildGenderDto(1);
        HobbyDto expectedHobbyDto = dimensionDtoDataFactory.buildHobbyDto(1);
        LanguageDto expectedLanguageDto = dimensionDtoDataFactory.buildLanguageDto(1);
        ProjectDto expectedProjectDto = dimensionDtoDataFactory.buildProjectDto(1);
        ReligionDto expectedReligionDto = dimensionDtoDataFactory.buildReligionDto(1);
        SexualOrientationDto expectedSexualOrientationDto = dimensionDtoDataFactory.buildSexualOrientationDto(1);
        SocialBackgroundDiscriminationDto expectedSocialBackgroundDiscriminationDto = dimensionDtoDataFactory.buildSocialBackgroundDiscriminationDto(1);
        SocialBackgroundDto expectedSocialBackgroundDto = dimensionDtoDataFactory.buildSocialBackgroundDto(1);
        WorkExperienceDto expectedWorkExperienceDto = dimensionDtoDataFactory.buildWorkExperienceDto(1);

        // Act
        HobbyDto actualHobbyDto = hobbyMapper.entityToDto(inputHobbyEntity);

        WorkExperienceDto actualWorkExperienceDto = workExperienceMapper.entityToDto(inputWorkExperienceEntity);

        CountryDto actualCountryDto = countryMapper.entityToDto(inputCountryEntity);
        DietDto actualDietDto = dietMapper.entityToDto(inputDietEntity);
        EducationDto actualEducationDto = educationMapper.entityToDto(inputEducationEntity);
        GenderDto actualGenderDto = genderMapper.entityToDto(inputGenderEntity);
        LanguageDto actualLanguageDto = languageMapper.entityToDto(inputLanguageEntity);
        ProjectDto actualProjectDto = projectMapper.entityToDto(inputProjectEntity);
        ReligionDto actualReligionDto = religionMapper.entityToDto(inputReligionEntity);
        SexualOrientationDto actualSexualOrientationDto = sexualOrientationMapper.entityToDto(inputSexualOrientationEntity);
        SocialBackgroundDiscriminationDto actualSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationMapper.entityToDto(inputSocialBackgroundDiscriminationEntity);
        SocialBackgroundDto actualSocialBackgroundDto = socialBackgroundMapper.entityToDto(inputSocialBackgroundEntity);


        // Assert
        assertEquals(expectedCountryDto, actualCountryDto);
        assertEquals(expectedDietDto, actualDietDto);
        assertEquals(expectedEducationDto, actualEducationDto);
        assertEquals(expectedGenderDto, actualGenderDto);
        assertEquals(expectedHobbyDto, actualHobbyDto);
        assertEquals(expectedLanguageDto, actualLanguageDto);
        assertEquals(expectedProjectDto, actualProjectDto);
        assertEquals(expectedReligionDto, actualReligionDto);
        assertEquals(expectedSexualOrientationDto, actualSexualOrientationDto);
        assertEquals(expectedSocialBackgroundDiscriminationDto, actualSocialBackgroundDiscriminationDto);
        assertEquals(expectedSocialBackgroundDto, actualSocialBackgroundDto);
        assertEquals(expectedWorkExperienceDto, actualWorkExperienceDto);
    }

    @Test
    void testEntityToDto_withListOfTwoEntities_returnsListOfTwoDtos() {
        List<BasicDimensionSelectableOption> inputCountryEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(3).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputDietEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(7).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputEducationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(6).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputGenderEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(2).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputLanguageEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(5).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputProjectEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(1).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputReligionEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(4).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputSexualOrientationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(10).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputSocialBackgroundDiscriminationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(9).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> inputSocialBackgroundEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(8).getSelectableValues()).subList(1, 3);
        List<MultiselectDimensionSelectableOption> inputHobbyEntity = List.copyOf(multiselectDimensionTestDataFactory.buildEntity(1).getSelectableValues()).subList(0, 2);
        List<WeightedDimensionSelectableOption> inputWorkExperienceEntity = List.copyOf(weightedDimensionTestDataFactory.buildEntity(1).getSelectableValues()).subList(1, 3);


        List<CountryDto> expectedCountryDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildCountryDto).toList();
        List<DietDto> expectedDietDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildDietDto).toList();
        List<EducationDto> expectedEducationDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildEducationDto).toList();
        List<GenderDto> expectedGenderDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildGenderDto).toList();
        List<HobbyDto> expectedHobbyDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildHobbyDto).toList();
        List<LanguageDto> expectedLanguageDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildLanguageDto).toList();
        List<ProjectDto> expectedProjectDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildProjectDto).toList();
        List<ReligionDto> expectedReligionDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildReligionDto).toList();
        List<SexualOrientationDto> expectedSexualOrientationDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildSexualOrientationDto).toList();
        List<SocialBackgroundDiscriminationDto> expectedSocialBackgroundDiscriminationDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildSocialBackgroundDiscriminationDto).toList();
        List<SocialBackgroundDto> expectedSocialBackgroundDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildSocialBackgroundDto).toList();
        List<WorkExperienceDto> expectedWorkExperienceDtos = Stream.of(1, 2).map(dimensionDtoDataFactory::buildWorkExperienceDto).toList();

        // Act
        List<HobbyDto> actualHobbyDtos = hobbyMapper.entityToDto(inputHobbyEntity);

        List<WorkExperienceDto> actualWorkExperienceDtos = workExperienceMapper.entityToDto(inputWorkExperienceEntity);

        List<CountryDto> actualCountryDtos = countryMapper.entityToDto(inputCountryEntity);
        List<DietDto> actualDietDtos = dietMapper.entityToDto(inputDietEntity);
        List<EducationDto> actualEducationDtos = educationMapper.entityToDto(inputEducationEntity);
        List<GenderDto> actualGenderDtos = genderMapper.entityToDto(inputGenderEntity);
        List<LanguageDto> actualLanguageDtos = languageMapper.entityToDto(inputLanguageEntity);
        List<ProjectDto> actualProjectDtos = projectMapper.entityToDto(inputProjectEntity);
        List<ReligionDto> actualReligionDtos = religionMapper.entityToDto(inputReligionEntity);
        List<SexualOrientationDto> actualSexualOrientationDtos = sexualOrientationMapper.entityToDto(inputSexualOrientationEntity);
        List<SocialBackgroundDiscriminationDto> actualSocialBackgroundDiscriminationDtos = socialBackgroundDiscriminationMapper.entityToDto(inputSocialBackgroundDiscriminationEntity);
        List<SocialBackgroundDto> actualSocialBackgroundDtos = socialBackgroundMapper.entityToDto(inputSocialBackgroundEntity);


        // Assert
        assertEquals(2, actualCountryDtos.size());
        assertEquals(2, actualDietDtos.size());
        assertEquals(2, actualEducationDtos.size());
        assertEquals(2, actualGenderDtos.size());
        assertEquals(2, actualHobbyDtos.size());
        assertEquals(2, actualLanguageDtos.size());
        assertEquals(2, actualProjectDtos.size());
        assertEquals(2, actualReligionDtos.size());
        assertEquals(2, actualSexualOrientationDtos.size());
        assertEquals(2, actualSocialBackgroundDiscriminationDtos.size());
        assertEquals(2, actualSocialBackgroundDtos.size());
        assertEquals(2, actualWorkExperienceDtos.size());

        assertEquals(expectedCountryDtos, actualCountryDtos);
        assertEquals(expectedDietDtos, actualDietDtos);
        assertEquals(expectedEducationDtos, actualEducationDtos);
        assertEquals(expectedGenderDtos, actualGenderDtos);
        assertEquals(expectedHobbyDtos, actualHobbyDtos);
        assertEquals(expectedLanguageDtos, actualLanguageDtos);
        assertEquals(expectedProjectDtos, actualProjectDtos);
        assertEquals(expectedReligionDtos, actualReligionDtos);
        assertEquals(expectedSexualOrientationDtos, actualSexualOrientationDtos);
        assertEquals(expectedSocialBackgroundDiscriminationDtos, actualSocialBackgroundDiscriminationDtos);
        assertEquals(expectedSocialBackgroundDtos, actualSocialBackgroundDtos);
        assertEquals(expectedWorkExperienceDtos, actualWorkExperienceDtos);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        // Arrange
        List<BasicDimensionSelectableOption> inputBasicSelectableOptionEntities = Collections.emptyList();
        List<MultiselectDimensionSelectableOption> inputMultiSelectableOptionEntities = Collections.emptyList();
        List<WeightedDimensionSelectableOption> inputWeightedSelectableOptionEntities = Collections.emptyList();


        // Act
        List<HobbyDto> actualHobbyDto = hobbyMapper.entityToDto(inputMultiSelectableOptionEntities);

        List<WorkExperienceDto> actualWorkExperienceDto = workExperienceMapper.entityToDto(inputWeightedSelectableOptionEntities);

        List<CountryDto> actualCountryDto = countryMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<DietDto> actualDietDto = dietMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<EducationDto> actualEducationDto = educationMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<GenderDto> actualGenderDto = genderMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<LanguageDto> actualLanguageDto = languageMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<ProjectDto> actualProjectDto = projectMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<ReligionDto> actualReligionDto = religionMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<SexualOrientationDto> actualSexualOrientationDto = sexualOrientationMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<SocialBackgroundDiscriminationDto> actualSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<SocialBackgroundDto> actualSocialBackgroundDto = socialBackgroundMapper.entityToDto(inputBasicSelectableOptionEntities);


        // Assert
        assertTrue(actualHobbyDto.isEmpty());

        assertTrue(actualWorkExperienceDto.isEmpty());

        assertTrue(actualCountryDto.isEmpty());
        assertTrue(actualDietDto.isEmpty());
        assertTrue(actualEducationDto.isEmpty());
        assertTrue(actualGenderDto.isEmpty());
        assertTrue(actualLanguageDto.isEmpty());
        assertTrue(actualProjectDto.isEmpty());
        assertTrue(actualReligionDto.isEmpty());
        assertTrue(actualSexualOrientationDto.isEmpty());
        assertTrue(actualSocialBackgroundDiscriminationDto.isEmpty());
        assertTrue(actualSocialBackgroundDto.isEmpty());

    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList() {
        // Arrange
        List<HobbyDto> inputHobbyList = Collections.emptyList();

        List<WorkExperienceDto> inputWorkExperienceList = Collections.emptyList();

        List<CountryDto> inputCountryList = Collections.emptyList();
        List<DietDto> inputDietList = Collections.emptyList();
        List<EducationDto> inputEducationList = Collections.emptyList();
        List<GenderDto> inputGenderList = Collections.emptyList();
        List<LanguageDto> inputLanguageList = Collections.emptyList();
        List<ProjectDto> inputProjectList = Collections.emptyList();
        List<ReligionDto> inputReligionList = Collections.emptyList();
        List<SexualOrientationDto> inputSexualOrientationList = Collections.emptyList();
        List<SocialBackgroundDiscriminationDto> inputSocialBackgroundDiscriminationList = Collections.emptyList();
        List<SocialBackgroundDto> inputSocialBackgroundList = Collections.emptyList();

        // Act
        List<MultiselectDimensionSelectableOption> actualHobbyMapperOutput = hobbyMapper.dtoToEntity(inputHobbyList);

        List<WeightedDimensionSelectableOption> actualWorkExperienceMapperOutput = workExperienceMapper.dtoToEntity(inputWorkExperienceList);

        List<BasicDimensionSelectableOption> actualCountryMapperOutput = countryMapper.dtoToEntity(inputCountryList);
        List<BasicDimensionSelectableOption> actualDietMapperOutput = dietMapper.dtoToEntity(inputDietList);
        List<BasicDimensionSelectableOption> actualEducationMapperOutput = educationMapper.dtoToEntity(inputEducationList);
        List<BasicDimensionSelectableOption> actualGenderMapperOutput = genderMapper.dtoToEntity(inputGenderList);
        List<BasicDimensionSelectableOption> actualLanguageMapperOutput = languageMapper.dtoToEntity(inputLanguageList);
        List<BasicDimensionSelectableOption> actualProjectMapperOutput = projectMapper.dtoToEntity(inputProjectList);
        List<BasicDimensionSelectableOption> actualReligionMapperOutput = religionMapper.dtoToEntity(inputReligionList);
        List<BasicDimensionSelectableOption> actualSexualOrientationMapperOutput = sexualOrientationMapper.dtoToEntity(inputSexualOrientationList);
        List<BasicDimensionSelectableOption> actualSocialBackgroundDiscriminationMapperOutput = socialBackgroundDiscriminationMapper.dtoToEntity(inputSocialBackgroundDiscriminationList);
        List<BasicDimensionSelectableOption> actualSocialBackgroundMapperOutput = socialBackgroundMapper.dtoToEntity(inputSocialBackgroundList);

        // Assert
        assertTrue(actualHobbyMapperOutput.isEmpty());

        assertTrue(actualWorkExperienceMapperOutput.isEmpty());

        assertTrue(actualCountryMapperOutput.isEmpty());
        assertTrue(actualDietMapperOutput.isEmpty());
        assertTrue(actualEducationMapperOutput.isEmpty());
        assertTrue(actualGenderMapperOutput.isEmpty());
        assertTrue(actualLanguageMapperOutput.isEmpty());
        assertTrue(actualProjectMapperOutput.isEmpty());
        assertTrue(actualReligionMapperOutput.isEmpty());
        assertTrue(actualSexualOrientationMapperOutput.isEmpty());
        assertTrue(actualSocialBackgroundDiscriminationMapperOutput.isEmpty());
        assertTrue(actualSocialBackgroundMapperOutput.isEmpty());
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        // Arrange
        HobbyDto inputHobbyDto = dimensionDtoDataFactory.buildHobbyDto(1);

        WorkExperienceDto inputWorkExperienceDto = dimensionDtoDataFactory.buildWorkExperienceDto(1);

        CountryDto inputCountryDto = dimensionDtoDataFactory.buildCountryDto(1);
        DietDto inputDietDto = dimensionDtoDataFactory.buildDietDto(1);
        EducationDto inputEducationDto = dimensionDtoDataFactory.buildEducationDto(1);
        GenderDto inputGenderDto = dimensionDtoDataFactory.buildGenderDto(1);
        LanguageDto inputLanguageDto = dimensionDtoDataFactory.buildLanguageDto(1);
        ProjectDto inputProjectDto = dimensionDtoDataFactory.buildProjectDto(1);
        ReligionDto inputReligionDto = dimensionDtoDataFactory.buildReligionDto(1);
        SexualOrientationDto inputSexualOrientationDto = dimensionDtoDataFactory.buildSexualOrientationDto(1);
        SocialBackgroundDiscriminationDto inputSocialBackgroundDiscriminationDto = dimensionDtoDataFactory.buildSocialBackgroundDiscriminationDto(1);
        SocialBackgroundDto inputSocialBackgroundDto = dimensionDtoDataFactory.buildSocialBackgroundDto(1);
        
        BasicDimensionSelectableOption expectedCountryEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(3).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedDietEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(7).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedEducationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(6).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedGenderEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(2).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedLanguageEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(5).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedProjectEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(1).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedReligionEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(4).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedSexualOrientationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(10).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedSocialBackgroundDiscriminationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(9).getSelectableValues()).get(1);
        BasicDimensionSelectableOption expectedSocialBackgroundEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(8).getSelectableValues()).get(1);
        MultiselectDimensionSelectableOption expectedHobbyEntity = List.copyOf(multiselectDimensionTestDataFactory.buildEntity(1).getSelectableValues()).get(0);
        WeightedDimensionSelectableOption expectedWorkExperienceEntity = List.copyOf(weightedDimensionTestDataFactory.buildEntity(1).getSelectableValues()).get(1);

        serviceGetDimensionMock();

        //Act
        MultiselectDimensionSelectableOption actualHobbyMapperOutput = hobbyMapper.dtoToEntity(inputHobbyDto);

        WeightedDimensionSelectableOption actualWorkExperienceMapperOutput = workExperienceMapper.dtoToEntity(inputWorkExperienceDto);

        BasicDimensionSelectableOption actualCountryMapperOutput = countryMapper.dtoToEntity(inputCountryDto);
        BasicDimensionSelectableOption actualDietMapperOutput = dietMapper.dtoToEntity(inputDietDto);
        BasicDimensionSelectableOption actualEducationMapperOutput = educationMapper.dtoToEntity(inputEducationDto);
        BasicDimensionSelectableOption actualGenderMapperOutput = genderMapper.dtoToEntity(inputGenderDto);
        BasicDimensionSelectableOption actualLanguageMapperOutput = languageMapper.dtoToEntity(inputLanguageDto);
        BasicDimensionSelectableOption actualProjectMapperOutput = projectMapper.dtoToEntity(inputProjectDto);
        BasicDimensionSelectableOption actualReligionMapperOutput = religionMapper.dtoToEntity(inputReligionDto);
        BasicDimensionSelectableOption actualSexualOrientationMapperOutput = sexualOrientationMapper.dtoToEntity(inputSexualOrientationDto);
        BasicDimensionSelectableOption actualSocialBackgroundDiscriminationMapperOutput = socialBackgroundDiscriminationMapper.dtoToEntity(inputSocialBackgroundDiscriminationDto);
        BasicDimensionSelectableOption actualSocialBackgroundMapperOutput = socialBackgroundMapper.dtoToEntity(inputSocialBackgroundDto);


        //Assert

        assertEquals(expectedHobbyEntity, actualHobbyMapperOutput);

        assertEquals(expectedWorkExperienceEntity, actualWorkExperienceMapperOutput);

        assertEquals(expectedCountryEntity, actualCountryMapperOutput);
        assertEquals(expectedDietEntity, actualDietMapperOutput);
        assertEquals(expectedEducationEntity, actualEducationMapperOutput);
        assertEquals(expectedGenderEntity, actualGenderMapperOutput);
        assertEquals(expectedLanguageEntity, actualLanguageMapperOutput);
        assertEquals(expectedProjectEntity, actualProjectMapperOutput);
        assertEquals(expectedReligionEntity, actualReligionMapperOutput);
        assertEquals(expectedSexualOrientationEntity, actualSexualOrientationMapperOutput);
        assertEquals(expectedSocialBackgroundDiscriminationEntity, actualSocialBackgroundDiscriminationMapperOutput);
        assertEquals(expectedSocialBackgroundEntity, actualSocialBackgroundMapperOutput);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntity() {
        // Arrange
        List<HobbyDto> inputHobbyList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildHobbyDto).toList();

        List<WorkExperienceDto> inputWorkExperienceList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildWorkExperienceDto).toList();

        List<CountryDto> inputCountryList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildCountryDto).toList();
        List<DietDto> inputDietList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildDietDto).toList();
        List<EducationDto> inputEducationList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildEducationDto).toList();
        List<GenderDto> inputGenderList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildGenderDto).toList();
        List<LanguageDto> inputLanguageList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildLanguageDto).toList();
        List<ProjectDto> inputProjectList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildProjectDto).toList();
        List<ReligionDto> inputReligionList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildReligionDto).toList();
        List<SexualOrientationDto> inputSexualOrientationList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildSexualOrientationDto).toList();
        List<SocialBackgroundDiscriminationDto> inputSocialBackgroundDiscriminationList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildSocialBackgroundDiscriminationDto).toList();
        List<SocialBackgroundDto> inputSocialBackgroundList = Stream.of(1, 2).map(dimensionDtoDataFactory::buildSocialBackgroundDto).toList();

        List<BasicDimensionSelectableOption> expectedCountryEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(3).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedDietEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(7).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedEducationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(6).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedGenderEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(2).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedLanguageEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(5).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedProjectEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(1).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedReligionEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(4).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedSexualOrientationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(10).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedSocialBackgroundDiscriminationEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(9).getSelectableValues()).subList(1, 3);
        List<BasicDimensionSelectableOption> expectedSocialBackgroundEntity = List.copyOf(basicDimensionTestDataFactory.buildEntity(8).getSelectableValues()).subList(1, 3);
        List<MultiselectDimensionSelectableOption> expectedHobbyEntity = List.copyOf(multiselectDimensionTestDataFactory.buildEntity(1).getSelectableValues()).subList(0, 2);
        List<WeightedDimensionSelectableOption> expectedWorkExperienceEntity = List.copyOf(weightedDimensionTestDataFactory.buildEntity(1).getSelectableValues()).subList(1, 3);
        
        serviceGetDimensionMock();

        //Act
        List<MultiselectDimensionSelectableOption> actualHobbyMapperOutput = hobbyMapper.dtoToEntity(inputHobbyList);

        List<WeightedDimensionSelectableOption> actualWorkExperienceMapperOutput = workExperienceMapper.dtoToEntity(inputWorkExperienceList);

        List<BasicDimensionSelectableOption> actualCountryMapperOutput = countryMapper.dtoToEntity(inputCountryList);
        List<BasicDimensionSelectableOption> actualDietMapperOutput = dietMapper.dtoToEntity(inputDietList);
        List<BasicDimensionSelectableOption> actualEducationMapperOutput = educationMapper.dtoToEntity(inputEducationList);
        List<BasicDimensionSelectableOption> actualGenderMapperOutput = genderMapper.dtoToEntity(inputGenderList);
        List<BasicDimensionSelectableOption> actualLanguageMapperOutput = languageMapper.dtoToEntity(inputLanguageList);
        List<BasicDimensionSelectableOption> actualProjectMapperOutput = projectMapper.dtoToEntity(inputProjectList);
        List<BasicDimensionSelectableOption> actualReligionMapperOutput = religionMapper.dtoToEntity(inputReligionList);
        List<BasicDimensionSelectableOption> actualSexualOrientationMapperOutput = sexualOrientationMapper.dtoToEntity(inputSexualOrientationList);
        List<BasicDimensionSelectableOption> actualSocialBackgroundDiscriminationMapperOutput = socialBackgroundDiscriminationMapper.dtoToEntity(inputSocialBackgroundDiscriminationList);
        List<BasicDimensionSelectableOption> actualSocialBackgroundMapperOutput = socialBackgroundMapper.dtoToEntity(inputSocialBackgroundList);


        //Assert
        assertEquals(2, actualHobbyMapperOutput.size());

        assertEquals(2, actualWorkExperienceMapperOutput.size());

        assertEquals(2, actualCountryMapperOutput.size());
        assertEquals(2, actualDietMapperOutput.size());
        assertEquals(2, actualEducationMapperOutput.size());
        assertEquals(2, actualGenderMapperOutput.size());
        assertEquals(2, actualLanguageMapperOutput.size());
        assertEquals(2, actualProjectMapperOutput.size());
        assertEquals(2, actualReligionMapperOutput.size());
        assertEquals(2, actualSexualOrientationMapperOutput.size());
        assertEquals(2, actualSocialBackgroundDiscriminationMapperOutput.size());
        assertEquals(2, actualSocialBackgroundDiscriminationMapperOutput.size());

        assertEquals(expectedHobbyEntity, actualHobbyMapperOutput);

        assertEquals(expectedWorkExperienceEntity, actualWorkExperienceMapperOutput);

        assertEquals(expectedCountryEntity, actualCountryMapperOutput);
        assertEquals(expectedDietEntity, actualDietMapperOutput);
        assertEquals(expectedEducationEntity, actualEducationMapperOutput);
        assertEquals(expectedGenderEntity, actualGenderMapperOutput);
        assertEquals(expectedLanguageEntity, actualLanguageMapperOutput);
        assertEquals(expectedProjectEntity, actualProjectMapperOutput);
        assertEquals(expectedReligionEntity, actualReligionMapperOutput);
        assertEquals(expectedSexualOrientationEntity, actualSexualOrientationMapperOutput);
        assertEquals(expectedSocialBackgroundDiscriminationEntity, actualSocialBackgroundDiscriminationMapperOutput);
        assertEquals(expectedSocialBackgroundEntity, actualSocialBackgroundMapperOutput);
    }

    void serviceGetDimensionMock() {
        when(basicDimensionService.getDimension("Projekt")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(1)));
        when(basicDimensionService.getDimension("Geschlechtliche Identität")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(2)));
        when(basicDimensionService.getDimension("Ethnische Herkunft")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(3)));
        when(basicDimensionService.getDimension("Religion")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(4)));
        when(basicDimensionService.getDimension("Muttersprache")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(5)));
        when(basicDimensionService.getDimension("Bildungsweg")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(6)));
        when(basicDimensionService.getDimension("Ernährung")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(7)));
        when(basicDimensionService.getDimension("Soziale Herkunft")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(8)));
        when(basicDimensionService.getDimension("Diskriminierung aufgrund sozialer Herkunft")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(9)));
        when(basicDimensionService.getDimension("Sexuelle Orientierung")).thenReturn(Optional.of(basicDimensionTestDataFactory.buildEntity(10)));

        when(weightedDimensionService.getDimension("Berufserfahrung")).thenReturn(Optional.of(weightedDimensionTestDataFactory.buildEntity(1)));

        when(multiselectDimensionService.getDimension("Hobby")).thenReturn(Optional.of(multiselectDimensionTestDataFactory.buildEntity(1)));
    }
}
