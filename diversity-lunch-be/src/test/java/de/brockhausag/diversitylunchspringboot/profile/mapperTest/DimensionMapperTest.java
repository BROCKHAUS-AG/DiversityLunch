package de.brockhausag.diversitylunchspringboot.profile.mapperTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.*;
import de.brockhausag.diversitylunchspringboot.profile.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(false);
    }

    @Test
    void testEntityToDto_withListOfThreeEntities_returnsListOfThreeDtos() {
        assertTrue(false);
    }

    @Test
    void testEntityToDto_withEmptyEntityList_returnsEmptyDtoList() {
        assertTrue(false);
    }

    @Test
    void testDtoToEntity_withOneDto_returnsOneEntity() {
        assertTrue(false);
    }

    @Test
    void testDtoToEntity_withListOfTwoDtos_returnsListOfTwoEntities() {
        assertTrue(false);
    }

    @Test
    void testDtoToEntity_withEmptyDtoList_returnsEmptyEntityList() {
        assertTrue(false);
    }


}
