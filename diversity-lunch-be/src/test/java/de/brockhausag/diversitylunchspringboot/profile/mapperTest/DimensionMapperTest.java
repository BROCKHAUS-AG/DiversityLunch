package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.*;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.*;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DimensionMapperTest {

    CountryTestDataFactory countryFactory;
    DietTestDataFactory dietFactory;
    EducationTestDataFactory educationFactory;
    GenderTestDataFactory genderFactory;
    HobbyTestDataFactory hobbyFactory;
    LanguageTestDataFactory languageFactory;
    ProjectTestDataFactory projectFactory;
    ReligionTestDataFactory religionFactory;
    SexualOrientationTestDataFactory sexualOrientationFactory;
    SocialBackgroundDiscriminationTestDataFactory socialBackgroundDiscriminationFactory;
    SocialBackgroundTestDataFactory socialBackgroundFactory;
    WorkExperienceTestDataFactory workExperienceFactory;
    CountryMapper countryMapper;
    DietMapper dietMapper;
    EducationMapper educationMapper;
    GenderMapper genderMapper;
    HobbyMapper hobbyMapper;
    LanguageMapper languageMapper;
    ProjectMapper projectMapper;
    ReligionMapper religionMapper;
    SexualOrientationMapper sexualOrientationMapper;
    SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;
    SocialBackgroundMapper socialBackgroundMapper;
    WorkExperienceMapper workExperienceMapper;



    @BeforeEach
    void setup()
    {
        this.countryFactory = new CountryTestDataFactory();
        this.dietFactory = new DietTestDataFactory();
        this.educationFactory = new EducationTestDataFactory();
        this.genderFactory = new GenderTestDataFactory();
        this.hobbyFactory = new HobbyTestDataFactory();
        this.languageFactory = new LanguageTestDataFactory();
        this.projectFactory = new ProjectTestDataFactory();
        this.religionFactory = new ReligionTestDataFactory();
        this.sexualOrientationFactory = new SexualOrientationTestDataFactory();
        this.socialBackgroundDiscriminationFactory = new SocialBackgroundDiscriminationTestDataFactory();
        this.socialBackgroundFactory = new SocialBackgroundTestDataFactory();
        this.workExperienceFactory = new WorkExperienceTestDataFactory();

        this.countryMapper = new CountryMapper();
        this.dietMapper = new DietMapper();
        this.educationMapper = new EducationMapper();
        this.genderMapper = new GenderMapper();
        this.hobbyMapper = new HobbyMapper();
        this.languageMapper = new LanguageMapper();
        this.projectMapper = new ProjectMapper();
        this.religionMapper = new ReligionMapper();
        this.sexualOrientationMapper = new SexualOrientationMapper();
        this.socialBackgroundDiscriminationMapper = new SocialBackgroundDiscriminationMapper();
        this.socialBackgroundMapper = new SocialBackgroundMapper();
        this.workExperienceMapper = new WorkExperienceMapper();
    }


    @Test
    void testEntityToDto_withOneEntity_returnsOneDto() {
        // Arrange
        CountryEntity inputCountryEntity = countryFactory.buildEntity(1);
        DietEntity inputDietEntity = dietFactory.buildEntity(1);
        EducationEntity inputEducationEntity = educationFactory.buildEntity(1);
        GenderEntity inputGenderEntity = genderFactory.buildEntity(1);
        HobbyEntity inputHobbyEntity = hobbyFactory.buildEntity(1);
        LanguageEntity inputLanguageEntity = languageFactory.buildEntity(1);
        ProjectEntity inputProjectEntity = projectFactory.buildEntity(1);
        ReligionEntity inputReligionEntity = religionFactory.buildEntity(1);
        SexualOrientationEntity inputSexualOrientationEntity = sexualOrientationFactory.buildEntity(1);
        SocialBackgroundDiscriminationEntity inputSocialBackgroundDiscriminationEntity = socialBackgroundDiscriminationFactory.buildEntity(1);
        SocialBackgroundEntity inputSocialBackgroundEntity = socialBackgroundFactory.buildEntity(1);
        WorkExperienceEntity inputWorkExperienceEntity = workExperienceFactory.buildEntity(1);

        CountryDto expectedCountryDto = countryFactory.buildDto(1);
        DietDto expectedDietDto = dietFactory.buildDto(1);
        EducationDto expectedEducationDto = educationFactory.buildDto(1);
        GenderDto expectedGenderDto = genderFactory.buildDto(1);
        HobbyDto expectedHobbyDto = hobbyFactory.buildDto(1);
        LanguageDto expectedLanguageDto = languageFactory.buildDto(1);
        ProjectDto expectedProjectDto = projectFactory.buildDto(1);
        ReligionDto expectedReligionDto = religionFactory.buildDto(1);
        SexualOrientationDto expectedSexualOrientationDto = sexualOrientationFactory.buildDto(1);
        SocialBackgroundDiscriminationDto expectedSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationFactory.buildDto(1);
        SocialBackgroundDto expectedSocialBackgroundDto = socialBackgroundFactory.buildDto(1);
        WorkExperienceDto expectedWorkExperienceDto = workExperienceFactory.buildDto(1);

        // Act
        CountryDto actualCountryDto = countryMapper.entityToDto(inputCountryEntity);
        DietDto actualDietDto = dietMapper.entityToDto(inputDietEntity);
        EducationDto actualEducationDto = educationMapper.entityToDto(inputEducationEntity);
        GenderDto actualGenderDto = genderMapper.entityToDto(inputGenderEntity);
        HobbyDto actualHobbyDto = hobbyMapper.entityToDto(inputHobbyEntity);
        LanguageDto actualLanguageDto = languageMapper.entityToDto(inputLanguageEntity);
        ProjectDto actualProjectDto = projectMapper.entityToDto(inputProjectEntity);
        ReligionDto actualReligionDto = religionMapper.entityToDto(inputReligionEntity);
        SexualOrientationDto actualSexualOrientationDto = sexualOrientationMapper.entityToDto(inputSexualOrientationEntity);
        SocialBackgroundDiscriminationDto actualSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationMapper.entityToDto(inputSocialBackgroundDiscriminationEntity);
        SocialBackgroundDto actualSocialBackgroundDto = socialBackgroundMapper.entityToDto(inputSocialBackgroundEntity);
        WorkExperienceDto actualWorkExperienceDto = workExperienceMapper.entityToDto(inputWorkExperienceEntity);

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
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        List<CountryEntity> inputCountryEntity = Stream.of(1, 2, 3).map(countryFactory::buildEntity).toList();
        List<DietEntity> inputDietEntity = Stream.of(1, 2, 3).map(dietFactory::buildEntity).toList();
        List<EducationEntity> inputEducationEntity = Stream.of(1, 2, 3).map(educationFactory::buildEntity).toList();
        List<GenderEntity> inputGenderEntity = Stream.of(1, 2, 3).map(genderFactory::buildEntity).toList();
        List<HobbyEntity> inputHobbyEntity = Stream.of(1, 2, 3).map(hobbyFactory::buildEntity).toList();
        List<LanguageEntity> inputLanguageEntity = Stream.of(1, 2, 3).map(languageFactory::buildEntity).toList();
        List<ProjectEntity> inputProjectEntity = Stream.of(1, 2, 3).map(projectFactory::buildEntity).toList();
        List<ReligionEntity> inputReligionEntity = Stream.of(1, 2, 3).map(religionFactory::buildEntity).toList();
        List<SexualOrientationEntity> inputSexualOrientationEntity = Stream.of(1, 2, 3).map(sexualOrientationFactory::buildEntity).toList();
        List<SocialBackgroundDiscriminationEntity> inputSocialBackgroundDiscriminationEntity = Stream.of(1, 2, 3).map(socialBackgroundDiscriminationFactory::buildEntity).toList();
        List<SocialBackgroundEntity> inputSocialBackgroundEntity = Stream.of(1, 2, 3).map(socialBackgroundFactory::buildEntity).toList();
        List<WorkExperienceEntity> inputWorkExperienceEntity = Stream.of(1, 2, 3).map(workExperienceFactory::buildEntity).toList();

        List<CountryDto> expectedCountryDto = Stream.of(1, 2, 3).map(countryFactory::buildDto).toList();
        List<DietDto> expectedDietDto = Stream.of(1, 2, 3).map(dietFactory::buildDto).toList();
        List<EducationDto> expectedEducationDto = Stream.of(1, 2, 3).map(educationFactory::buildDto).toList();
        List<GenderDto> expectedGenderDto = Stream.of(1, 2, 3).map(genderFactory::buildDto).toList();
        List<HobbyDto> expectedHobbyDto = Stream.of(1, 2, 3).map(hobbyFactory::buildDto).toList();
        List<LanguageDto> expectedLanguageDto = Stream.of(1, 2, 3).map(languageFactory::buildDto).toList();
        List<ProjectDto> expectedProjectDto = Stream.of(1, 2, 3).map(projectFactory::buildDto).toList();
        List<ReligionDto> expectedReligionDto = Stream.of(1, 2, 3).map(religionFactory::buildDto).toList();
        List<SexualOrientationDto> expectedSexualOrientationDto = Stream.of(1, 2, 3).map(sexualOrientationFactory::buildDto).toList();
        List<SocialBackgroundDiscriminationDto> expectedSocialBackgroundDiscriminationDto = Stream.of(1, 2, 3).map(socialBackgroundDiscriminationFactory::buildDto).toList();
        List<SocialBackgroundDto> expectedSocialBackgroundDto = Stream.of(1, 2, 3).map(socialBackgroundFactory::buildDto).toList();
        List<WorkExperienceDto> expectedWorkExperienceDto = Stream.of(1, 2, 3).map(workExperienceFactory::buildDto).toList();

        // Act
        List<CountryDto> actualCountryDto = countryMapper.entityToDto(inputCountryEntity);
        List<DietDto> actualDietDto = dietMapper.entityToDto(inputDietEntity);
        List<EducationDto> actualEducationDto = educationMapper.entityToDto(inputEducationEntity);
        List<GenderDto> actualGenderDto = genderMapper.entityToDto(inputGenderEntity);
        List<HobbyDto> actualHobbyDto = hobbyMapper.entityToDto(inputHobbyEntity);
        List<LanguageDto> actualLanguageDto = languageMapper.entityToDto(inputLanguageEntity);
        List<ProjectDto> actualProjectDto = projectMapper.entityToDto(inputProjectEntity);
        List<ReligionDto> actualReligionDto = religionMapper.entityToDto(inputReligionEntity);
        List<SexualOrientationDto> actualSexualOrientationDto = sexualOrientationMapper.entityToDto(inputSexualOrientationEntity);
        List<SocialBackgroundDiscriminationDto> actualSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationMapper.entityToDto(inputSocialBackgroundDiscriminationEntity);
        List<SocialBackgroundDto> actualSocialBackgroundDto = socialBackgroundMapper.entityToDto(inputSocialBackgroundEntity);
        List<WorkExperienceDto> actualWorkExperienceDto = workExperienceMapper.entityToDto(inputWorkExperienceEntity);

        // Assert
        assertEquals(3, actualCountryDto.size());
        assertEquals(3, actualDietDto.size());
        assertEquals(3, actualEducationDto.size());
        assertEquals(3, actualGenderDto.size());
        assertEquals(3, actualHobbyDto.size());
        assertEquals(3, actualLanguageDto.size());
        assertEquals(3, actualProjectDto.size());
        assertEquals(3, actualReligionDto.size());
        assertEquals(3, actualSexualOrientationDto.size());
        assertEquals(3, actualSocialBackgroundDiscriminationDto.size());
        assertEquals(3, actualSocialBackgroundDto.size());
        assertEquals(3, actualWorkExperienceDto.size());
        
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
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        // Arrange
        List<CountryEntity> inputCountryEntity = Collections.emptyList();
        List<DietEntity> inputDietEntity = Collections.emptyList();
        List<EducationEntity> inputEducationEntity = Collections.emptyList();
        List<GenderEntity> inputGenderEntity = Collections.emptyList();
        List<HobbyEntity> inputHobbyEntity = Collections.emptyList();
        List<LanguageEntity> inputLanguageEntity = Collections.emptyList();
        List<ProjectEntity> inputProjectEntity = Collections.emptyList();
        List<ReligionEntity> inputReligionEntity = Collections.emptyList();
        List<SexualOrientationEntity> inputSexualOrientationEntity = Collections.emptyList();
        List<SocialBackgroundDiscriminationEntity> inputSocialBackgroundDiscriminationEntity = Collections.emptyList();
        List<SocialBackgroundEntity> inputSocialBackgroundEntity = Collections.emptyList();
        List<WorkExperienceEntity> inputWorkExperienceEntity = Collections.emptyList();

        // Act
        List<CountryDto> actualCountryDto = countryMapper.entityToDto(inputCountryEntity);
        List<DietDto> actualDietDto = dietMapper.entityToDto(inputDietEntity);
        List<EducationDto> actualEducationDto = educationMapper.entityToDto(inputEducationEntity);
        List<GenderDto> actualGenderDto = genderMapper.entityToDto(inputGenderEntity);
        List<HobbyDto> actualHobbyDto = hobbyMapper.entityToDto(inputHobbyEntity);
        List<LanguageDto> actualLanguageDto = languageMapper.entityToDto(inputLanguageEntity);
        List<ProjectDto> actualProjectDto = projectMapper.entityToDto(inputProjectEntity);
        List<ReligionDto> actualReligionDto = religionMapper.entityToDto(inputReligionEntity);
        List<SexualOrientationDto> actualSexualOrientationDto = sexualOrientationMapper.entityToDto(inputSexualOrientationEntity);
        List<SocialBackgroundDiscriminationDto> actualSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationMapper.entityToDto(inputSocialBackgroundDiscriminationEntity);
        List<SocialBackgroundDto> actualSocialBackgroundDto = socialBackgroundMapper.entityToDto(inputSocialBackgroundEntity);
        List<WorkExperienceDto> actualWorkExperienceDto = workExperienceMapper.entityToDto(inputWorkExperienceEntity);

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
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        // Arrange
        CountryDto inputCountryDto = countryFactory.buildDto(1);
        DietDto inputDietDto = dietFactory.buildDto(1);
        EducationDto inputEducationDto = educationFactory.buildDto(1);
        GenderDto inputGenderDto = genderFactory.buildDto(1);
        HobbyDto inputHobbyDto = hobbyFactory.buildDto(1);
        LanguageDto inputLanguageDto = languageFactory.buildDto(1);
        ProjectDto inputProjectDto = projectFactory.buildDto(1);
        ReligionDto inputReligionDto = religionFactory.buildDto(1);
        SexualOrientationDto inputSexualOrientationDto = sexualOrientationFactory.buildDto(1);
        SocialBackgroundDiscriminationDto inputSocialBackgroundDiscriminationDto = socialBackgroundDiscriminationFactory.buildDto(1);
        SocialBackgroundDto inputSocialBackgroundDto = socialBackgroundFactory.buildDto(1);
        WorkExperienceDto inputWorkExperienceDto = workExperienceFactory.buildDto(1);

        CountryEntity expectedCountryEntity = countryFactory.buildEntity(1);
        DietEntity expectedDietEntity = dietFactory.buildEntity(1);
        EducationEntity expectedEducationEntity = educationFactory.buildEntity(1);
        GenderEntity expectedGenderEntity = genderFactory.buildEntity(1);
        HobbyEntity expectedHobbyEntity = hobbyFactory.buildEntity(1);
        LanguageEntity expectedLanguageEntity = languageFactory.buildEntity(1);
        ProjectEntity expectedProjectEntity = projectFactory.buildEntity(1);
        ReligionEntity expectedReligionEntity = religionFactory.buildEntity(1);
        SexualOrientationEntity expectedSexualOrientationEntity = sexualOrientationFactory.buildEntity(1);
        SocialBackgroundDiscriminationEntity expectedSocialBackgroundDiscriminationEntity = socialBackgroundDiscriminationFactory.buildEntity(1);
        SocialBackgroundEntity expectedSocialBackgroundEntity = socialBackgroundFactory.buildEntity(1);
        WorkExperienceEntity expectedWorkExperienceEntity = workExperienceFactory.buildEntity(1);

        // Act
        CountryEntity actualCountryEntity = countryMapper.dtoToEntity(inputCountryDto);
        DietEntity actualDietEntity = dietMapper.dtoToEntity(inputDietDto);
        EducationEntity actualEducationEntity = educationMapper.dtoToEntity(inputEducationDto);
        GenderEntity actualGenderEntity = genderMapper.dtoToEntity(inputGenderDto);
        HobbyEntity actualHobbyEntity = hobbyMapper.dtoToEntity(inputHobbyDto);
        LanguageEntity actualLanguageEntity = languageMapper.dtoToEntity(inputLanguageDto);
        ProjectEntity actualProjectEntity = projectMapper.dtoToEntity(inputProjectDto);
        ReligionEntity actualReligionEntity = religionMapper.dtoToEntity(inputReligionDto);
        SexualOrientationEntity actualSexualOrientationEntity = sexualOrientationMapper.dtoToEntity(inputSexualOrientationDto);
        SocialBackgroundDiscriminationEntity actualSocialBackgroundDiscriminationEntity = socialBackgroundDiscriminationMapper.dtoToEntity(inputSocialBackgroundDiscriminationDto);
        SocialBackgroundEntity actualSocialBackgroundEntity = socialBackgroundMapper.dtoToEntity(inputSocialBackgroundDto);
        WorkExperienceEntity actualWorkExperienceEntity = workExperienceMapper.dtoToEntity(inputWorkExperienceDto);

        // Assert
        assertEquals(expectedCountryEntity, actualCountryEntity);
        assertEquals(expectedDietEntity, actualDietEntity);
        assertEquals(expectedEducationEntity, actualEducationEntity);
        assertEquals(expectedGenderEntity, actualGenderEntity);
        assertEquals(expectedHobbyEntity, actualHobbyEntity);
        assertEquals(expectedLanguageEntity, actualLanguageEntity);
        assertEquals(expectedProjectEntity, actualProjectEntity);
        assertEquals(expectedReligionEntity, actualReligionEntity);
        assertEquals(expectedSexualOrientationEntity, actualSexualOrientationEntity);
        assertEquals(expectedSocialBackgroundDiscriminationEntity, actualSocialBackgroundDiscriminationEntity);
        assertEquals(expectedSocialBackgroundEntity, actualSocialBackgroundEntity);
        assertEquals(expectedWorkExperienceEntity, actualWorkExperienceEntity);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities() {
        // Arrange
        List<CountryDto> inputCountryDto = Stream.of(1, 2).map(countryFactory::buildDto).toList();
        List<DietDto> inputDietDto = Stream.of(1, 2).map(dietFactory::buildDto).toList();
        List<EducationDto> inputEducationDto = Stream.of(1, 2).map(educationFactory::buildDto).toList();
        List<GenderDto> inputGenderDto = Stream.of(1, 2).map(genderFactory::buildDto).toList();
        List<HobbyDto> inputHobbyDto = Stream.of(1, 2).map(hobbyFactory::buildDto).toList();
        List<LanguageDto> inputLanguageDto = Stream.of(1, 2).map(languageFactory::buildDto).toList();
        List<ProjectDto> inputProjectDto = Stream.of(1, 2).map(projectFactory::buildDto).toList();
        List<ReligionDto> inputReligionDto = Stream.of(1, 2).map(religionFactory::buildDto).toList();
        List<SexualOrientationDto> inputSexualOrientationDto = Stream.of(1, 2).map(sexualOrientationFactory::buildDto).toList();
        List<SocialBackgroundDiscriminationDto> inputSocialBackgroundDiscriminationDto = Stream.of(1, 2).map(socialBackgroundDiscriminationFactory::buildDto).toList();
        List<SocialBackgroundDto> inputSocialBackgroundDto = Stream.of(1, 2).map(socialBackgroundFactory::buildDto).toList();
        List<WorkExperienceDto> inputWorkExperienceDto = Stream.of(1, 2).map(workExperienceFactory::buildDto).toList();

        List<CountryEntity> expectedCountryEntity = Stream.of(1, 2).map(countryFactory::buildEntity).toList();
        List<DietEntity> expectedDietEntity = Stream.of(1, 2).map(dietFactory::buildEntity).toList();
        List<EducationEntity> expectedEducationEntity = Stream.of(1, 2).map(educationFactory::buildEntity).toList();
        List<GenderEntity> expectedGenderEntity = Stream.of(1, 2).map(genderFactory::buildEntity).toList();
        List<HobbyEntity> expectedHobbyEntity = Stream.of(1, 2).map(hobbyFactory::buildEntity).toList();
        List<LanguageEntity> expectedLanguageEntity = Stream.of(1, 2).map(languageFactory::buildEntity).toList();
        List<ProjectEntity> expectedProjectEntity = Stream.of(1, 2).map(projectFactory::buildEntity).toList();
        List<ReligionEntity> expectedReligionEntity = Stream.of(1, 2).map(religionFactory::buildEntity).toList();
        List<SexualOrientationEntity> expectedSexualOrientationEntity = Stream.of(1, 2).map(sexualOrientationFactory::buildEntity).toList();
        List<SocialBackgroundDiscriminationEntity> expectedSocialBackgroundDiscriminationEntity = Stream.of(1, 2).map(socialBackgroundDiscriminationFactory::buildEntity).toList();
        List<SocialBackgroundEntity> expectedSocialBackgroundEntity = Stream.of(1, 2).map(socialBackgroundFactory::buildEntity).toList();
        List<WorkExperienceEntity> expectedWorkExperienceEntity = Stream.of(1, 2).map(workExperienceFactory::buildEntity).toList();

        // Act
        List<CountryEntity> actualCountryEntity = countryMapper.dtoToEntity(inputCountryDto);
        List<DietEntity> actualDietEntity = dietMapper.dtoToEntity(inputDietDto);
        List<EducationEntity> actualEducationEntity = educationMapper.dtoToEntity(inputEducationDto);
        List<GenderEntity> actualGenderEntity = genderMapper.dtoToEntity(inputGenderDto);
        List<HobbyEntity> actualHobbyEntity = hobbyMapper.dtoToEntity(inputHobbyDto);
        List<LanguageEntity> actualLanguageEntity = languageMapper.dtoToEntity(inputLanguageDto);
        List<ProjectEntity> actualProjectEntity = projectMapper.dtoToEntity(inputProjectDto);
        List<ReligionEntity> actualReligionEntity = religionMapper.dtoToEntity(inputReligionDto);
        List<SexualOrientationEntity> actualSexualOrientationEntity = sexualOrientationMapper.dtoToEntity(inputSexualOrientationDto);
        List<SocialBackgroundDiscriminationEntity> actualSocialBackgroundDiscriminationEntity = socialBackgroundDiscriminationMapper.dtoToEntity(inputSocialBackgroundDiscriminationDto);
        List<SocialBackgroundEntity> actualSocialBackgroundEntity = socialBackgroundMapper.dtoToEntity(inputSocialBackgroundDto);
        List<WorkExperienceEntity> actualWorkExperienceEntity = workExperienceMapper.dtoToEntity(inputWorkExperienceDto);

        // Assert
        assertEquals(2, actualCountryEntity.size());
        assertEquals(2, actualDietEntity.size());
        assertEquals(2, actualEducationEntity.size());
        assertEquals(2, actualGenderEntity.size());
        assertEquals(2, actualHobbyEntity.size());
        assertEquals(2, actualLanguageEntity.size());
        assertEquals(2, actualProjectEntity.size());
        assertEquals(2, actualReligionEntity.size());
        assertEquals(2, actualSexualOrientationEntity.size());
        assertEquals(2, actualSocialBackgroundDiscriminationEntity.size());
        assertEquals(2, actualSocialBackgroundEntity.size());
        assertEquals(2, actualWorkExperienceEntity.size());

        assertEquals(expectedCountryEntity, actualCountryEntity);
        assertEquals(expectedDietEntity, actualDietEntity);
        assertEquals(expectedEducationEntity, actualEducationEntity);
        assertEquals(expectedGenderEntity, actualGenderEntity);
        assertEquals(expectedHobbyEntity, actualHobbyEntity);
        assertEquals(expectedLanguageEntity, actualLanguageEntity);
        assertEquals(expectedProjectEntity, actualProjectEntity);
        assertEquals(expectedReligionEntity, actualReligionEntity);
        assertEquals(expectedSexualOrientationEntity, actualSexualOrientationEntity);
        assertEquals(expectedSocialBackgroundDiscriminationEntity, actualSocialBackgroundDiscriminationEntity);
        assertEquals(expectedSocialBackgroundEntity, actualSocialBackgroundEntity);
        assertEquals(expectedWorkExperienceEntity, actualWorkExperienceEntity);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList() {
        // Arrange
        List<CountryDto> inputCountryDto = Collections.emptyList();
        List<DietDto> inputDietDto = Collections.emptyList();
        List<EducationDto> inputEducationDto = Collections.emptyList();
        List<GenderDto> inputGenderDto = Collections.emptyList();
        List<HobbyDto> inputHobbyDto = Collections.emptyList();
        List<LanguageDto> inputLanguageDto = Collections.emptyList();
        List<ProjectDto> inputProjectDto = Collections.emptyList();
        List<ReligionDto> inputReligionDto = Collections.emptyList();
        List<SexualOrientationDto> inputSexualOrientationDto = Collections.emptyList();
        List<SocialBackgroundDiscriminationDto> inputSocialBackgroundDiscriminationDto = Collections.emptyList();
        List<SocialBackgroundDto> inputSocialBackgroundDto = Collections.emptyList();
        List<WorkExperienceDto> inputWorkExperienceDto = Collections.emptyList();

        // Act
        List<CountryEntity> actualCountryEntity = countryMapper.dtoToEntity(inputCountryDto);
        List<DietEntity> actualDietEntity = dietMapper.dtoToEntity(inputDietDto);
        List<EducationEntity> actualEducationEntity = educationMapper.dtoToEntity(inputEducationDto);
        List<GenderEntity> actualGenderEntity = genderMapper.dtoToEntity(inputGenderDto);
        List<HobbyEntity> actualHobbyEntity = hobbyMapper.dtoToEntity(inputHobbyDto);
        List<LanguageEntity> actualLanguageEntity = languageMapper.dtoToEntity(inputLanguageDto);
        List<ProjectEntity> actualProjectEntity = projectMapper.dtoToEntity(inputProjectDto);
        List<ReligionEntity> actualReligionEntity = religionMapper.dtoToEntity(inputReligionDto);
        List<SexualOrientationEntity> actualSexualOrientationEntity = sexualOrientationMapper.dtoToEntity(inputSexualOrientationDto);
        List<SocialBackgroundDiscriminationEntity> actualSocialBackgroundDiscriminationEntity = socialBackgroundDiscriminationMapper.dtoToEntity(inputSocialBackgroundDiscriminationDto);
        List<SocialBackgroundEntity> actualSocialBackgroundEntity = socialBackgroundMapper.dtoToEntity(inputSocialBackgroundDto);
        List<WorkExperienceEntity> actualWorkExperienceEntity = workExperienceMapper.dtoToEntity(inputWorkExperienceDto);

        // Assert
        assertTrue(actualCountryEntity.isEmpty());
        assertTrue(actualDietEntity.isEmpty());
        assertTrue(actualEducationEntity.isEmpty());
        assertTrue(actualGenderEntity.isEmpty());
        assertTrue(actualHobbyEntity.isEmpty());
        assertTrue(actualLanguageEntity.isEmpty());
        assertTrue(actualProjectEntity.isEmpty());
        assertTrue(actualReligionEntity.isEmpty());
        assertTrue(actualSexualOrientationEntity.isEmpty());
        assertTrue(actualSocialBackgroundDiscriminationEntity.isEmpty());
        assertTrue(actualSocialBackgroundEntity.isEmpty());
        assertTrue(actualWorkExperienceEntity.isEmpty());
    }
}
