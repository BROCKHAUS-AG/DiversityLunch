package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import java.util.List;

public class ProfileTestdataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] names = {"incomplete user", "first user", "second user", "third user"};
    private static final String[] emails = {"incomplete.mail@some.tdl", "first.mail@some.tld", "second.mail@some.tld", "third.mail@some.tld"};
    private static final int[] birthYears = {1901, 1957, 1930, 2001};


    private final CountryTestDataFactory countryFactory = new CountryTestDataFactory();
    private final DietTestDataFactory dietFactory = new DietTestDataFactory();
    private final EducationTestDataFactory educationFactory = new EducationTestDataFactory();
    private final GenderTestDataFactory genderFactory = new GenderTestDataFactory();
    private final LanguageTestDataFactory languageFactory = new LanguageTestDataFactory();
    private final ProjectTestDataFactory projectFactory = new ProjectTestDataFactory();
    private final ReligionTestDataFactory religionFactory = new ReligionTestDataFactory();
    private final WorkExperienceTestDataFactory workExperienceFactory = new WorkExperienceTestDataFactory();
    private final HobbyTestDataFactory hobbyFactory = new HobbyTestDataFactory();
    private final SexualOrientationTestDataFactory sexualOrientationFactory = new SexualOrientationTestDataFactory();
    private final SocialBackgroundTestDataFactory socialBackgroundFactory = new SocialBackgroundTestDataFactory();
    private final SocialBackgroundDiscriminationTestDataFactory socialBackgroundDiscriminationFactory = new SocialBackgroundDiscriminationTestDataFactory();

    public ProfileEntity buildEntity(int setNumber) {
        int actualSetNumber = (setNumber >= 1) && (setNumber <= numberOfCompleteSets) ? setNumber : 1;
        return new ProfileEntity(
                ids[actualSetNumber], names[actualSetNumber], emails[actualSetNumber], birthYears[actualSetNumber],
                countryFactory.buildEntity(actualSetNumber),
                dietFactory.buildEntity(actualSetNumber),
                educationFactory.buildEntity(actualSetNumber),
                genderFactory.buildEntity(actualSetNumber),
                languageFactory.buildEntity(actualSetNumber),
                projectFactory.buildEntity(actualSetNumber),
                religionFactory.buildEntity(actualSetNumber),
                workExperienceFactory.buildEntity(actualSetNumber),
                List.of(hobbyFactory.buildEntity(actualSetNumber)),
                sexualOrientationFactory.buildEntity(actualSetNumber),
                socialBackgroundFactory.buildEntity(actualSetNumber),
                socialBackgroundDiscriminationFactory.buildEntity(actualSetNumber),
                false);
    }

    public ProfileDto buildDto(int setNumber) {
        int actualSetNumber = (setNumber >= 1) && (setNumber <= numberOfCompleteSets) ? setNumber : 1;
        return new ProfileDto(
                ids[actualSetNumber], names[actualSetNumber], emails[actualSetNumber], birthYears[actualSetNumber],
                countryFactory.buildDto(actualSetNumber),
                dietFactory.buildDto(actualSetNumber),
                educationFactory.buildDto(actualSetNumber),
                genderFactory.buildDto(actualSetNumber),
                languageFactory.buildDto(actualSetNumber),
                projectFactory.buildDto(actualSetNumber),
                religionFactory.buildDto(actualSetNumber),
                workExperienceFactory.buildDto(actualSetNumber),
                hobbyFactory.buildDtoList(actualSetNumber),
                sexualOrientationFactory.buildDto(actualSetNumber),
                socialBackgroundFactory.buildDto(actualSetNumber),
                socialBackgroundDiscriminationFactory.buildDto(actualSetNumber));
    }
}
