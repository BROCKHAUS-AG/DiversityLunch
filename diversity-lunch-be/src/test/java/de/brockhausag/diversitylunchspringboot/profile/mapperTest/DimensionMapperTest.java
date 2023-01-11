package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.BasicSelectableOptionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.DimensionDtoDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.MultiselectSelectableOptionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.WeightedSelectableOptionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DimensionMapperTest {

    private DimensionDtoDataFactory dimensionDtoDataFactory;
    private final DimensionCategory category = new DimensionCategory(1L,"test", "test failed ?" );


    //MultiselectDimension
    @Mock
    private MultiselectDimensionService multiselectDimensionService;
    private MultiselectSelectableOptionTestDataFactory multiselectDimensionFactory;
    private HobbyMapper hobbyMapper;


    //WeightedDimension
    @Mock
    private WeightedDimensionService weightedDimensionService;
    private WeightedSelectableOptionTestDataFactory weightedDimensionFactory;
    private WorkExperienceMapper workExperienceMapper;


    //BasicDimension
    @Mock
    private BasicDimensionService basicDimensionService;
    private BasicSelectableOptionTestDataFactory basicDimensionFactory;
    private CountryMapper countryMapper;
    private DietMapper dietMapper;
    private EducationMapper educationMapper;
    private GenderMapper genderMapper;
    private LanguageMapper languageMapper;
    private ProjectMapper projectMapper;
    private ReligionMapper religionMapper;
    private SexualOrientationMapper sexualOrientationMapper;
    private SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;
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
        assertTrue(actualCountryDto.isEmpty());
        assertTrue(actualDietDto.isEmpty());
        assertTrue(actualEducationDto.isEmpty());
        assertTrue(actualGenderDto.isEmpty());
        assertTrue(actualHobbyDto.isEmpty());
        assertTrue(actualLanguageDto.isEmpty());
        assertTrue(actualProjectDto.isEmpty());
        assertTrue(actualReligionDto.isEmpty());
        assertTrue(actualSexualOrientationDto.isEmpty());
        assertTrue(actualSocialBackgroundDiscriminationDto.isEmpty());
        assertTrue(actualSocialBackgroundDto.isEmpty());
        assertTrue(actualWorkExperienceDto.isEmpty());
    }

    @Test
    void workToDo(){
        assertTrue(false);
    }

//    @Test
//    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList() {
//
//    }
//
//    @Test
//    void testDtoToEntity_withOneDto_returnsOneEntity() {
//
//    }
//
//    @Test
//    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntity() {
//
//    }
}
