package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.BasicSelectableOptionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.DimensionDtoDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.MultiselectSelectableOptionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.WeightedSelectableOptionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DimensionMapperTest {

    private DimensionDtoDataFactory dimensionDtoDataFactory;
    private final DimensionCategory category = new DimensionCategory(1L,"test", "test failed ?" );
    private final BasicDimension basicDimension = BasicDimension.builder()
            .id(1L)
            .dimensionCategory(category)
            .build();
    private final MultiselectDimension multiselectDimension = MultiselectDimension.builder()
            .id(2L)
            .dimensionCategory(category)
            .build();
    private final  WeightedDimension weightedDimension = WeightedDimension.builder()
            .id(3L)
            .dimensionCategory(category)
            .build();

    //MultiselectDimension
    @Mock
    private MultiselectDimensionService multiselectDimensionService;
    private MultiselectSelectableOptionTestDataFactory multiselectDimensionFactory;
    @InjectMocks
    private HobbyMapper hobbyMapper;


    //WeightedDimension
    @Mock
    private WeightedDimensionService weightedDimensionService;
    private WeightedSelectableOptionTestDataFactory weightedDimensionFactory;
    @InjectMocks
    private WorkExperienceMapper workExperienceMapper;


    //BasicDimension
    @Mock
    private BasicDimensionService basicDimensionService;
    private BasicSelectableOptionTestDataFactory basicDimensionFactory;
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
        this.dimensionDtoDataFactory = new DimensionDtoDataFactory();

        this.multiselectDimensionFactory = new MultiselectSelectableOptionTestDataFactory();
        this.hobbyMapper = new HobbyMapper(multiselectDimensionService);

        this.weightedDimensionFactory = new WeightedSelectableOptionTestDataFactory();
        this.workExperienceMapper = new WorkExperienceMapper(weightedDimensionService);


        this.basicDimensionFactory = new BasicSelectableOptionTestDataFactory();
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
        BasicDimensionSelectableOption inputBasicSelectableOptionEntity = basicDimensionFactory.buildEntity(category,1);
        MultiselectDimensionSelectableOption inputMultiSelectableOptionEntity = multiselectDimensionFactory.buildEntity(category, 1);
        WeightedDimensionSelectableOption inputWeightedSelectableOptionEntity = weightedDimensionFactory.buildEntity(category, 1);

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
        HobbyDto actualHobbyDto = hobbyMapper.entityToDto(inputMultiSelectableOptionEntity);

        WorkExperienceDto actualWorkExperienceDto = workExperienceMapper.entityToDto(inputWeightedSelectableOptionEntity);

        CountryDto actualCountryDto = countryMapper.entityToDto(inputBasicSelectableOptionEntity);
        DietDto actualDietDto = dietMapper.entityToDto(inputBasicSelectableOptionEntity);
        EducationDto actualEducationDto = educationMapper.entityToDto(inputBasicSelectableOptionEntity);
        GenderDto actualGenderDto = genderMapper.entityToDto(inputBasicSelectableOptionEntity);
        LanguageDto actualLanguageDto = languageMapper.entityToDto(inputBasicSelectableOptionEntity);
        ProjectDto actualProjectDto = projectMapper.entityToDto(inputBasicSelectableOptionEntity);
        ReligionDto actualReligionDto = religionMapper.entityToDto(inputBasicSelectableOptionEntity);
        SexualOrientationDto actualSexualOrientationDto = sexualOrientationMapper.entityToDto(inputBasicSelectableOptionEntity);
        SocialBackgroundDiscriminationDto actualSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationMapper.entityToDto(inputBasicSelectableOptionEntity);
        SocialBackgroundDto actualSocialBackgroundDto = socialBackgroundMapper.entityToDto(inputBasicSelectableOptionEntity);


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
        List<BasicDimensionSelectableOption> inputBasicSelectableOptionEntities = Stream.of(1, 2).map(value -> basicDimensionFactory.buildEntity(category, value)).toList();
        List<MultiselectDimensionSelectableOption> inputMultiSelectableOptionEntities = Stream.of(1, 2).map(value -> multiselectDimensionFactory.buildEntity(category, value)).toList();
        List<WeightedDimensionSelectableOption> inputWeightedSelectableOptionEntities = Stream.of(1, 2).map(value -> weightedDimensionFactory.buildEntity(category, value)).toList();


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
        List<HobbyDto> actualHobbyDtos = hobbyMapper.entityToDto(inputMultiSelectableOptionEntities);

        List<WorkExperienceDto> actualWorkExperienceDtos = workExperienceMapper.entityToDto(inputWeightedSelectableOptionEntities);

        List<CountryDto> actualCountryDtos = countryMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<DietDto> actualDietDtos = dietMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<EducationDto> actualEducationDtos = educationMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<GenderDto> actualGenderDtos = genderMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<LanguageDto> actualLanguageDtos = languageMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<ProjectDto> actualProjectDtos = projectMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<ReligionDto> actualReligionDtos = religionMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<SexualOrientationDto> actualSexualOrientationDtos = sexualOrientationMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<SocialBackgroundDiscriminationDto> actualSocialBackgroundDiscriminationDtos = socialBackgroundDiscriminationMapper.entityToDto(inputBasicSelectableOptionEntities);
        List<SocialBackgroundDto> actualSocialBackgroundDtos = socialBackgroundMapper.entityToDto(inputBasicSelectableOptionEntities);


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

        BasicDimensionSelectableOption expectedBasicSelectableOptionEntity = basicDimensionFactory.buildEntity(category, 1);
        MultiselectDimensionSelectableOption expectedMultiSelectableOptionEntity = multiselectDimensionFactory.buildEntity(category, 1);
        WeightedDimensionSelectableOption expectedWeightedSelectableOptionEntity = weightedDimensionFactory.buildEntity(category, 1);

        when(multiselectDimensionService.getDimension(anyString())).thenReturn(multiselectDimension);
        when(weightedDimensionService.getDimension(anyString())).thenReturn(weightedDimension);
        when(basicDimensionService.getDimension(anyString())).thenReturn(basicDimension);

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

        assertEquals(expectedMultiSelectableOptionEntity, actualHobbyMapperOutput);

        assertEquals(expectedWeightedSelectableOptionEntity, actualWorkExperienceMapperOutput);

        assertEquals(expectedBasicSelectableOptionEntity, actualCountryMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualDietMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualEducationMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualGenderMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualLanguageMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualProjectMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualReligionMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualSexualOrientationMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualSocialBackgroundDiscriminationMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntity, actualSocialBackgroundMapperOutput);
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

        List<BasicDimensionSelectableOption> expectedBasicSelectableOptionEntities = Stream.of(1, 2).map(value -> basicDimensionFactory.buildEntity(category, value)).toList();
        List<MultiselectDimensionSelectableOption> expectedMultiSelectableOptionEntities = Stream.of(1, 2).map(value -> multiselectDimensionFactory.buildEntity(category, value)).toList();
        List<WeightedDimensionSelectableOption> expectedWeightedSelectableOptionEntities = Stream.of(1, 2).map(value -> weightedDimensionFactory.buildEntity(category, value)).toList();

        when(multiselectDimensionService.getDimension(anyString())).thenReturn(multiselectDimension);
        when(weightedDimensionService.getDimension(anyString())).thenReturn(weightedDimension);
        when(basicDimensionService.getDimension(anyString())).thenReturn(basicDimension);

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

        assertEquals(expectedMultiSelectableOptionEntities, actualHobbyMapperOutput);

        assertEquals(expectedWeightedSelectableOptionEntities, actualWorkExperienceMapperOutput);

        assertEquals(expectedBasicSelectableOptionEntities, actualCountryMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualDietMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualEducationMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualGenderMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualLanguageMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualProjectMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualReligionMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualSexualOrientationMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualSocialBackgroundDiscriminationMapperOutput);
        assertEquals(expectedBasicSelectableOptionEntities, actualSocialBackgroundMapperOutput);
    }
}
