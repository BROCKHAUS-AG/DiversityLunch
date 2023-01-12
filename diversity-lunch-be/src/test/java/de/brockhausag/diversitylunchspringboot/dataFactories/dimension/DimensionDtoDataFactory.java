package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;

public class DimensionDtoDataFactory {

    public CountryDto buildCountryDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new CountryDto(1L, "Option1");
            case 2 -> new CountryDto(2L, "Option2");
            case 3 -> new CountryDto(3L, "Option3");
            default -> new CountryDto(10L, "defaultOption");
        };
    }

    public DietDto buildDietDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new DietDto(1L, "Option1");
            case 2 -> new DietDto(2L, "Option2");
            case 3 -> new DietDto(3L, "Option3");
            default -> new DietDto(10L, "defaultOption");
        };
    }

    public EducationDto buildEducationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new EducationDto(1L, "Option1");
            case 2 -> new EducationDto(2L, "Option2");
            case 3 -> new EducationDto(3L, "Option3");
            default -> new EducationDto(10L, "defaultOption");
        };
    }

    public GenderDto buildGenderDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new GenderDto(1L, "Option1");
            case 2 -> new GenderDto(2L, "Option2");
            case 3 -> new GenderDto(3L, "Option3");
            default -> new GenderDto(10L, "defaultOption");
        };
    }

    public HobbyDto buildHobbyDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new HobbyDto(1L, "Option1");
            case 2 -> new HobbyDto(2L, "Option2");
            case 3 -> new HobbyDto(3L, "Option3");
            default -> new HobbyDto(10L, "defaultOption");
        };
    }

    public LanguageDto buildLanguageDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new LanguageDto(1L, "Option1");
            case 2 -> new LanguageDto(2L, "Option2");
            case 3 -> new LanguageDto(3L, "Option3");
            default -> new LanguageDto(10L, "defaultOption");
        };
    }

    public ProjectDto buildProjectDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new ProjectDto(1L, "Option1");
            case 2 -> new ProjectDto(2L, "Option2");
            case 3 -> new ProjectDto(3L, "Option3");
            default -> new ProjectDto(10L, "defaultOption");
        };
    }

    public ReligionDto buildReligionDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new ReligionDto(1L, "Option1");
            case 2 -> new ReligionDto(2L, "Option2");
            case 3 -> new ReligionDto(3L, "Option3");
            default -> new ReligionDto(10L, "defaultOption");
        };
    }

    public SexualOrientationDto buildSexualOrientationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SexualOrientationDto(1L, "Option1");
            case 2 -> new SexualOrientationDto(2L, "Option2");
            case 3 -> new SexualOrientationDto(3L, "Option3");
            default -> new SexualOrientationDto(10L, "defaultOption");
        };
    }

    public SocialBackgroundDiscriminationDto buildSocialBackgroundDiscriminationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SocialBackgroundDiscriminationDto(1L, "Option1");
            case 2 -> new SocialBackgroundDiscriminationDto(2L, "Option2");
            case 3 -> new SocialBackgroundDiscriminationDto(3L, "Option3");
            default -> new SocialBackgroundDiscriminationDto(10L, "defaultOption");
        };
    }

    public SocialBackgroundDto buildSocialBackgroundDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SocialBackgroundDto(1L, "Option1");
            case 2 -> new SocialBackgroundDto(2L, "Option2");
            case 3 -> new SocialBackgroundDto(3L, "Option3");
            default -> new SocialBackgroundDto(10L, "defaultOption");
        };
    }

    public WorkExperienceDto buildWorkExperienceDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new WorkExperienceDto(1L, "Option1");
            case 2 -> new WorkExperienceDto(2L, "Option2");
            case 3 -> new WorkExperienceDto(3L, "Option3");
            default -> new WorkExperienceDto(10L, "defaultOption");
        };
    }
}
