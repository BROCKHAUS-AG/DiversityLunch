package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileTestdataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {1L, 2L, 3L};
    private static final String[] names = {"first user", "second user", "third user"};
    private static final String[] emails = {"first.mail@some.tld", "second.mail@some.tld", "third.mail@some.tld"};
    private static final int[] birthYears = {1957, 1930, 2001};


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
        ProfileEntity result;
        switch (setNumber) {
            case 1:
                result = ProfileEntity.builder()
                        .id(1L)
                        .name("First User")
                        .email("first.mail@some.tld")
                        .birthYear(1957)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            case 2:
                result = ProfileEntity.builder()
                        .id(2L)
                        .name("Second User")
                        .email("second.mail@some.tld")
                        .birthYear(1930)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            case 3:
                result = ProfileEntity.builder()
                        .id(3L)
                        .name("Third User")
                        .email("third.mail@some.tld")
                        .birthYear(2001)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            default:
                result = ProfileEntity.builder()
                        .id(10L)
                        .name("Default User")
                        .email("default.mail@some.tld")
                        .birthYear(2000)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
        }
        return result;
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

    private Map<BasicDimension, BasicDimensionSelectableOption> getBasicSelectedOptions(int setNumber) {
        BasicDimensionTestDataFactory basicFactory = new BasicDimensionTestDataFactory();
        BasicSelectableOptionTestDataFactory basicSelectableFactory = new BasicSelectableOptionTestDataFactory();
        Set<BasicDimension> basicDimensions = basicFactory.buildEntities(2);
        Map<BasicDimension, BasicDimensionSelectableOption> result = new HashMap<>();
        int selectedValue = 0;

        for (BasicDimension d : basicDimensions) {
            switch (setNumber) {
                case 1:
                    selectedValue = 1;
                    break;
                case 2:
                    selectedValue = 2;
                    break;
                case 3:
                    selectedValue++;
                    break;
                default:
            }
            result.put(d, basicSelectableFactory.buildEntity(d.getDimensionCategory(), selectedValue));
        }
        return result;
    }
}