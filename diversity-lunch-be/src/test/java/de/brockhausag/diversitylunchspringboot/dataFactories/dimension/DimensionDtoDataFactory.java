package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;

public class DimensionDtoDataFactory {

    public CountryDto buildCountryDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new CountryDto(1L, "Option1");
            case 2 -> new CountryDto(2L, "Option2");
            default -> new CountryDto(42L, "defaultOption");
        };
    }

    public DietDto buildDietDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new DietDto(1L, "Option1");
            case 2 -> new DietDto(2L, "Option2");
            default -> new DietDto(42L, "defaultOption");
        };
    }

    public EducationDto buildEducationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new EducationDto(1L, "Option1");
            case 2 -> new EducationDto(2L, "Option2");
            default -> new EducationDto(42L, "defaultOption");
        };
    }

    public GenderDto buildGenderDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new GenderDto(1L, "Option1");
            case 2 -> new GenderDto(2L, "Option2");
            default -> new GenderDto(42L, "defaultOption");
        };
    }

    public HobbyDto buildHobbyDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new HobbyDto(1L, "Option1");
            case 2 -> new HobbyDto(2L, "Option2");
            default -> new HobbyDto(42L, "defaultOption");
        };
    }

    public LanguageDto buildLanguageDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new LanguageDto(1L, "Option1");
            case 2 -> new LanguageDto(2L, "Option2");
            default -> new LanguageDto(42L, "defaultOption");
        };
    }

    public ProjectDto buildProjectDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new ProjectDto(1L, "Option1");
            case 2 -> new ProjectDto(2L, "Option2");
            default -> new ProjectDto(42L, "defaultOption");
        };
    }

    public ReligionDto buildReligionDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new ReligionDto(1L, "Option1");
            case 2 -> new ReligionDto(2L, "Option2");
            default -> new ReligionDto(42L, "defaultOption");
        };
    }

    public SexualOrientationDto buildSexualOrientationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SexualOrientationDto(1L, "Option1");
            case 2 -> new SexualOrientationDto(2L, "Option2");
            default -> new SexualOrientationDto(42L, "defaultOption");
        };
    }

    public SocialBackgroundDiscriminationDto buildSocialBackgroundDiscriminationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SocialBackgroundDiscriminationDto(1L, "Option1");
            case 2 -> new SocialBackgroundDiscriminationDto(2L, "Option2");
            default -> new SocialBackgroundDiscriminationDto(42L, "defaultOption");
        };
    }

    public SocialBackgroundDto buildSocialBackgroundDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SocialBackgroundDto(1L, "Option1");
            case 2 -> new SocialBackgroundDto(2L, "Option2");
            default -> new SocialBackgroundDto(42L, "defaultOption");
        };
    }

    public WorkExperienceDto buildWorkExperienceDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new WorkExperienceDto(1L, "Option1");
            case 2 -> new WorkExperienceDto(2L, "Option2");
            default -> new WorkExperienceDto(42L, "defaultOption");
        };
    }
    
}
