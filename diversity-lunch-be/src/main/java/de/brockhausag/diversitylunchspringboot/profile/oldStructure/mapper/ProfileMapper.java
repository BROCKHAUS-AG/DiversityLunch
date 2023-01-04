package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.DimensionCategoryRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.logic.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.ProfileDto;
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
    private final BasicDimensionService basicDimensionService;
    private final WeightedDimensionService weightedDimensionService;
    private final MultiselectDimensionService multiselectDimensionService;


    public ProfileDto entityToDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setBirthYear(entity.getBirthYear());
        dto.setEducation(educationMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Bildungsweg"))));
        dto.setDiet(dietMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Ernährung"))));
        dto.setGender(genderMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Geschlechtliche Identität"))));
        dto.setHobby(hobbyMapper.entityToDto(List.copyOf(entity.getSelectedMultiselectValues().get(multiselectDimensionService.getDimension("Hobby")).getSelectedOptions())));
        dto.setMotherTongue(languageMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Muttersprache"))));
        dto.setOriginCountry(countryMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Ethnische Herkunft"))));
        dto.setProject(projectMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Projekt"))));
        dto.setReligion(religionMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Religion"))));
        dto.setSexualOrientation(sexualOrientationMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Sexuelle Orientierung"))));
        dto.setSocialBackground(socialBackgroundMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Soziale Herkunft"))));
        dto.setSocialBackgroundDiscrimination(socialBackgroundDiscriminationMapper.entityToDto(entity.getSelectedBasicValues().get(basicDimensionService.getDimension("Diskriminierung aufgrund sozialer Herkunft"))));
        dto.setWorkExperience(workExperienceMapper.entityToDto(entity.getSelectedWeightedValues().get(weightedDimensionService.getDimension("Berufserfahrung"))));


        return dto;
    }


    public Optional<ProfileEntity> dtoToEntity(ProfileDto dto) {
        Optional<CountryEntity> countryEntityOptional = this.countryService.getEntityById(dto.getOriginCountry().getId());
        Optional<DietEntity> dietEntityOptional = this.dietService.getEntityById(dto.getDiet().getId());
        Optional<EducationEntity> educationEntityOptional = this.educationService.getEntityById(dto.getEducation().getId());
        Optional<GenderEntity> genderEntityOptional = this.genderService.getEntityById(dto.getGender().getId());
        List<HobbyEntity> hobbyEntityList = this.hobbyService.getEntitySelectionByIds(dto.getHobby().stream().map(HobbyDto::getId).collect(Collectors.toList()));
        Optional<LanguageEntity> languageEntityOptional = this.languageService.getEntityById(dto.getMotherTongue().getId());
        Optional<ProjectEntity> projectEntityOptional = this.projectService.getEntityById(dto.getProject().getId());
        Optional<ReligionEntity> religionEntityOptional = this.religionService.getEntityById(dto.getReligion().getId());
        Optional<WorkExperienceEntity> workExperienceEntityOptional = this.workExperienceService.getEntityById(dto.getWorkExperience().getId());
        Optional<SexualOrientationEntity> sexualOrientationEntityOptional = this.sexualOrientationService.getEntityById(dto.getSexualOrientation().getId());
        Optional<SocialBackgroundEntity> socialBackgroundEntityOptional = this.socialBackgroundService.getEntityById(dto.getSocialBackground().getId());
        Optional<SocialBackgroundDiscriminationEntity> socialBackgroundDiscriminationEntityOptional = this.socialBackgroundDiscriminationService.getEntityById(dto.getSocialBackgroundDiscrimination().getId());

        if (this.allObjectWithIdsArePresent(countryEntityOptional, dietEntityOptional, educationEntityOptional,
                genderEntityOptional, languageEntityOptional, projectEntityOptional,
                religionEntityOptional, workExperienceEntityOptional, socialBackgroundEntityOptional))
        {
            // TODO: remove stream and if clause on top
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
                            .hobby(hobbyEntityList)
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
