package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.logic.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfileMapper {
    private final EducationMapper educationMapper;
    private final DietMapper dietMapper;
    private final GenderMapper genderMapper;
    private final LanguageMapper languageMapper;
    private final CountryMapper countryMapper;
    private final ProjectMapper projectMapper;
    private final ReligionMapper religionMapper;
    private final WorkExperienceMapper workExperienceMapper;
    private final HobbyMapper hobbyMapper;
    private final SexualOrientationMapper sexualOrientationMapper;
    private final SocialBackgroundMapper socialBackgroundMapper;

    private final SocialBackgroundDiscriminationMapper socialBackgroundDiscriminationMapper;

    private final CountryService countryService;
    private final DietService dietService;
    private final EducationService educationService;
    private final GenderService genderService;
    private final HobbyService hobbyService;
    private final LanguageService languageService;
    private final ProjectService projectService;
    private final ReligionService religionService;
    private final WorkExperienceService workExperienceService;

    private final SexualOrientationService sexualOrientationService;
    private final SocialBackgroundService socialBackgroundService;

    private final SocialBackgroundDiscriminationService socialBackgroundDiscriminationService;


    public ProfileDto entityToDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setBirthYear(entity.getBirthYear());
        dto.setEducation(educationMapper.entityToDto(entity.getEducation()));
        dto.setDiet(dietMapper.entityToDto(entity.getDiet()));
        dto.setGender(genderMapper.entityToDto(entity.getGender()));
        dto.setMotherTongue(languageMapper.entityToDto(entity.getMotherTongue()));
        dto.setOriginCountry(countryMapper.entityToDto(entity.getOriginCountry()));
        dto.setProject(projectMapper.entityToDto(entity.getProject()));
        dto.setReligion(religionMapper.entityToDto(entity.getReligion()));
        dto.setWorkExperience(workExperienceMapper.entityToDto(entity.getWorkExperience()));
        dto.setSexualOrientation(sexualOrientationMapper.entityToDto(entity.getSexualOrientation()));
        dto.setHobby(hobbyMapper.entityToDto(entity.getHobby().get(0)));
        dto.setSocialBackground(socialBackgroundMapper.entityToDto(entity.getSocialBackground()));
        dto.setSocialBackgroundDiscrimination(socialBackgroundDiscriminationMapper.entityToDto(entity.getSocialBackgroundDiscrimination()));


        return dto;
    }


    public Optional<ProfileEntity> dtoToEntity(ProfileDto dto) {
        Optional<CountryEntity> countryEntityOptional = this.countryService.getEntityById(dto.getOriginCountry().getId());
        Optional<DietEntity> dietEntityOptional = this.dietService.getEntityById(dto.getDiet().getId());
        Optional<EducationEntity> educationEntityOptional = this.educationService.getEntityById(dto.getEducation().getId());
        Optional<GenderEntity> genderEntityOptional = this.genderService.getEntityById(dto.getGender().getId());
        Optional<HobbyEntity> hobbyEntityOptional = this.hobbyService.getEntityById(dto.getHobby().getId());
        Optional<LanguageEntity> languageEntityOptional = this.languageService.getEntityById(dto.getMotherTongue().getId());
        Optional<ProjectEntity> projectEntityOptional = this.projectService.getEntityById(dto.getProject().getId());
        Optional<ReligionEntity> religionEntityOptional = this.religionService.getEntityById(dto.getReligion().getId());
        Optional<WorkExperienceEntity> workExperienceEntityOptional = this.workExperienceService.getEntityById(dto.getWorkExperience().getId());
        Optional<SexualOrientationEntity> sexualOrientationEntityOptional = this.sexualOrientationService.getEntityById(dto.getSexualOrientation().getId());
        Optional<SocialBackgroundEntity> socialBackgroundEntityOptional = this.socialBackgroundService.getEntityById(dto.getSocialBackground().getId());
        Optional<SocialBackgroundDiscriminationEntity> socialBackgroundDiscriminationEntityOptional = this.socialBackgroundDiscriminationService.getEntityById(dto.getSocialBackgroundDiscrimination().getId());

        if (this.allObjectWithIdsArePresent(countryEntityOptional, dietEntityOptional, educationEntityOptional,
                genderEntityOptional, hobbyEntityOptional, languageEntityOptional, projectEntityOptional,
                religionEntityOptional, workExperienceEntityOptional, socialBackgroundEntityOptional)) {

            return Optional.of(
                    ProfileEntity.builder()
                            .id(dto.getId())
                            .name(dto.getName())
                            .email(dto.getEmail())
                            .birthYear(dto.getBirthYear())
                            .originCountry(countryEntityOptional.orElseThrow())
                            .diet(dietEntityOptional.orElseThrow())
                            .education(educationEntityOptional.orElseThrow())
                            .gender(genderEntityOptional.orElseThrow())
                            .hobby(List.of(hobbyEntityOptional.orElseThrow()))
                            .motherTongue(languageEntityOptional.orElseThrow())
                            .project(projectEntityOptional.orElseThrow())
                            .religion(religionEntityOptional.orElseThrow())
                            .workExperience(workExperienceEntityOptional.orElseThrow())
                            .sexualOrientation(sexualOrientationEntityOptional.orElseThrow())
                            .socialBackground(socialBackgroundEntityOptional.orElseThrow())
                            .socialBackgroundDiscrimination(socialBackgroundDiscriminationEntityOptional.orElseThrow())
                            .build()
            );
        }
        return Optional.empty();
    }

    private boolean allObjectWithIdsArePresent(Optional<?>... optionals) {
        return Arrays.stream(optionals).allMatch(Optional::isPresent);
    }

    public List<ProfileDto> entityToDto(List<ProfileEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
