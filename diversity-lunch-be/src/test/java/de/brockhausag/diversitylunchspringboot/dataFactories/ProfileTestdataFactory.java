package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.ProfileEntitySelectedMultiselectValue;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;
import liquibase.pro.packaged.E;

import java.util.*;

public class ProfileTestdataFactory {

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
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            case 2:
                result = ProfileEntity.builder()
                        .id(2L)
                        .name("Second User")
                        .email("second.mail@some.tld")
                        .birthYear(2001)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            case 3:
                result = ProfileEntity.builder()
                        .id(3L)
                        .name("Third User")
                        .email("third.mail@some.tld")
                        .birthYear(1969)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
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
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
        }
        return result;
    }

    public ProfileDto buildDto(int setNumber) {
        ProfileDto result;
        switch (setNumber) {
            case 1:
                result = ProfileDto.builder()
                        .id(1L)
                        .name("First User")
                        .email("first.mail@some.tld")
                        .birthYear(1957)
                        .originCountry(new CountryDto(1L, "Option1"))
                        .diet(new DietDto(1L, "Option2"))
                        .education(new EducationDto(1L, "Option1"))
                        .gender(new GenderDto(1L, "Option1"))
                        .hobby(List.of(new HobbyDto(1L, "Option1"), new HobbyDto(2L, "Option2"), new HobbyDto(3L, "Option3")))
                        .motherTongue(new LanguageDto(1L, "Option1"))
                        .project(new ProjectDto(1L, "Option1"))
                        .religion(new ReligionDto(1L, "Option1"))
                        .sexualOrientation(new SexualOrientationDto(1L, "Option1"))
                        .socialBackgroundDiscrimination(new SocialBackgroundDiscriminationDto(1L, "Option1"))
                        .socialBackground(new SocialBackgroundDto(1L, "Option1"))
                        .workExperience(new WorkExperienceDto(1L, "Option1"))
                        .build();
                break;
            case 2:
                result = ProfileDto.builder()
                        .id(2L)
                        .name("Third User")
                        .email("third.mail@some.tld")
                        .birthYear(1969)
                        .originCountry(new CountryDto(3L, "Option3"))
                        .diet(new DietDto(3L, "Option3"))
                        .education(new EducationDto(3L, "Option3"))
                        .gender(new GenderDto(3L, "Option3"))
                        .hobby(List.of(new HobbyDto(4L, "Option4"), new HobbyDto(5L, "Option5"), new HobbyDto(6L, "Option6")))
                        .motherTongue(new LanguageDto(3L, "Option3"))
                        .project(new ProjectDto(3L, "Option3"))
                        .religion(new ReligionDto(3L, "Option3"))
                        .sexualOrientation(new SexualOrientationDto(3L, "Option3"))
                        .socialBackgroundDiscrimination(new SocialBackgroundDiscriminationDto(3L, "Option3"))
                        .socialBackground(new SocialBackgroundDto(3L, "Option3"))
                        .workExperience(new WorkExperienceDto(3L, "Option3"))
                        .build();
                break;
            case 3:
                result = ProfileDto.builder()
                        .id(3L)
                        .name("Second User")
                        .email("second.mail@some.tld")
                        .birthYear(2001)
                        .originCountry(new CountryDto(10L, "DefaultOption"))
                        .diet(new DietDto(1L, "Option1"))
                        .education(new EducationDto(2L, "Option2"))
                        .gender(new GenderDto(3L, "Option3"))
                        .hobby(List.of(new HobbyDto(3L, "Option3"), new HobbyDto(4L, "Option4"), new HobbyDto(5L, "Option5")))
                        .motherTongue(new LanguageDto(10L, "DefaultOption"))
                        .project(new ProjectDto(1L, "Option1"))
                        .religion(new ReligionDto(2L, "Option2"))
                        .sexualOrientation(new SexualOrientationDto(3L, "Option3"))
                        .socialBackgroundDiscrimination(new SocialBackgroundDiscriminationDto(10L, "DefaultOption"))
                        .socialBackground(new SocialBackgroundDto(1L, "Option1"))
                        .workExperience(new WorkExperienceDto(1L, "Option1"))
                        .build();
                break;
            default:
                result = ProfileDto.builder()
                        .id(10L)
                        .name("Default User")
                        .email("default.mail@some.tld")
                        .birthYear(2000)
                        .originCountry(new CountryDto(10L, "DefaultOption"))
                        .diet(new DietDto(10L, "DefaultOption"))
                        .education(new EducationDto(10L, "DefaultOption"))
                        .gender(new GenderDto(10L, "DefaultOption"))
                        .hobby(new ArrayList<>())
                        .motherTongue(new LanguageDto(10L, "DefaultOption"))
                        .project(new ProjectDto(10L, "DefaultOption"))
                        .religion(new ReligionDto(10L, "DefaultOption"))
                        .sexualOrientation(new SexualOrientationDto(10L, "DefaultOption"))
                        .socialBackgroundDiscrimination(new SocialBackgroundDiscriminationDto(10L, "DefaultOption"))
                        .socialBackground(new SocialBackgroundDto(10L, "DefaultOption"))
                        .workExperience(new WorkExperienceDto(10L, "DefaultOption"))
                        .build();
        }
        return result;
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
                    selectedValue = 3;
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

    private Map<WeightedDimension, WeightedDimensionSelectableOption> getWeightedSelectedOptions(int setNumber) {
        WeightedDimensionTestDataFactory WeightedFactory = new WeightedDimensionTestDataFactory();
        WeightedSelectableOptionTestDataFactory WeightedSelectableFactory = new WeightedSelectableOptionTestDataFactory();
        Set<WeightedDimension> WeightedDimensions = WeightedFactory.buildEntities(2);
        Map<WeightedDimension, WeightedDimensionSelectableOption> result = new HashMap<>();
        int selectedValue = 0;

        for (WeightedDimension d : WeightedDimensions) {
            switch (setNumber) {
                case 1:
                    selectedValue = 1;
                    break;
                case 2:
                    selectedValue = 3;
                    break;
                case 3:
                    selectedValue++;
                    break;
                default:
            }
            result.put(d, WeightedSelectableFactory.buildEntity(d.getDimensionCategory(), selectedValue));
        }
        return result;
    }
    private Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> getMultiselectSelectedOptions(int setNumber) {
        MultiselectDimensionTestDataFactory MultiselectFactory = new MultiselectDimensionTestDataFactory();
        MultiselectSelectableOptionTestDataFactory MultiselectSelectableFactory = new MultiselectSelectableOptionTestDataFactory();
        Set<MultiselectDimension> MultiselectDimensions = MultiselectFactory.buildEntities(2);
        Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> result = new HashMap<>();

        for (MultiselectDimension d : MultiselectDimensions) {
            Set<MultiselectDimensionSelectableOption> selectedOptions = new HashSet<>();
            switch (setNumber) {
                case 1:
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 1));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 2));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 3));
                    break;
                case 2:
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 4));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 5));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 6));
                    break;
                case 3:
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 3));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 4));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 5));
                    break;
                default:
            }
            ProfileEntitySelectedMultiselectValue selectedMultiselectValue = new ProfileEntitySelectedMultiselectValue();
            selectedMultiselectValue.setSelectedOptions(selectedOptions);
            result.put(d, selectedMultiselectValue);
        }
        return result;
    }

}