package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.*;

import java.util.List;

public class DimensionDtoDataFactory {

    public CountryDto buildCountryDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new CountryDto(9L, "Option1");
            case 2 -> new CountryDto(10L, "Option2");
            case 3 -> new CountryDto(11L, "Option3");
            default -> new CountryDto(12L, "defaultOption");
        };
    }

    public DietDto buildDietDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new DietDto(25L, "Option1");
            case 2 -> new DietDto(26L, "Option2");
            case 3 -> new DietDto(27L, "Option3");
            default -> new DietDto(28L, "defaultOption");
        };
    }

    public EducationDto buildEducationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new EducationDto(21L, "Option1");
            case 2 -> new EducationDto(22L, "Option2");
            case 3 -> new EducationDto(23L, "Option3");
            default -> new EducationDto(24L, "defaultOption");
        };
    }

    public GenderDto buildGenderDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new GenderDto(5L, "Option1");
            case 2 -> new GenderDto(6L, "Option2");
            case 3 -> new GenderDto(7L, "Option3");
            default -> new GenderDto(8L, "defaultOption");
        };
    }

    public HobbyDto buildHobbyDto(int setNumber){
        return new HobbyDto((long) setNumber, "Option" + setNumber);
    }

    public List<HobbyDto> buildHobbyDtos(List<Integer> setNumbers) {
        return setNumbers.stream().map(this::buildHobbyDto).toList();
    }

    public LanguageDto buildLanguageDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new LanguageDto(17L, "Option1");
            case 2 -> new LanguageDto(18L, "Option2");
            case 3 -> new LanguageDto(19L, "Option3");
            default -> new LanguageDto(20L, "defaultOption");
        };
    }

    public ProjectDto buildProjectDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new ProjectDto(1L, "Option1");
            case 2 -> new ProjectDto(2L, "Option2");
            case 3 -> new ProjectDto(3L, "Option3");
            default -> new ProjectDto(4L, "defaultOption");
        };
    }

    public ReligionDto buildReligionDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new ReligionDto(13L, "Option1");
            case 2 -> new ReligionDto(14L, "Option2");
            case 3 -> new ReligionDto(15L, "Option3");
            default -> new ReligionDto(16L, "defaultOption");
        };
    }

    public SexualOrientationDto buildSexualOrientationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SexualOrientationDto(37L, "Option1");
            case 2 -> new SexualOrientationDto(38L, "Option2");
            case 3 -> new SexualOrientationDto(39L, "Option3");
            default -> new SexualOrientationDto(40L, "defaultOption");
        };
    }

    public SocialBackgroundDiscriminationDto buildSocialBackgroundDiscriminationDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SocialBackgroundDiscriminationDto(33L, "Option1");
            case 2 -> new SocialBackgroundDiscriminationDto(34L, "Option2");
            case 3 -> new SocialBackgroundDiscriminationDto(35L, "Option3");
            default -> new SocialBackgroundDiscriminationDto(36L, "defaultOption");
        };
    }

    public SocialBackgroundDto buildSocialBackgroundDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new SocialBackgroundDto(29L, "Option1");
            case 2 -> new SocialBackgroundDto(30L, "Option2");
            case 3 -> new SocialBackgroundDto(31L, "Option3");
            default -> new SocialBackgroundDto(32L, "defaultOption");
        };
    }

    public WorkExperienceDto buildWorkExperienceDto(int setNumber){
        return switch (setNumber) {
            case 1 -> new WorkExperienceDto(1L, "Option1");
            case 2 -> new WorkExperienceDto(2L, "Option2");
            case 3 -> new WorkExperienceDto(3L, "Option3");
            default -> new WorkExperienceDto(4L, "defaultOption");
        };
    }
}
