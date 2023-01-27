package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;

import java.util.*;

public class ProfileTestdataFactory {

    private final DimensionDtoDataFactory dtoDataFactory = new DimensionDtoDataFactory();

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

    // TODO: dtos anpassen, sodass das gleiche drin steht, wie bei der entity
    public ProfileDto buildDto(int setNumber) {
        ProfileDto result;
        switch (setNumber) {
            case 1:
                result = ProfileDto.builder()
                        .id(1L)
                        .name("First User")
                        .email("first.mail@some.tld")
                        .birthYear(1957)
                        .originCountry(dtoDataFactory.buildCountryDto(1))
                        .diet(dtoDataFactory.buildDietDto(1))
                        .education(dtoDataFactory.buildEducationDto(1))
                        .gender(dtoDataFactory.buildGenderDto(1))
                        .hobby(dtoDataFactory.buildHobbyDtos(List.of(1, 2, 3)))
                        .motherTongue(dtoDataFactory.buildLanguageDto(1))
                        .project(dtoDataFactory.buildProjectDto(1))
                        .religion(dtoDataFactory.buildReligionDto(1))
                        .sexualOrientation(dtoDataFactory.buildSexualOrientationDto(1))
                        .socialBackgroundDiscrimination(dtoDataFactory.buildSocialBackgroundDiscriminationDto(1))
                        .socialBackground(dtoDataFactory.buildSocialBackgroundDto(1))
                        .workExperience(dtoDataFactory.buildWorkExperienceDto(1))
                        .build();
                break;
            case 2:
                result = ProfileDto.builder()
                        .id(2L)
                        .name("Second User")
                        .email("second.mail@some.tld")
                        .birthYear(2001)
                        .originCountry(dtoDataFactory.buildCountryDto(3))
                        .diet(dtoDataFactory.buildDietDto(3))
                        .education(dtoDataFactory.buildEducationDto(3))
                        .gender(dtoDataFactory.buildGenderDto(3))
                        .hobby(dtoDataFactory.buildHobbyDtos(List.of(4, 5, 6)))
                        .motherTongue(dtoDataFactory.buildLanguageDto(3))
                        .project(dtoDataFactory.buildProjectDto(3))
                        .religion(dtoDataFactory.buildReligionDto(3))
                        .sexualOrientation(dtoDataFactory.buildSexualOrientationDto(3))
                        .socialBackgroundDiscrimination(dtoDataFactory.buildSocialBackgroundDiscriminationDto(3))
                        .socialBackground(dtoDataFactory.buildSocialBackgroundDto(3))
                        .workExperience(dtoDataFactory.buildWorkExperienceDto(3))
                        .build();
                break;
            case 3:
                result = ProfileDto.builder()
                        .id(3L)
                        .name("Third User")
                        .email("third.mail@some.tld")
                        .birthYear(1969)
                        .originCountry(dtoDataFactory.buildCountryDto(1))
                        .diet(dtoDataFactory.buildDietDto(1))
                        .education(dtoDataFactory.buildEducationDto(2))
                        .gender(dtoDataFactory.buildGenderDto(2))
                        .hobby(dtoDataFactory.buildHobbyDtos(List.of(3, 4, 5)))
                        .motherTongue(dtoDataFactory.buildLanguageDto(1))
                        .project(dtoDataFactory.buildProjectDto(1))
                        .religion(dtoDataFactory.buildReligionDto(2))
                        .sexualOrientation(dtoDataFactory.buildSexualOrientationDto(2))
                        .socialBackgroundDiscrimination(dtoDataFactory.buildSocialBackgroundDiscriminationDto(1))
                        .socialBackground(dtoDataFactory.buildSocialBackgroundDto(2))
                        .workExperience(dtoDataFactory.buildWorkExperienceDto(1))
                        .build();
                break;
            default:
                result = ProfileDto.builder()
                        .id(10L)
                        .name("Default User")
                        .email("default.mail@some.tld")
                        .birthYear(2000)
                        .originCountry(dtoDataFactory.buildCountryDto(0))
                        .diet(dtoDataFactory.buildDietDto(0))
                        .education(dtoDataFactory.buildEducationDto(0))
                        .gender(dtoDataFactory.buildGenderDto(0))
                        .hobby(dtoDataFactory.buildHobbyDtos(Collections.emptyList()))
                        .motherTongue(dtoDataFactory.buildLanguageDto(0))
                        .project(dtoDataFactory.buildProjectDto(0))
                        .religion(dtoDataFactory.buildReligionDto(0))
                        .sexualOrientation(dtoDataFactory.buildSexualOrientationDto(0))
                        .socialBackgroundDiscrimination(dtoDataFactory.buildSocialBackgroundDiscriminationDto(0))
                        .socialBackground(dtoDataFactory.buildSocialBackgroundDto(0))
                        .workExperience(dtoDataFactory.buildWorkExperienceDto(0))
                        .build();
        }
        return result;
    }

    private Map<BasicDimension, BasicDimensionSelectableOption> getBasicSelectedOptions(int setNumber) {
        BasicDimensionTestDataFactory basicFactory = new BasicDimensionTestDataFactory();
        BasicSelectableOptionTestDataFactory basicSelectableFactory = new BasicSelectableOptionTestDataFactory();
        Set<BasicDimension> basicDimensions = basicFactory.buildEntities(BasicDimensionTestDataFactory.BASIC_SET_SIZE);
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
                    selectedValue %= 2;
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
        Set<WeightedDimension> WeightedDimensions = WeightedFactory.buildEntities(WeightedDimensionTestDataFactory.WEIGHTED_SET_SIZE);
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
                    selectedValue %= 2;
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
        Set<MultiselectDimension> MultiselectDimensions = MultiselectFactory.buildEntities(MultiselectDimensionTestDataFactory.MULTISELECT_SET_SIZE);
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